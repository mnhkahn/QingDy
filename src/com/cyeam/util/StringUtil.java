package com.cyeam.util;

import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}
}
