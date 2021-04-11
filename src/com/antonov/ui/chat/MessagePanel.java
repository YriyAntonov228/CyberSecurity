package com.antonov.ui.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.antonov.ui.layouts.VerticalLayout;

public class MessagePanel extends JPanel{

	private JTextArea messageArea = new JTextArea();
	
	private JPanel wrap = new JPanel(new VerticalLayout());
	
	private JLabel nameLabel = new JLabel("name..");
	private JLabel timeLabel = new JLabel("12:00"); 
	
	public MessagePanel() {
		
		setLayout(new BorderLayout());
		messageArea.setBackground(new Color(0x6995db));
		messageArea.setFont(new Font("Arial", Font.BOLD, 20));
		messageArea.setEditable(false);
		
		wrap.setBackground(new Color(0x90abd6));
		
		wrap.add(nameLabel);
		wrap.add(timeLabel);
	
		
		add(wrap, BorderLayout.LINE_START);
		add(messageArea, BorderLayout.CENTER);	
	}
	
	
	public void setMessageText(String message) {
		messageArea.setText(" " + message + "  ");
	}
	
	public void setName(String name) {
		nameLabel.setText(name + "  ");
	}
	
	public void setTime(String time) {
		timeLabel.setText(time + "  ");
	}
}
