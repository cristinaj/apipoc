package com.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static ConfigManager manager;
	private static final Properties properties = new Properties();
	
	private ConfigManager() throws IOException {
		InputStream inputStream = ConfigManager.class.getResourceAsStream("/config.properties");
		properties.load(inputStream);
	}
	
	public static ConfigManager getInstance() {
		if(manager == null) {
			synchronized (ConfigManager.class) {
				try {
					manager = new ConfigManager();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return manager;
	}
	
	public String getValue(String key) {
		return System.getProperty(key, properties.getProperty(key));
	}
}