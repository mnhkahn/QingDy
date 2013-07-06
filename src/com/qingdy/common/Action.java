package com.qingdy.common;

import javax.servlet.http.HttpServletRequest;

import com.qingdy.dao.SQLParameters;

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
	public static int isDigit(String digit) {
		int result = 0;
		for (int i = 0; i < digit.length(); i++) {
			if (Character.isDigit(digit.charAt(i))) {
				result++;
			}
		}
		return result;
	}
	
	public static SQLParameters getParameter(HttpServletRequest request) {
		SQLParameters parameters = new SQLParameters();

		int size = request.getParameter("rows") != null ? Integer.parseInt(request.getParameter("rows")) : Constant.PAGE_DEFAULT_SIZE;
		parameters.setSize(size);
		
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : Constant.PAGE_DEFAULT_NUMBER;
		parameters.setPage(page);
		parameters.setField(request.getParameter("searchField"));
		
		String op = (request.getParameter("searchOper") != null) ? request.getParameter("searchOper") : "eq";
		switch (op) {
		case "eq":
			op = "=";
			break;
		case "cn":
			op = " like ";
			break;

		default:
			op = " like ";
			break;
		}
		parameters.setOperator(op);
		parameters.setValue(request.getParameter("searchString"));
		parameters.setSidx(request.getParameter("sidx"));
		parameters.setSord(request.getParameter("sord"));
		
		System.out.println("size: " + parameters.getSize() + " page:" + parameters.getPage());

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
