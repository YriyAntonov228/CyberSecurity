package com.antonov.ui.os;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.antonov.Constants;
import com.antonov.PeopleInfo;
import com.antonov.ui.SplashDialogs;

public class ObjectScrollPane extends JScrollPane{
	

	private JPanel contentPanel;
	
	private ArrayList<MouseListener> listeners = new ArrayList<MouseListener>();
	
	private  FaceEventListener listener;
	
	
	public ObjectScrollPane() {
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		
		contentPanel = new JPanel(new FlowLayout());
		
		setViewportView(contentPanel);
		
		setPreferredSize(new Dimension(500,140));
		
		
		
	}
	
	

	public void setImages(BufferedImage[] img) {
		contentPanel.removeAll();
		for(int i = 0; i < img.length;i++) {
			ImageObjectPanel imageObjectPanel = new ImageObjectPanel(img[i]);
		
			
			imageObjectPanel.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					BufferedImage img = imageObjectPanel.getImage();
					
					
					
					PeopleInfo info = SplashDialogs.showPeopleInfoInputDialog(null);
					if(info == null) {
						listener.onSelect(null);
						return;
					}
					
						Date date = new Date(System.currentTimeMillis());
						File imagePath = new File(Constants.DEFAULT_PHOTO_PATH +
											System.currentTimeMillis() + '.' +
											Constants.DEFAULT_PHOTO_FORMAT);
						
						
						try {
							ImageIO.write(img, Constants.DEFAULT_PHOTO_FORMAT, imagePath);
						} catch (IOException e1) {
							SplashDialogs.showErrorDialog(null, "Не удалось сохранить изображение.");
					}
						
						info.setEntryDate(date);
						info.setPathToImage(imagePath.getAbsolutePath());
						
						listener.onSelect(info);
						
					
					super.mouseClicked(e);
				}
			});
			
			contentPanel.add(imageObjectPanel);
		}
		contentPanel.repaint();
		this.revalidate();
		this.repaint();
	}
	
	public void setFaceEventListener(FaceEventListener listener) {
		this.listener = listener;
	}
	

}