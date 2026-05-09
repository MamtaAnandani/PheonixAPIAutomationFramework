package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mozilla.javascript.ast.SwitchCase;

public class ConfigManager {
	private static Properties prop = new Properties();
	private static String path = "config/config.properties";

	private static String env;

	private ConfigManager() {
		// create private constructor so no one can create an object of the config
		// manager
	}

	static {

		env = System.getProperty("env", "qa"); // if we are not passing anything it will run qa or else whichever you
												// pass
		env = env.toLowerCase().trim();// whether whatever you right it will be converted into lower case and trim the
										// extra space
		System.out.println("Reuning tests in Env" + env);
		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties"; // instead of curly braces, : and break statement use ->
															// operator to make code concise supported by java 14

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";
		}

		InputStream input = Thread.currentThread().getContextClassLoader() // it provides the current thread directory
																			// where the framework is located, the class
																			// loader provides the informaton about the
																			// path of the config manager, the resources
																			// As stream
																			// will provide the exact loaction of the
																			// resource file
				// .getResourceAsStream("config/config.properties");
				.getResourceAsStream(path);// we are using the path so that can handle any runtime exception

		if (input == null) {
			throw new RuntimeException("Can not find the file at the path" + path); // can handle the exception if path
																					// goes wrong
		}

		try {
			prop.load(input); // Using input steam so that we can optimize the big path which file reader uses
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		return prop.getProperty(key);
	}

}
