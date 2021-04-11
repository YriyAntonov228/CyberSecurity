package com.antonov.ui.help;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class FaqWindow extends JFrame{
	
	private FaqTabPanel mainWindowTabPanel = new FaqTabPanel();
	private FaqTabPanel netTabPanel = new FaqTabPanel();
	private FaqTabPanel addFormTabPanel = new FaqTabPanel();
	
	public FaqWindow() {
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(900,700);
		setResizable(false);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		
		
		try {
			mainWindowTabPanel.setImage(ImageIO.read(new File("img/mainwin_faq.png")));
			netTabPanel.setImage(ImageIO.read(new File("img/net_faq.png")));
			addFormTabPanel.setImage(ImageIO.read(new File("img//db_faq.png")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			mainWindowTabPanel.getTextArea().setText(
					"1 – Панель чата для связи с персоналом (Как настроить \nсм. во вкладке «сеть»)\r\n" + 
					"2-  Кнопки управления электронным замком \r\n" + 
					"3- Панель отображения видео с камеры \r\n" + 
					"4- Панель для добавления лиц в БД (Для добавления\n нажать на лицо)\r\n" + 
					"");
			netTabPanel.getTextArea().setText(
					"Что бы подключиться к сети пользователю необходимо\n выбрать"
					+ " в меню «Опции» раздел «Подключиться к сети»\n (Рисунок 1)"
					+ " далее во всплывающем окне (Рисунок 2)\nнужно ввести следующие данные:\n" + 
					"В поле ввода IP: Необходимо ввести IP адрес сервера \r\n" + 
					"В поле ввода Port: Необходимо ввести порт сервера\nзаданный администратором\r\n" + 
					"Далее после проделанных выше операций нужно нажать\nкнопку «Yes»\r\n" + 
					"");
			
			addFormTabPanel.getTextArea().setText("В данном окне для занесения в БД данных\n"
					+ "о госте пользователю требуется ввести данные\n"
					+ "во все поля ввода после чего нажать на кнопку «Yes».");
			tabbedPane.addTab("<html><h2>Главное окно</h2>", mainWindowTabPanel);
			tabbedPane.addTab("<html><h2>Сеть</h2>", netTabPanel);
			tabbedPane.addTab("<html><h2>Добавление в базу</h2>", addFormTabPanel);
		
		
		
		add(tabbedPane);
	}
}
