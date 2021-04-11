package com.antonov;

import com.antonov.net.NetProcessor;
import com.antonov.settings.Settings;
import com.antonov.ui.MainWindow;

public class CyberSecurity {

	private Settings settings;
	
	private MainWindow mainWindow;
	
	private NetProcessor netProcessor;
	
	
	public CyberSecurity() {
		mainWindow = new MainWindow();
		
		// Если нажата кнопка настроек
			mainWindow.addSettingsActionListener((event) -> {
					
			});

		// Если нажата кнопка папки отчетов
			mainWindow.addReportsActionListener((event) -> {

			});
				
		// Если нажата кнопка подключения к сети
			mainWindow.addNetConnectActionListener((event) -> {

			});
			
		// Если нажата кнопка выхода
			mainWindow.addExitActionListener((event) -> {
				
			});
			// если щелкнули на лицо
			mainWindow.setScrollPaneEventListener((info) -> {
				System.out.println(info == null ? "info is null" : info.toString());
			});
	}
	
}
