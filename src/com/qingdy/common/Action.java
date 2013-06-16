package com.qingdy.common;

import javax.servlet.http.HttpServletRequest;

public class Action {
	
	public static int getURIType(String url) {
		int count = 0;
		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) == '/')
				count++;
		}
        
		if (count == 5) {
			return 0;
		}
		else if (count == 6) {
	        String digit = url.substring(url.lastIndexOf('/') + 1);
	        
	        int digitCount = isDigit(digit);
	        System.out.println(digitCount);
			// Id
			if (digitCount == digit.length())
				return 1;
			// Action
			else if (digitCount == 0)
				return 2;
			else
				return -1;
		}
		else if (count == 7) {
			return 3;
		}
		else {
			return -1;
		}
	}
	
	// return digit number
	private static int isDigit(String digit) {
		int result = 0;
		for (int i = 0; i < digit.length(); i++) {
			if (Character.isDigit(digit.indexOf(i))) {
				result++;
			}
		}
		return result;
	}
	
	public static String[] getParameter(HttpServletRequest request) {
		String[] parameters = new String[3];
		
		parameters[0] = request.getParameter("size");
		parameters[1] = request.getParameter("page");
		parameters[2] = request.getParameter("keyword");
		
		return parameters;
	}
	
	public static String[] getActionID(String url) {

		String[] parameters = new String[2];

        if (url.endsWith("/"))
            url = url.substring(0, url.length() - 1);
        
        // action
        parameters[1] = url.substring(url.lastIndexOf('/') + 1);
        
        url = url.substring(0, url.lastIndexOf('/'));
        
        // id
        parameters[0] = url.substring(url.lastIndexOf('/') + 1);
        
        return parameters;
	}
	
	public static String getID(String url) {
		String id = new String();

        if (url.endsWith("/"))
            url = url.substring(0, url.length() - 1);
        
        // id
        id = url.substring(url.lastIndexOf('/') + 1);
        
        return id;
	}
	
	public static String getAction(String url) {
		String action = new String();

        if (url.endsWith("/"))
            url = url.substring(0, url.length() - 1);
        
        // action
        action = url.substring(url.lastIndexOf('/') + 1);
        
        return action;
	}
}
