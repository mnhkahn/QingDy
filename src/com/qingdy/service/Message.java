package com.qingdy.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qingdy.common.CServlet;
import com.qingdy.dao.MessageDao;
import com.qingdy.dao.impl.MessageDaoImpl;
import com.qingdy.domain.QdLoan;
import com.qingdy.domain.QdMessage;

/**
 * Servlet implementation class Message
 */
@WebServlet("/Message")
public class Message extends CServlet {
	private static final long serialVersionUID = 1L;
       
	private MessageDao messageDao = null;
	private QdMessage message = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Message() {
        super();
        messageDao = new MessageDaoImpl();
        
        message = new QdMessage();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list = new LinkedList<>();
		
		id = request.getParameter("id");
		if (parameters.getSize() > 1) {
			list = messageDao.getMessageList(parameters.getSize(), parameters.getPage(), Integer.parseInt(id));
		}
		else if (action.equals("unread")) {
			list.add(messageDao.getUnreadCount(Integer.parseInt(id)));
		}
		
		response.getWriter().write(toJson());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initialize(request, response);
		if (action.equals("")) {
			messageDao.addMessage(message);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void initialize(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		for (int i = 0; i < json.keySet().size(); i++) {
			Method method = null;
			try {
				method = QdMessage.class.getMethod("set" + json.keySet().toArray()[i], json.get(json.keySet().toArray()[i]).getClass());
				System.out.println(method.getName());
				if (method != null) {
					System.out.println(json.get(json.keySet().toArray()[i]));
					method.invoke(message, json.get(json.keySet().toArray()[i]));
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
		
	}

}
