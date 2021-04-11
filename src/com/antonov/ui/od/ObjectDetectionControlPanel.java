package com.antonov.ui.od;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ObjectDetectionControlPanel extends JPanel{
	
	public static JButton yesButton;
	
	public static JButton noButton;
	
	public ObjectDetectionControlPanel() {
		setLayout(new FlowLayout());
		
		yesButton = createButton("Открыть");
		
		noButton = createButton("Закрыть");
		
		add(noButton);
		add(yesButton);
		setBackground(new Color(0x96a3b0));
	}
	
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
			button.setFont(new Font("Arial", Font.BOLD, 20));
		return button;
	}

}
