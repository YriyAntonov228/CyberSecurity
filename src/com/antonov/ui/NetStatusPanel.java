package com.antonov.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NetStatusPanel extends JPanel{
	
	private BufferedImage image;
	private boolean status = false;
	
	public NetStatusPanel() {
		try {
			image = ImageIO.read(new File("img\\icons\\net_connection.png"));
		} catch (Exception e) {
			System.err.println("Could not load image");
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(50,50));
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, 0, 0, null);
		if(!status) {
			g2d.setStroke(new BasicStroke(7));
			g2d.setColor(Color.RED);
			g2d.drawLine(0, 0, getWidth(), getHeight());
			g2d.drawLine(getWidth(), 0, 0, getHeight());
		}
	}
	
	
	public void setStatus(boolean status) {
		this.status = status;
		this.repaint();
	}
}
