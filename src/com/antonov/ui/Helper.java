package com.antonov.ui;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.antonov.Constants;

public class Helper {
	public static JPanel getWrapped(JComponent... components) {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(Constants.DEFAULT_SETTINGS_COLOR);
		for (int i = 0; i < components.length; i++) {
			panel.add(components[i]);
		}
		return panel;
	}
}
