package com.antonov.cv;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;

public class ObjectDetector {

	private CascadeClassifier classifer;

	private double scaleFactor;

	private int minNeighbors;

	private Size minSize;

	private Size maxSize;

	public ObjectDetector() {
		classifer = new CascadeClassifier();
	}

	public MatOfRect detect(Mat frame) {
		MatOfRect faceDetections = new MatOfRect();
		classifer.detectMultiScale(frame, faceDetections, scaleFactor, minNeighbors, 0, minSize, maxSize);
		return faceDetections;
	}

	public ObjectDetector setCascadeClassifer(String cascadeClassifer) {
		classifer.load(cascadeClassifer);
		return this;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public ObjectDetector setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
		return this;
	}

	public int getMinNeighbors() {
		return minNeighbors;
	}

	public ObjectDetector setMinNeighbors(int minNeighbors) {
		this.minNeighbors = minNeighbors;
		return this;
	}

	public Size getMinSize() {
		return minSize;
	}

	public ObjectDetector setMinSize(Size minSize) {
		this.minSize = minSize;
		return this;
	}

	public Size getMaxSize() {
		return maxSize;
	}

	public ObjectDetector setMaxSize(Size maxSize) {
		this.maxSize = maxSize;
		return this;
	}

}
