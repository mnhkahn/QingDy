package com.cyeam.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

public class FileUtil {

	public static String File2Str(String fileName) throws IOException {
		InputStream is = new FileInputStream("slide.json");
		StringBuilder builder = new StringBuilder();
		int ch;
		while ((ch = is.read()) != -1) {
			builder.append((char) ch);
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

		// FileOutputStream fos = new
		// FileOutputStream("C:/Users/Bryce/Documents/GitHub/QingDy/WebRoot/data/"
		// + fileName);
		// DataOutputStream dos = new DataOutputStream(fos);
		// dos.writeUTF(content);
		// dos.close();
	}
	
	/**
	 * 复制文件(以超快的速度复制文件)
	 * 
	 * @param srcFile
	 *            源文件File
	 * @param destDir
	 *            目标目录File
	 * @param newFileName
	 *            新文件名
	 * @return 实际复制的字节数，如果文件、目录不存在、文件为null或者发生IO异常，返回-1
	 */
	public static long copyFile(File srcFile, File destDir, String newFileName) {
		long copySizes = 0;
		if (!srcFile.exists()) {
			System.out.println("源文件不存在");
			copySizes = -1;
		} else if (!destDir.exists()) {
			System.out.println("目标目录不存在");
			copySizes = -1;
		} else if (newFileName == null) {
			System.out.println("文件名为null");
			copySizes = -1;
		} else {
			try {
				FileChannel fcin = new FileInputStream(srcFile).getChannel();
				FileChannel fcout = new FileOutputStream(new File(destDir,
						newFileName)).getChannel();
				long size = fcin.size();
				fcin.transferTo(0, fcin.size(), fcout);
				fcin.close();
				fcout.close();
				copySizes = size;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return copySizes;
	}

}
