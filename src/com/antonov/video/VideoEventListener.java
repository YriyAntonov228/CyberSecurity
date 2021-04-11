package com.antonov.video;

import org.opencv.core.Mat;

public interface VideoEventListener {
	
	public void frameCaptured(Mat frame);
	
}
