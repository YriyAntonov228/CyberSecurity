package com.antonov.ui.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.antonov.date.DateConverter;

public class DateTimePanel extends JPanel{
	
	private Date date;
	
	private JTextArea textArea;
	
	public DateTimePanel() {
		setLayout(new FlowLayout());
		
		textArea = new JTextArea();
		textArea.setRows(1);
		textArea.setColumns(5);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.BOLD, 25));
		
		textArea.setBackground(new Color(238, 238, 238));
		
		
		textArea.setBackground(new Color(0x96a3b0));
		add(textArea);
		setBackground(new Color(0x96a3b0));
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		StringBuilder builder = new StringBuilder("");
		builder.append(DateConverter.getDayOfWeekName(date.getDay(), DateConverter.LOCALE_RUS));
		builder.append(", ");
		builder.append(date.getDate());
		builder.append(" ");
		builder.append(DateConverter.getMonthName(date.getMonth(), DateConverter.LOCALE_RUS));
		builder.append(" || ");
		builder.append(date.getHours());
		builder.append(":");
		builder.append(date.getMinutes() < 10 ? ("0" + date.getMinutes()) : date.getMinutes());
		
		textArea.setText(builder.toString());
		
	}
	
	
	
}
