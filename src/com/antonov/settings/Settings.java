package com.antonov.settings;

import java.io.Serializable;

import org.opencv.core.Size;

public class Settings implements Serializable {

	private static final long serialVersionUID = 1L;

	// ========== VIDEO ========

	private int videoW, videoH;

	private int videoIndex;
	
	private int fps;

	// ========== SERIAL ==========
	private String serialPort;

	private int serialSpeed;

	private String trueLockField;

	private String falseLockField;

	private String trueStateField;

	private String falseStateField;

	private boolean serialLoggingFlag;

	private String serialLoggingPath;
	
	
	// =========== REPORTS ============
	private String reportPath;
	
	
	// ===========  NETWORK ===========
	private String serverIP;
	
	private int serverPort;
	
	
	// ==========  OPENCV  ============
	
	private String cascadeClassifer;
	
	private double scaleFactor;
	
	private int minNeighbors;
	
	

	public String getCascadeClassifer() {
		return cascadeClassifer;
	}

	public void setCascadeClassifer(String cascadeClassifer) {
		this.cascadeClassifer = cascadeClassifer;
	}

	public double getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public int getMinNeighbors() {
		return minNeighbors;
	}

	public void setMinNeighbors(int minNeighbors) {
		this.minNeighbors = minNeighbors;
	}

	public int getVideoW() {
		return videoW;
	}

	public int getVideoH() {
		return videoH;
	}

	public void setVideoResolution(int videoW, int videoH) {
		this.videoW = videoW;
		this.videoH = videoH;
	}

	public int getVideoIndex() {
		return videoIndex;
	}

	public void setVideoIndex(int videoIndex) {
		this.videoIndex = videoIndex;
	}

	public String getSerialPort() {
		return serialPort;
	}

	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}

	public int getSerialSpeed() {
		return serialSpeed;
	}

	public void setSerialSpeed(int serialSpeed) {
		this.serialSpeed = serialSpeed;
	}

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public String getTrueLockField() {
		return trueLockField;
	}

	public void setTrueLockField(String trueLockField) {
		this.trueLockField = trueLockField;
	}

	public String getFalseLockField() {
		return falseLockField;
	}

	public void setFalseLockField(String falseLockField) {
		this.falseLockField = falseLockField;
	}

	public String getTrueStateField() {
		return trueStateField;
	}

	public void setTrueStateField(String trueStateField) {
		this.trueStateField = trueStateField;
	}

	public String getFalseStateField() {
		return falseStateField;
	}

	public void setFalseStateField(String falseStateField) {
		this.falseStateField = falseStateField;
	}

	public boolean isSerialLoggingFlag() {
		return serialLoggingFlag;
	}

	public void setSerialLoggingFlag(boolean serialLoggingFlag) {
		this.serialLoggingFlag = serialLoggingFlag;
	}

	public String getSerialLoggingPath() {
		return serialLoggingPath;
	}

	public void setSerialLoggingPath(String serialLoggingPath) {
		this.serialLoggingPath = serialLoggingPath;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setVideoW(int videoW) {
		this.videoW = videoW;
	}

	public void setVideoH(int videoH) {
		this.videoH = videoH;
	}

	
}
