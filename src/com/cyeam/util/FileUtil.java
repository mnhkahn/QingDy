package com.cyeam.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class FileUtil {

	public static String File2Str(String fileName) throws IOException {
		InputStream is = new FileInputStream("slide.json");
		StringBuilder builder = new StringBuilder();
		int ch;
		while((ch = is.read()) != -1){
		    builder.append((char)ch);
		}
		return builder.toString();
	}
	
	public static void Str2File(String fileName, String content) {
		System.out.println(fileName + "**********" + content);
		FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
        	fos = new FileOutputStream(fileName);
        	osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
//		FileOutputStream fos = new FileOutputStream("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/data/" + fileName);
//		DataOutputStream dos = new DataOutputStream(fos);
//		dos.writeUTF(content);
//		dos.close();	
	}
}
