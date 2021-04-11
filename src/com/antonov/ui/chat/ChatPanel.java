package com.antonov.ui.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.antonov.ui.layouts.VerticalLayout;

public class ChatPanel extends JPanel{

	private DownPanel downPanel = new DownPanel();
	
	// ===========================================
	private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
													 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JPanel contentPanel = new JPanel();
	
	// ===========================================
	public ChatPanel() {
		setLayout(new BorderLayout());
		
		
		contentPanel.setLayout(new VerticalLayout());
		
		
		scrollPane.setViewportView(contentPanel);
		
		add(scrollPane, BorderLayout.CENTER);
		add(downPanel, BorderLayout.SOUTH);
	}
	
	
	
	
	public void addMessage(MessagePanel panel) {
		contentPanel.add(panel);
		JScrollBar sb = scrollPane.getVerticalScrollBar();
		sb.setValue( sb.getMaximum() );
	}
	
	public JButton getSendButton() {
		return downPanel.getSendButton();
	}
	
	public String getMessageText() {
		return downPanel.getMessageText();
	}
	
	public void clearMsg() {
		downPanel.clear();
	}
	
	public void setEnabled(boolean enabled) {
		scrollPane.getHorizontalScrollBar().setEnabled(enabled);
		scrollPane.getVerticalScrollBar().setEnabled(enabled);
		scrollPane.getViewport().getView().setEnabled(enabled);
		downPanel.setEnabled(enabled);
		if(enabled) {
			contentPanel.removeAll();
			contentPanel.repaint();
		} else {
			JLabel label1 = new JLabel("Нет соединения с сервером.");
			JLabel label2 = new JLabel("Чат недоступен.");
			label1.setFont(new Font("Arial", Font.BOLD, 25));
			label1.setForeground(Color.RED);
			label2.setFont(new Font("Arial", Font.BOLD, 25));
			label2.setForeground(Color.RED);
			contentPanel.add(label1);
			contentPanel.add(label2);
		}
	}
	
}
