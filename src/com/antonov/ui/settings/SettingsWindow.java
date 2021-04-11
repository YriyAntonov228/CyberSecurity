package com.antonov.ui.settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.antonov.Constants;
import com.antonov.settings.Settings;
import com.antonov.settings.SettingsManager;
import com.antonov.ui.SplashDialogs;
import com.antonov.ui.settings.tabs.ReportingSettingsTab;
import com.antonov.ui.settings.tabs.SerialSettingsTab;
import com.antonov.ui.settings.tabs.VideoSettingsTab;

public class SettingsWindow extends JFrame {

	private Settings settings = new Settings();

	private JTabbedPane tabbedPane;

	private JButton saveButton = new JButton("Сохранить");

	private JButton cancelButton = new JButton("Отмена");

	private JLabel label = new JLabel("Примечание. Некоторые настройки будут доступны тольно после перезапуска.");

	private SerialSettingsTab co = new SerialSettingsTab();
	private VideoSettingsTab vi = new VideoSettingsTab();
	private ReportingSettingsTab rt = new ReportingSettingsTab();
	
	public SettingsWindow() {
		
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 20));

		cancelButton.addActionListener((event) -> {
			this.setVisible(false);
			this.dispose();
		});

		JPanel downPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel wrap = new JPanel(new FlowLayout());

		downPanel.setBackground(Constants.DEFAULT_SETTINGS_COLOR);
		wrap.setBackground(Constants.DEFAULT_SETTINGS_COLOR);

		getTabbedPane().addTab("Serial", co);
		getTabbedPane().addTab("Видео", vi);
		getTabbedPane().addTab("Отчеты", rt);

		add(downPanel, BorderLayout.SOUTH);
		add(tabbedPane, BorderLayout.NORTH);
		wrap.add(label);
		wrap.add(saveButton);
		wrap.add(cancelButton);
		downPanel.add(wrap);
		
		updateUi();
		
		saveButton.addActionListener((event) -> {
				updateSettings();
			SettingsManager manager = SettingsManager.getInstance();
			try {
				manager.save(new File(Constants.SETTINGS_FILE), settings);
				
				int answer = SplashDialogs.showConfirmDialog(null, "Некоторые настройки вступят в\n"
											+ "силу только после перезагрузки. \n"
											+ "Хотите перезапустить приложение?");
				if(answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				setVisible(false); 
				dispose();
				
			} catch (IOException e) {
					SplashDialogs.showErrorDialog(null, "Ошибка при сохранении настроек");
					e.printStackTrace();
			}
		});
		
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JButton getSaveButton() {
		return saveButton;
	}


	private void updateSettings() {
		// serial
		settings.setSerialPort(co.portTextField.getText());
		settings.setSerialSpeed(Integer.parseInt(co.baudRateTextField.getText().replaceAll(" ", "")));
		
		// video
		StringTokenizer strtok = new StringTokenizer(((String)vi.resolutionComboBox.getSelectedItem()).replaceAll(" ", ""), "*");
		settings.setVideoResolution(Integer.parseInt(strtok.nextToken()), Integer.parseInt(strtok.nextToken()));
		settings.setVideoIndex(Integer.parseInt(vi.deviceIndexField.getText()));
		settings.setFps(vi.fpsSlider.getValue());
		
		// report
		settings.setReportPath(rt.pathField.getText());
		
		
	}
	
	public void updateUi() {
		// serial
		
		SettingsManager settingsManager = SettingsManager.getInstance();
		
		try {
			settings = settingsManager.load(new File(Constants.SETTINGS_FILE));
		} catch (ClassNotFoundException e) {
			SplashDialogs.showErrorDialog(null, "Ошибка сериализации");
			e.printStackTrace();
		} catch (IOException e) {
			SplashDialogs.showErrorDialog(null, "Ошибка чтения файла настроек");
			e.printStackTrace();
		}
		
		
		
		// serial
		co.baudRateTextField.setText(settings.getSerialSpeed() + "");
		co.portTextField.setText(settings.getSerialPort());
		
		
		// video
		vi.resolutionComboBox.setSelectedItem(settings.getVideoW() + " * " + settings.getVideoH());
		vi.deviceIndexField.setText(settings.getVideoIndex() + "");
		vi.fpsSlider.setValue(settings.getFps());
		
		// report
		rt.pathField.setText(settings.getReportPath());
		
	}

}
