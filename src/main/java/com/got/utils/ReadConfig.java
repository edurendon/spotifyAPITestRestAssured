package com.got.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	
	private Properties prop = new Properties();
	
	public ReadConfig () {
		try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	public String getProperties (String objeto) {
		String obj = prop.getProperty(objeto);
		return obj;
	}
	
}
