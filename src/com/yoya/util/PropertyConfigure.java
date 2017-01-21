package com.yoya.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfigure {

	public static Properties configure(String confUrl){
		InputStream inputStream=null;
		Properties properties=null;
		try {
			inputStream = new FileInputStream(confUrl);
			properties=new Properties();	
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}
