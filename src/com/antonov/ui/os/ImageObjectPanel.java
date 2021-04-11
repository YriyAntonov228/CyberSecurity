package com.antonov.ui.os;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageObjectPanel extends JPanel{
	
	private BufferedImage image;
	
	
	
	public ImageObjectPanel(BufferedImage image) {
		this.image = image;
		setPreferredSize(new Dimension(120,120));
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
