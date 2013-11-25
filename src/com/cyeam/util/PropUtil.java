package com.cyeam.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.map.HashedMap;

public class PropUtil {

	private final static String pathConfFileName = "config.properties";
	private final static Properties prop = new Properties();
	
	public final static int SLIDE_PATH = 1;
	
	public static String getProps(String rootPath, int type) {
    	try {
    		System.out.println(rootPath + "WEB-INF\\" + pathConfFileName);
    		InputStream is = new FileInputStream(rootPath + "WEB-INF/" + pathConfFileName);
    		//load a properties file from class path, inside static method
    		prop.load(is);
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	
		String propStr = null;
		switch (type) {
		case 1:
			propStr = prop.getProperty("slidePath");
			break;

		default:
			break;
		}
		return propStr;
	}
}
