package com.antonov.ui.chat;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DownPanel extends JPanel {
	
	private JTextField messageField = new JTextField(20);
	
	private JButton sendButton = new JButton("send");
	
	public DownPanel() {
		setLayout(new FlowLayout());
		messageField.setFont(new Font("Arial", Font.PLAIN, 20 ));
		add(messageField);
		add(sendButton);
	}
	
	public JButton getSendButton() {
		return sendButton;
	}
	public String getMessageText() {
		return messageField.getText();
	}
	public void clear() {
		messageField.setText("");
	}
	
	public void setEnabled(boolean enabled) {
		messageField.setEnabled(enabled);
		sendButton.setEnabled(enabled);
	}
}
