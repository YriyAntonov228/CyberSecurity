package com.antonov.ui.settings.tabs;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.antonov.Constants;
import com.antonov.ui.Helper;
import com.antonov.ui.layouts.VerticalLayout;


public class VideoSettingsTab extends JPanel {

	private Font defaultFont = new Font("Arial", Font.BOLD, 20);

	public JComboBox<String> resolutionComboBox = new JComboBox<String>(
			new String[] { "240 * 360", "640 * 480", "1280 * 720" });
	
	public JTextField deviceIndexField = new JTextField("0",3);
	
	public JSlider fpsSlider = new JSlider(0, 60);
	public JLabel fpsInfoLabel = new JLabel();

	public VideoSettingsTab() {
		setLayout(new VerticalLayout());
		setBackground(Constants.DEFAULT_SETTINGS_COLOR);
		
		deviceIndexField.setFont(defaultFont);
		fpsSlider.setMajorTickSpacing(10);
		fpsSlider.setSnapToTicks(true);
		fpsSlider.setPaintTicks(true);
		fpsSlider.setBackground(getBackground());
		
		JLabel l1 = new JLabel("Разрешение: ");
		l1.setFont(defaultFont);
		JLabel l2 = new JLabel("Индекс: ");
		l2.setFont(defaultFont);
		
		
		
		add(Helper.getWrapped(l1, resolutionComboBox));
		add(Helper.getWrapped(l2, deviceIndexField));
	}

	
}
