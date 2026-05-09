package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
//if the method is non static we need to created the object of multiple tests for one operation, so to make it memory efficient
	// make it static so that object creation only happened once //static block will
	// be ececuted only once during the class loading time
	// which will ensure the properties file get loaded into the memory
	// WAP to read the properties file from
	// src/test/resources/config/config.properties
	// public static void main(String[] args) throws IOException {
	private static Properties prop = new Properties();
	// we can create the object static so that it can be created once and shared
	// across all the classes

	private ConfigManagerOld() {
		// create private constructor so no one can create an object of the config
		// manager
	}

	static { // we have created the static block so that it can run once when class is
				// loading for initializing static variables
		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" +File.separator + "test" + File.separator
				+ "resources" + File.separator + "config" + File.separator + "config.properties"); // we have removed
																									// Backward slashes
																									// and used
																									// file.seprator so
																									// it would be
																									// platform
																									// independent
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		// special class: properties help to read the property file
		// Properties prop = new Properties(); // create the object of the properties
		// file //properties files is used to load the properties and read it

		// Load the properties file using load()

		return prop.getProperty(key);
	}

}
