package com.antonov.ui.help;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.antonov.ui.layouts.VerticalLayout;

public class AboutWindow extends JFrame{
	
	public AboutWindow() {
		setLayout(new VerticalLayout());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(250,100);
		setResizable(false);
		
		JLabel l1 = new JLabel("Автор: Антонов Юрий Александрович");
		JLabel l2 = new JLabel("Связь: ");
		JLabel l3 = new JLabel("<html><a href=''>https://linktr.ee/antonov_yriy</a>");
		l3.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://linktr.ee/4ertila"));
				} catch (Exception ex) {
					// TODO: handle exception
				}
				super.mouseClicked(e);
			}
		});
		
		add(l1);
		JPanel wrap = new JPanel(new FlowLayout());
		wrap.add(l2);
		wrap.add(l3);
		add(wrap);
		
	}
}
