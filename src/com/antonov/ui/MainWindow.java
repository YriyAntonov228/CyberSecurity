package com.antonov.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.opencv.core.Rect;

import com.antonov.ui.chat.ChatPanel;
import com.antonov.ui.chat.MessagePanel;
import com.antonov.ui.od.ObjectDetectionPanel;
import com.antonov.ui.os.FaceEventListener;
import com.antonov.ui.os.ObjectSelectionPanel;
import com.antonov.ui.view.DateTimePanel;

public class MainWindow extends JFrame {

	private JPanel panel1, panel2, panel3;

	private ChatPanel chatPanel;

	public ObjectDetectionPanel detectionViewPanel;

	public ObjectSelectionPanel selectionPanel;
	
	private NetStatusPanel netStatusPanel;

	JMenuBar optionsBar = new JMenuBar();
	JMenu menu = new JMenu("Опции");

	JMenu menu2 = new JMenu("Помощь");

	JMenuItem[] items1 = new JMenuItem[] { new JMenuItem("Открыть базу данных"), new JMenuItem("Настройки"),
			new JMenuItem("Подключиться к сети"), new JMenuItem("Выход") };
	JMenuItem[] items2 = new JMenuItem[] { new JMenuItem("Справка"), new JMenuItem("Об авторе") };

	public MainWindow() {

		configWindow();

		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new BorderLayout());

		setDefaultColor(panel1);
		setDefaultColor(panel2);
		setDefaultColor(panel3);

		// test
		chatPanel = new ChatPanel();
		detectionViewPanel = new ObjectDetectionPanel();
		selectionPanel = new ObjectSelectionPanel();
		// test
		
		netStatusPanel = new NetStatusPanel();

		setDefaultColor(selectionPanel);
		setDefaultColor(netStatusPanel);

		DateTimePanel dateTimePanel = new DateTimePanel();

		try {
			BufferedImage trayIcon = ImageIO.read(new File("img\\icons\\tray_icon.png"));
			this.setIconImage(trayIcon);
		} catch (Exception e) {
			System.err.println("eeeeerrrroooorrrr");
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					dateTimePanel.setDate(new Date(System.currentTimeMillis()));
					try {
						Thread.sleep(10000);
					} catch (Exception e) {

					}
				}
			}
		}).start();

		JPanel p11 = new JPanel(new BorderLayout());
		p11.setPreferredSize(new Dimension(420, 350));
		p11.add(chatPanel, BorderLayout.CENTER);
		panel2.add(p11);

		panel2.add(detectionViewPanel);
		panel2.add(selectionPanel);
		// add(dateTimePanel);

		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		//////////////////////////////////////////

		p1.add(dateTimePanel);
		p1.add(netStatusPanel);

		setDefaultColor(p1);
		setDefaultColor(p2);
		///////////////////////////////////////////

		panel3.add(p1, BorderLayout.LINE_END);
		panel3.add(p2, BorderLayout.LINE_START);

		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);

		for (int i = 0; i < items1.length; i++) {
			menu.add(items1[i]);
		}

		for (int i = 0; i < items2.length; i++) {
			menu2.add(items2[i]);
		}

		optionsBar.add(menu);
		optionsBar.add(new JLabel(" | "));
		optionsBar.add(menu2);

		this.setJMenuBar(optionsBar);
		setNetStatus(false);
	}

	public void addReportsActionListener(ActionListener listener) {
		items1[0].addActionListener(listener);
	}

	public void addNetConnectActionListener(ActionListener listener) {
		items1[items1.length - 2].addActionListener(listener);
	}
	
	public void addExitActionListener(ActionListener listener) {
		items1[items1.length - 1].addActionListener(listener);
	}

	public void addSettingsActionListener(ActionListener listener) {
		items1[1].addActionListener(listener);
	}
	
	public void addAboutActionListener(ActionListener listener) {
		items2[1].addActionListener(listener);
	}
	
	public void addFaqActionListener(ActionListener listener) {
		items2[0].addActionListener(listener);
	}

	private void configWindow() {
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setUndecorated(true);
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}

	private void setDefaultColor(JPanel panel) {
		panel.setBackground(new Color(0x96a3b0));
	}
	
	//  ==============NET   STATUS  =============
	public void setNetStatus(boolean status) {
		netStatusPanel.setStatus(status);
		setChatEnabled(status);
	}

	// ==================== ЕБАЛЬНИК РЕКОГНИШН СЕКШН
	// ==================================
	// квадраты которые обводят лица
	public void setDetectedObjects(Rect[] rects) {
		detectionViewPanel.getDetectionPanel().setObjects(rects);
	}

	// вью порт камеры -_-
	public void setCameraOutputImage(BufferedImage image) {
		detectionViewPanel.getDetectionPanel().setImage(image);
		detectionViewPanel.getDetectionPanel().repaint();
		detectionViewPanel.getDetectionPanel().revalidate();
	}

	// выбор лиц
	public void setImagesToSelect(BufferedImage[] images) {
		selectionPanel.setImages(images);
	}

	// ================== делегация методов из чата ===============================

	public void addMessage(MessagePanel panel) {
		chatPanel.addMessage(panel);
		chatPanel.revalidate();
		chatPanel.repaint();
		panel.revalidate();
		panel.repaint();
		revalidate();
		repaint();
	}

	public JButton getSendButton() {
		return chatPanel.getSendButton();
	}

	public String getMessageText() {
		return chatPanel.getMessageText();
	}

	public void clearMsg() {
		chatPanel.clearMsg();
	}

	public void setChatEnabled(boolean enabled) {
		chatPanel.setEnabled(enabled);
	}
	
	public void setScrollPaneEventListener(FaceEventListener listener) {
		selectionPanel.getObjectScrollPane().setFaceEventListener(listener);
	}
	
	public void setFrameRate(float frameRate) {
		detectionViewPanel.getDetectionPanel().setFrameRate(frameRate);
	}
	
	public void setDrawTime(float drawTime) {
		detectionViewPanel.getDetectionPanel().setDrawTime(drawTime);
	}
}
