package com.qingdy.common;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Servlet implementation class CServlet
 */
@WebServlet("/CServlet")
public class CServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected String id = "0";
	protected String action = null;
	
	protected SQLParameters parameters = null;
	
	protected String raw = null;
	protected Map<String, Object> json = null;
	protected List list = new LinkedList<>();
	protected Object object;
	
	protected JsonValueProcessor jsonProcessor;
	protected JsonConfig jsonConfig;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		jsonProcessor = new DateJsonValueProcessor();
		jsonConfig = new JsonConfig();
        //注册值处理器
        jsonConfig.registerJsonValueProcessor(Date.class, jsonProcessor);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		int type = Action.getURIType(url);
		
		String action[] = new String[2];
		
		parameters = Action.getParameter(request);
		// No id or action
		if (type == 0) {
			
		}
		// Id
		else if (type == 1) {
			action[0] = Action.getID(url);
		}
		// Action
		else if (type == 2) {
			action[1] = Action.getAction(url);
		}
		// Id & action
		else if (type == 3) {
			action = Action.getActionID(url);
		}
		else {
		}
		
		// 
		String raw = CConvert.convertStreamToString(request.getInputStream());
        if (!raw.equals("")) {
        	json = (Map<String, Object>)JSONObject.toBean(JSONObject.fromObject(raw), Map.class);
        }        
		
		this.id = (action[0] == null) ? "-1" : action[0];
		this.action = (action[1] == null) ? "" : action[1];

/*		this.size = (parameters[0] == null) ? Constant.PAGE_DEFAULT_SIZE : Integer.parseInt(parameters[0]);
		this.page = (parameters[1] == null) ? Constant.PAGE_DEFAULT_NUMBER : Integer.parseInt(parameters[1]);
		
		String searchField = (parameters[2] == null) ? "" : parameters[2];
		String searchOper = (parameters[3] == null) ? "" : " like ";
		String keyword = (parameters[4] == null) ? "" : "'%" + parameters[4] + "%'";
		keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
		
		String sidx = (parameters[5] == null) ? "" : " order by " + parameters[5];
		String sord = (parameters[6] == null) ? "" : " " + parameters[6]; 
		
		this.condition = searchField + searchOper + keyword;
		this.sort = sidx + sord;*/
		this.raw = raw;
		
//		System.out.println("id: " + id + "| action: " + this.action + "| size: " + size + "| page:" + page + "| condition: " + condition + "| sort: " + sort);
		System.out.println("raw: " + raw);
		
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = JSONSerializer.toJSON(object , jsonConfig).toString();
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected Object initialize(Class classes, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		Object object = classes.newInstance();
		
		
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = classes.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(object, json.get(json.keySet().toArray()[i]));
				}
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return object;
	}
	
	public String toJson() {
		return JSONSerializer.toJSON(list , jsonConfig).toString();
	}

}
