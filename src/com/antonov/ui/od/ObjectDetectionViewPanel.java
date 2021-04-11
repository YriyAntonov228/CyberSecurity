package com.antonov.ui.od;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.opencv.core.Rect;

public class ObjectDetectionViewPanel extends JPanel {
	
	private BufferedImage image;
	
	private Rect[] objects;
	
	private boolean paintObjectsFlag = true;
	
	private float frameRate = -1;
	
	private float drawTime = -1;
	
	public ObjectDetectionViewPanel() {
		
		setBackground(new Color(0x000000));
		setPreferredSize(new Dimension(500,300)); 
		//setPreferredSize(new Dimension(640,480));
		
	}
	
	public void paintComponent(Graphics g) {
		if(image != null) {
			long frameStartTime = System.currentTimeMillis();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			
			// draw fps and ms
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			
			g.drawString((int)frameRate + " FPS, " + drawTime + " ms", 10, 25);
		if(paintObjectsFlag && objects != null) {
			
			for(int i = 0; i < objects.length;i++) {
				
				Rect currObject = recalculate(objects[i], image.getWidth(), image.getHeight(), getWidth(), getHeight());
				
				selectObject(g, currObject, image);
				
			}
		}
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.BLUE);
			g.drawString("No input signal", getWidth() / 4, getHeight() / 2);
			
			
		}
		
	}
	
	private void selectObject(Graphics g, Rect rect, BufferedImage img) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.BLUE);
		g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		
	}
	
	private Rect recalculate(Rect input, int sourceWidth, int sourceHeight, int outputWidth, int outputHeight) {
		Rect rect = new Rect();
		
		float mW  = (float)outputWidth / (float)sourceWidth;
		float mH  = (float)outputHeight / (float)sourceHeight;
		
	
		rect.width = (int)(input.width * mW);
		rect.height = (int)(input.height * mH);
		rect.x = (int)(input.x * mW);
		rect.y = (int)(input.y * mH);
		
		return rect;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setObjects(Rect[] objects) {
		this.objects = objects;
	}
	
	public Rect[] getObjects() {
		return objects;
	}

	public void setFrameRate(float frameRate) {
		this.frameRate = frameRate;
	}

	public void setDrawTime(float drawTime) {
		this.drawTime = drawTime;
	}
	
	
	

}
