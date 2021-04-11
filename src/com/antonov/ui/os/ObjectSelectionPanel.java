package com.antonov.ui.os;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


// basic panel
public class ObjectSelectionPanel extends JPanel{
	
	private ObjectScrollPane scrollPane;
	
	public ObjectSelectionPanel() {
		
		setLayout(new BorderLayout());
		scrollPane = new ObjectScrollPane();
		
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	
	public void setImages(BufferedImage[] imageArr) {
		scrollPane.setImages(imageArr);
	}
	
	public ObjectScrollPane getObjectScrollPane() {
		return scrollPane;
	}
	
	
 
}
