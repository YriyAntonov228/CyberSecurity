package com.antonov.ui.settings.tabs;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.antonov.Constants;
import com.antonov.ui.Helper;
import com.antonov.ui.layouts.VerticalLayout;

public class SerialSettingsTab extends JPanel {

	private Font defaultFont = new Font("Arial", Font.BOLD, 20);

	public JTextField baudRateTextField = new JTextField(5);
	public JTextField portTextField = new JTextField(5);

	public SerialSettingsTab() {
		setLayout(new VerticalLayout());
		setBackground(Constants.DEFAULT_SETTINGS_COLOR);

		portTextField.setFont(defaultFont);
		baudRateTextField.setFont(defaultFont);

		JLabel l1 = new JLabel("Скорость(бод): ");
		l1.setFont(defaultFont);
		JLabel l2 = new JLabel("Порт: ");
		l2.setFont(defaultFont);
		

		add(Helper.getWrapped(l1, baudRateTextField));
		add(Helper.getWrapped(l2, portTextField));
	}

	

}
