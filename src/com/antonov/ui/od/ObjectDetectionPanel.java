package com.antonov.ui.od;

import java.awt.Color;

import javax.swing.JPanel;

import com.antonov.ui.layouts.VerticalLayout;

public class ObjectDetectionPanel extends JPanel{
	
	private ObjectDetectionViewPanel detectionPanel;
	
	private ObjectDetectionControlPanel controlPanel;
	
	public ObjectDetectionPanel() {
		setLayout(new VerticalLayout());
		
		detectionPanel = new ObjectDetectionViewPanel();
		
		controlPanel = new ObjectDetectionControlPanel();
		
		add(detectionPanel);
		add(controlPanel);
		setBackground(new Color(0x96a3b0));
	}
	
	public ObjectDetectionViewPanel getDetectionPanel() {
		return detectionPanel;
	}

}
