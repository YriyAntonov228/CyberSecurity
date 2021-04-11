package com.antonov;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;

import com.antonov.net.NetProcessor;
import com.antonov.reporting.AcessReportingWriter;
import com.antonov.serial.SerialProcessor;
import com.antonov.settings.Settings;
import com.antonov.settings.SettingsManager;
import com.antonov.ui.MainWindow;
import com.antonov.ui.SplashDialogs;
import com.antonov.ui.WelcomeWindow;
import com.antonov.ui.chat.MessagePanel;
import com.antonov.ui.help.AboutWindow;
import com.antonov.ui.help.FaqWindow;
import com.antonov.ui.od.ObjectDetectionControlPanel;
import com.antonov.ui.settings.SettingsWindow;
import com.antonov.video.Capture;
import com.antonov.video.MatUtils;
import com.antonov.video.VideoEventListener;

public class Join {

	public static Settings settings;

	public static NetProcessor np;

	public static AcessReportingWriter writer;

	public static void main(String[] args) throws Exception {

		WelcomeWindow welcomeWindow = new WelcomeWindow();
		welcomeWindow.setVisible(true);
		int loadStep = 0;

		np = new NetProcessor();

		welcomeWindow.getProgressBar().setString("Загрузка настроек");
		welcomeWindow.getProgressBar().setValue(loadStep += 20);
		settings = SettingsManager.getInstance().load(new File(Constants.SETTINGS_FILE));

		writer = new AcessReportingWriter();
		writer.connect(new File(settings.getReportPath()));

		welcomeWindow.getProgressBar().setString("Загрузка динамических библиотек");
		welcomeWindow.getProgressBar().setValue(loadStep += 20);
		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		} catch (UnsatisfiedLinkError e) {
			SplashDialogs.showErrorDialog(null, "Ошибка при загрузке динамических библиотек.");
			System.exit(-1);
		}

		welcomeWindow.getProgressBar().setString("Инициализация окон");
		welcomeWindow.getProgressBar().setValue(loadStep += 20);

		MainWindow mainWindow = new MainWindow();

		mainWindow.setScrollPaneEventListener((info) -> {
			
				
		
			if (info != null)
				try {
					writer.addPeopleInfo(info);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else SplashDialogs.showErrorDialog(null, "Данные введены некорректно.");
		});

		welcomeWindow.getProgressBar().setString("Инициализация видеоустройств");
		welcomeWindow.getProgressBar().setValue(loadStep += 20);
		Capture cap = new Capture(settings.getVideoIndex());

		cap.setSize(settings.getVideoW(), settings.getVideoH());

		CascadeClassifier cc = new CascadeClassifier("haarcascade_frontalface_alt.xml");

		
		
		cap.addVideoEventListener(new VideoEventListener() {

			@Override
			public void frameCaptured(Mat frame) {
				long frameStartTime = System.currentTimeMillis();
				BufferedImage img = MatUtils.matToBufferedImage(frame);

				mainWindow.setCameraOutputImage(img);

				MatOfRect faceDetections = new MatOfRect();

				//cc.detectMultiScale(frame, faceDetections, 1.4f, 4, 0, new Size(10, 10), new Size(200, 200));
				cc.detectMultiScale(frame, faceDetections);

				// квадратики
				mainWindow.setDetectedObjects(faceDetections.toArray());

				try {
					BufferedImage[] imgs = new BufferedImage[faceDetections.toArray().length];
					for (int i = 0; i < faceDetections.toArray().length; i++) {
						Rect r = faceDetections.toArray()[i];
						Mat faceImage = frame.submat(r);
						imgs[i] = MatUtils.matToBufferedImage(faceImage);
					}
					mainWindow.setImagesToSelect(imgs);
				} catch (Exception e) {

				}
				float drawTime = System.currentTimeMillis() - frameStartTime;
				float frameRate = 1000f / drawTime;
				mainWindow.setFrameRate(frameRate);
				mainWindow.setDrawTime(drawTime);
			}
		});

		cap.start();

		ActionListener exitListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int var = SplashDialogs.showConfirmDialog(null, "Вы действительно хотите покинуть KiberSecurity?");
				if (var == JOptionPane.YES_OPTION) {
					cap.stop();
					System.exit(0);
				}
			}
		};
		mainWindow.addExitActionListener(exitListener);

		mainWindow.addNetConnectActionListener((event) -> {
			InetSocketAddress addr = SplashDialogs.showNetworkConnectionDialog(mainWindow);
			if (addr == null)
				SplashDialogs.showErrorDialog(null, "Данные введены некорректно");
			try {
				np.connect(addr);
				mainWindow.setNetStatus(true);
			} catch (IOException e1) {
				SplashDialogs.showErrorDialog(mainWindow, "Не удалось подключиться");
				mainWindow.setNetStatus(false);
			}

		});

		mainWindow.addSettingsActionListener((event) -> {
			SettingsWindow settingsWindow = new SettingsWindow();
			settingsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			settingsWindow.setResizable(false);
			settingsWindow.setVisible(true);
			settingsWindow.setSize(700, 460);
		});

		// Если нажата кнопка папки отчетов
		mainWindow.addReportsActionListener((event) -> {
			try {
				Desktop.getDesktop().open(new File(settings.getReportPath()));
			} catch (IOException e1) {
				SplashDialogs.showErrorDialog(null, "Не удалось открыть базу данных");
				e1.printStackTrace();
			}
		});

		mainWindow.getSendButton().addActionListener((event) -> {

			String messageText = mainWindow.getMessageText();
			if (messageText.replaceAll(" ", "") == "")
				return;
			Message message = new Message();
			message.setText(messageText);
			message.setDate(new Date(System.currentTimeMillis()));
			message.setSender(System.getProperty("user.name"));

			System.out.println(message.toString());

			try {
				np.send(message);
			} catch (IOException e) {
				System.out.println("out stream is null");
				return;
			}

			Date date = new Date(System.currentTimeMillis());
			MessagePanel panel = new MessagePanel();
			panel.setMessageText(messageText);
			panel.setName(System.getProperty("user.name"));
			panel.setTime(date.getHours() + ":" + date.getMinutes());
			panel.setFont(new Font("Arial", Font.BOLD, 30));
			panel.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainWindow.addMessage(panel);
			panel.revalidate();
			panel.repaint();

			mainWindow.clearMsg();
			System.out.println("send end");
		});

		np.setOnMessageReceivedListener(new NetProcessor.OnMessageReceiveListener() {

			@Override
			public void messageReceived(Message mess) {
				MessagePanel panel = new MessagePanel();
				Date date = mess.getDate();
				panel.setMessageText(mess.getText());
				panel.setName("server");
				panel.setTime(date.getHours() + ":" + date.getMinutes());
				panel.setFont(new Font("Arial", Font.BOLD, 30));
				panel.setAlignmentX(Component.CENTER_ALIGNMENT);

				mainWindow.addMessage(panel);
				panel.revalidate();
				panel.repaint();
			}
		});

		mainWindow.addAboutActionListener((event) -> {
			AboutWindow window = new AboutWindow();
			window.setVisible(true);
		});

		mainWindow.addFaqActionListener((event) -> {
			FaqWindow window = new FaqWindow();
			window.setVisible(true);
		});


		// GraphicsDevice graphics = GraphicsEnvironment.getLocalGraphicsEnvironment()
		// .getDefaultScreenDevice();

		// graphics.setFullScreenWindow(mainWindow);

		// graphics.setDisplayMode(new DisplayMode(1280, 720, 32, 60));

		welcomeWindow.getProgressBar().setString("Запуск приложения");
		welcomeWindow.getProgressBar().setValue(loadStep += 20);
		Thread.sleep(1000);
		welcomeWindow.setVisible(false);
		welcomeWindow = null;
		
		
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setUndecorated(true);
		mainWindow.setVisible(true);

		SerialProcessor ino = new SerialProcessor(settings.getSerialPort(), settings.getSerialSpeed());

		try {
			ino.openConnection();
		} catch (Exception e) {
			SplashDialogs.showErrorDialog(null, "Не удалось подключиться к электронному замку.\n"
					+ "Возможно, неверно выбран последовательный порт,\n" + "Либо не установлен драйвер CH340.\n"
					+ "Скачать драйвер для Windows, Linux, MacOS: \nhttps://sparks.gogo.co.nz/ch340.html");
		}

		ObjectDetectionControlPanel.yesButton.addActionListener((event) -> {
			try {
			ino.serialWrite("b");
			} catch (Exception e) {
				SplashDialogs.showErrorDialog(null, "Не удалось открыть дверь.");
			}
			

		});

		ObjectDetectionControlPanel.noButton.addActionListener((event) -> {
			try {
				ino.serialWrite("a");
			} catch (Exception e) {
				SplashDialogs.showErrorDialog(null, "Не удалось закрыть дверь.");
			}
			
		});
	}

	

}
