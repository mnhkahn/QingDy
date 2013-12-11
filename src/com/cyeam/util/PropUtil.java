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
	public final static int CONFIG_INDEX = 11;
	public final static int CONFIG_NEWS = 12;
	public final static int CONFIG_AD = 13;
	public final static int UPLOAD_USER = 21;
	public final static int UPLOAD_AVATAR = 22;
	public final static int UPLOAD_SKIN = 23;
	public final static int UPLOAD_IMAGES = 24;
	public final static int UPLOAD_NEWS = 25;
	// Upload file size
	public static final int UPLOAD_MAX_SIZE = 20;
	// Avatar size
	public static final int AVATAR_WIDTH = 31;
	public static final int AVATAR_HEIGHT = 32;
	// Skin size
	public static final int SKIN_WIDTH = 33;
	public static final int SKIN_HEIGHT = 34;
	
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
		case SLIDE_PATH:
			propStr = prop.getProperty("slidePath");
			break;
		case CONFIG_INDEX:
			propStr = prop.getProperty("indexAd");
			break;
		case CONFIG_NEWS:
			propStr = prop.getProperty("newsAd");
			break;
		case CONFIG_AD:
			propStr = prop.getProperty("ad");
			break;
		case UPLOAD_USER:
			propStr = prop.getProperty("uploadFile");
			break;
		case UPLOAD_AVATAR:
			propStr = prop.getProperty("uploadAvatar");
			break;
		case UPLOAD_SKIN:
			propStr = prop.getProperty("uploadSkin");
			break;
		case UPLOAD_IMAGES:
			propStr = prop.getProperty("uploadImg");
			break;
		case UPLOAD_NEWS:
			propStr = prop.getProperty("uploadNews");
			break;
		case UPLOAD_MAX_SIZE:
			propStr = prop.getProperty("fileSize");
			break;
		case AVATAR_WIDTH:
			propStr = prop.getProperty("avatarWidth");
			break;
		case AVATAR_HEIGHT:
			propStr = prop.getProperty("avatarHeight");
			break;
		case SKIN_WIDTH:
			propStr = prop.getProperty("skinWidth");
			break;
		case SKIN_HEIGHT:
			propStr = prop.getProperty("skinHeight");
			break;
		default:
			break;
		}
		return propStr;
	}
	
	public static String getProps(String rootPath, String key) {
    	try {
    		System.out.println(rootPath + "WEB-INF\\" + pathConfFileName);
    		InputStream is = new FileInputStream(rootPath + "WEB-INF/" + pathConfFileName);
    		//load a properties file from class path, inside static method
    		prop.load(is);
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
    	
		return prop.getProperty(key);
	}
}
