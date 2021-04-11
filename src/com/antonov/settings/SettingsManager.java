package com.antonov.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SettingsManager {

	private static SettingsManager instance;
	
	private SettingsManager() {}
	
	public static SettingsManager getInstance() {
		if(instance == null) {
			instance = new SettingsManager();
		}
		return instance;
	}
	public void save(File file, Settings settings) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		objectOutputStream.writeObject(settings);
		objectOutputStream.close();
	}

	public Settings load(File file) throws ClassNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		return (Settings) objectInputStream.readObject();

	}
}
