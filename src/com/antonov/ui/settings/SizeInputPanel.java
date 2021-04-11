package com.antonov.ui.settings;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.opencv.core.Size;

public class SizeInputPanel extends JPanel {
	
	private JTextField widthTextField = new JTextField(3);
	
	private JTextField heightTextField = new JTextField(3);
	
	public SizeInputPanel() {
		setLayout(new FlowLayout());
		
		add(widthTextField);
		add(new JLabel(" x "));
		add(heightTextField);
	}
	
	public Size getInputSize() {
		int width = Integer.parseInt(widthTextField.getText().replaceAll(" ", ""));
		int height = Integer.parseInt(heightTextField.getText().replaceAll(" ", ""));
		
		return new Size(width, height);
	}
	
	public void setInputSize(Size size) {
		widthTextField.setText((int)size.width + "");
		heightTextField.setText((int)size.height + "");
	}
}