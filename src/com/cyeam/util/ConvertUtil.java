package com.cyeam.util;

import java.io.IOException;
import java.io.InputStream;

public class ConvertUtil {
	public static String inputStream2String (InputStream in) {
	    StringBuffer out = new StringBuffer();
	    byte[] b = new byte[4096];
	    try {
			for (int n; (n = in.read(b)) != -1;) {
			    out.append(new String(b, 0, n));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return out.toString();
	}
}
