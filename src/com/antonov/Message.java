package com.antonov;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date date;
	
	private String text;
	
	private String sender;

	
	
	public Date getDate() {
		return date;
	}

	public Message setDate(Date date) {
		this.date = date;
		return this;
	}

	public String getText() {
		return text;
	}

	public Message setText(String text) {
		this.text = text;
		return this;
	}

	public String getSender() {
		return sender;
	}

	public Message setSender(String sender) {
		this.sender = sender;
		return this;
	}
	
	@Override
	public String toString() {
		return  "text: " + text + "\n" + "sender: " + sender + "\n" + date.toString();
	}
	
}
