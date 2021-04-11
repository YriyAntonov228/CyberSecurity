package com.antonov.ui.settings.tabs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.antonov.Constants;
import com.antonov.ui.Helper;
import com.antonov.ui.layouts.VerticalLayout;

public class ReportingSettingsTab extends JPanel{


		
		private Font defaultFont = new Font("Arial", Font.BOLD, 25);
		
		public JTextField pathField;
		
		public ReportingSettingsTab() {
			setLayout(new VerticalLayout());
			setBackground(Constants.DEFAULT_SETTINGS_COLOR);
			
			JLabel l1 = new JLabel("Файл базы данных: ");
			pathField = new JTextField(8);
			pathField.setText("reports");
			pathField.setFont(defaultFont);
			
			add(Helper.getWrapped(l1, pathField));
		}
		
	}


