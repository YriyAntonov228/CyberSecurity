package com.antonov.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class WelcomeWindow extends JFrame{
	
	private JProgressBar progressBar;
	
	private BufferedImage image;
	
	public WelcomeWindow() {
		setSize(calculateBestWindowSize());
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		 addComponentListener(new ComponentAdapter() {
	          
	            @Override
	            public void componentResized(ComponentEvent e) {
	                setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 100, 100));
	            }
	        });
		 setUndecorated(true);
		 
		progressBar = createProgressBar();
		try {
			image = ImageIO.read(new File("img\\icons\\logo.png"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ImagePanel imagePanel = new ImagePanel(image);
		
		add(imagePanel, BorderLayout.CENTER);
		add(progressBar, BorderLayout.SOUTH);
	}
	
	private JProgressBar createProgressBar() {
		JProgressBar bar = new JProgressBar();
		bar.setStringPainted(true);
		bar.setFont(new Font("Arial", Font.BOLD, 25));
		
		
		return bar;
	}
	
	
	private Dimension calculateBestWindowSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension(screenSize.width / 3, screenSize.width / 3);
	}


	public JProgressBar getProgressBar() {
		return progressBar;
	}




	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}
	
	
	public class ImagePanel extends JPanel{
		
		private BufferedImage image;
		
		public ImagePanel(BufferedImage image) {
			this.image = image;
		}
		public void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
