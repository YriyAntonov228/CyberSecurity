package com.antonov.video;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class Capture {
	
	
	public enum DefaultPreviewSize {
		
		SIZE_320_240(new Size(320, 240)),
		SIZE_640_480(new Size(640, 480)),
		SIZE_800_600(new Size(800,600)),
		SIZE_1280_720(new Size(1280,720)),
		SIZE_1080_1920(new Size(1080, 1920));
		
	    private Size size;
	    
	    private DefaultPreviewSize(Size size) {
			this.size = size;
		}
	    
	    public Size getSize() {
	    	return size;
	    }
	}
	
	
	private VideoCapture capture;
	
	private Thread thread;
	
	private ArrayList<VideoEventListener> listeners = new ArrayList<VideoEventListener>();
	
	private boolean runningFlag = false;
	
	private Runnable videoRunnable;
	
	public Capture(int deviceIndex) {
		capture = new VideoCapture(deviceIndex);
	
		
		videoRunnable = createVideoRunnable();
		
		
	}
	
	
	
	
	public Runnable createVideoRunnable() {
		return new Runnable() {

			@Override
			public void run() {
				Mat capturedMat = new Mat();
				while(runningFlag) {
					if(capture.read(capturedMat)) {
						for (VideoEventListener videoEventListener : listeners) {
							videoEventListener.frameCaptured(capturedMat);
						}
					}
				}
			}
		};
	}
  
	
	public void setSize(DefaultPreviewSize size) {
		setSize((int)size.getSize().width, (int)size.getSize().height);
	}
	
	
	public void setSize(int width, int height) {
		 capture.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, width);
		 capture.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, height);
	}
	
	
	public void start() {
		if(runningFlag)
			return;
		runningFlag = true;
		
		thread = new Thread(videoRunnable);
		thread.start();
	}
	
	public void stop() {
		runningFlag = false;
		capture.release();
	}
	
	
	public void addVideoEventListener(VideoEventListener listener) {
		listeners.add(listener);
	}
	
	public void removeVideoEventListener(int index) {
		listeners.remove(index);
	}
	
	public void removeVideoEventListener(VideoEventListener listener) {
		listeners.remove(listener);
	}

	public boolean isStarted() {
		return runningFlag;
	}

	

}
