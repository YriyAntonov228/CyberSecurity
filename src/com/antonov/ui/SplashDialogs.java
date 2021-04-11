package com.antonov.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.antonov.Constants;
import com.antonov.PeopleInfo;
import com.antonov.ui.layouts.VerticalLayout;

public class SplashDialogs {

	private static BufferedImage errorImage;
	private static BufferedImage questionImage;

	// static initializer
	static {
		try {
			errorImage = ImageIO.read(new File("img\\icons\\error.jpg"));
			questionImage = ImageIO.read(new File("img\\icons\\question.png"));
		} catch (IOException e) {
			System.err.println("SplashDialogs: could not upload image.");
			e.printStackTrace();
		}
	}

	public static void showErrorDialog(Component component, String errorText) {
		JPanel contentPanel = new JPanel(new BorderLayout());
		JTextArea textField = new JTextArea(2, 15);
		JPanel imagePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(errorImage, 0, 0, getWidth(), getHeight(), this);
			}
		};

		imagePanel.setPreferredSize(new Dimension(400, 400));
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		textField.setForeground(Color.BLUE);
		textField.setEditable(false);
		textField.setText(errorText);
		textField.setBackground(contentPanel.getBackground());

		contentPanel.add(imagePanel, BorderLayout.CENTER);
		contentPanel.add(textField, BorderLayout.SOUTH);

		JOptionPane.showMessageDialog(component, contentPanel, "Error!", JOptionPane.PLAIN_MESSAGE);
	}

	public static int showConfirmDialog(Component component, String questionText) {
		JPanel contentPanel = new JPanel(new BorderLayout());
		JTextArea textField = new JTextArea(2, 16);
		JPanel imagePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(questionImage, 0, 0, getWidth(), getHeight(), this);
			}
		};

		imagePanel.setPreferredSize(new Dimension(400, 400));
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		textField.setForeground(Color.BLUE);
		textField.setEditable(false);
		textField.setText(questionText);
		textField.setBackground(contentPanel.getBackground());

		contentPanel.add(imagePanel, BorderLayout.CENTER);
		contentPanel.add(textField, BorderLayout.SOUTH);

		return JOptionPane.showConfirmDialog(component, contentPanel, "title", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null);
	}

	public static InetSocketAddress showNetworkConnectionDialog(Component component) {
		JPanel contentPanel = new JPanel(new BorderLayout());
		JPanel wrap = new JPanel(new VerticalLayout());
		JTextField ipTextField = new JTextField(7);
		JTextField portTextField = new JTextField(4);
		JPanel ipWrap = new JPanel(new FlowLayout());
		JPanel portWrap = new JPanel(new FlowLayout());

		wrap.setPreferredSize(new Dimension(100, 100));

		ipTextField.setFont(new Font("Arial", Font.BOLD, 18));
		portTextField.setFont(new Font("Arial", Font.BOLD, 18));

		ipWrap.add(new JLabel("IP:    "));
		ipWrap.add(ipTextField);

		portWrap.add(new JLabel("Port: "));
		portWrap.add(portTextField);

		wrap.add(ipWrap);
		wrap.add(portWrap);

		contentPanel.add(wrap, BorderLayout.CENTER);

		// проверка на корректность.....
		portTextField.addCaretListener((event) -> {
			String text = portTextField.getText().replaceAll(" ", "");
			if(checkNumber(text))
				portTextField.setBackground(Color.GREEN);
			else
				portTextField.setBackground(Color.RED);
		});

		ipTextField.addCaretListener((event) -> {
			String text = ipTextField.getText().replaceAll(" ", "");
			if(checkIp(text))
				ipTextField.setBackground(Color.GREEN);
			else
				ipTextField.setBackground(Color.RED);
			
		});
		// == = == == = = = == = = = = == =
		int answer = JOptionPane.showConfirmDialog(component, contentPanel, "Подключение к сети", 
																		JOptionPane.YES_NO_OPTION,
																		JOptionPane.PLAIN_MESSAGE, null);
		
		
		if(answer == JOptionPane.YES_OPTION && 
				checkIp(ipTextField.getText()) &&
				checkNumber(portTextField.getText()))
			
			return new InetSocketAddress(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
			
		return null;
	}
	
	
	private static boolean checkNumber(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private static boolean checkIp(String ip) {
		if(ip.equals("localhost"))
			return true;
		StringTokenizer strTok = new StringTokenizer(ip, ".");

		if (strTok.countTokens() != 4)
			return false;
	
		for (int i = 0; i < 4; i++) {
			String tok = strTok.nextToken();
			try {
				Integer.parseInt(tok);
			} catch (NumberFormatException e) {	
				return false;
			}
		}
		return true;
	}
	
	
	public static PeopleInfo showPeopleInfoInputDialog(Component component) {
		JPanel contentPanel = new JPanel(new BorderLayout());
		JPanel wrap = new JPanel(new VerticalLayout());
		
		JTextField nameTextField = new JTextField(8);
		JTextField surnameTextField = new JTextField(8);
		JTextField patronymicTextField = new JTextField(8);
		JComboBox<String> isEntryCompoBox = new JComboBox<String>(new String[] {"Вошел", "Вышел"});
		JCheckBox isGuestCheckBox = new JCheckBox();
		
		nameTextField.setFont(new Font("Arial", Font.BOLD, 20));
		surnameTextField.setFont(new Font("Arial", Font.BOLD, 20));
		patronymicTextField.setFont(new Font("Arial", Font.BOLD, 20));
		isGuestCheckBox.setBackground(Constants.DEFAULT_SETTINGS_COLOR);
		wrap.setBackground(Constants.DEFAULT_SETTINGS_COLOR);
		wrap.setPreferredSize(new Dimension(170, 200));
		wrap.add(Helper.getWrapped(new JLabel("Имя:          "), nameTextField));
		wrap.add(Helper.getWrapped(new JLabel("Фамилия:"), surnameTextField));
		wrap.add(Helper.getWrapped(new JLabel("Отчество:"), patronymicTextField));
		wrap.add(Helper.getWrapped(new JLabel("Действие:          "), isEntryCompoBox));
		wrap.add(Helper.getWrapped(new JLabel("Является гостем:     "), isGuestCheckBox));
		
		contentPanel.add(wrap, BorderLayout.CENTER);
		
		
		int answer = JOptionPane.showConfirmDialog(component, contentPanel, "Введите данные", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null);
		
		
		
		PeopleInfo info = new PeopleInfo();
		info.setName(nameTextField.getText());
		info.setSurname(surnameTextField.getText());
		info.setPatronymic(patronymicTextField.getText());
		info.setEntry(isEntryCompoBox.getSelectedIndex() == 0 ? true : false);
		info.setGuest(isGuestCheckBox.isSelected());
		
		if(info.getName().replaceAll(" " , "").equals("") 
				   || info.getSurname().replaceAll(" " , "").equals("") 
				   || info.getPatronymic().replaceAll(" " , "").equals(""))
					return null;
		
		if(answer == JOptionPane.YES_OPTION) {
			return info;
		}
		
		return null;
	}
	

}
