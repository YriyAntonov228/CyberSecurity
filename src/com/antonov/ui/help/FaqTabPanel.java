package com.antonov.ui.help;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.antonov.ui.layouts.VerticalLayout;

public class FaqTabPanel extends JPanel{

	private JTextArea textArea = new JTextArea(5, 31);
	private BufferedImage image;
	
	public FaqTabPanel() {
		setLayout(new VerticalLayout());
		JPanel imagePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				if(image != null)
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		textArea.setEditable(false);
		imagePanel.setPreferredSize(new Dimension(675, 400));
	  textArea.setFont(new Font("Arial", Font.PLAIN, 25));
	  add(imagePanel);
	  add(textArea);
	}

	
	
	public JTextArea getTextArea() {
		return textArea;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
}
