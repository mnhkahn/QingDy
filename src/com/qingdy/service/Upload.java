package com.qingdy.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.qingdy.common.Action;
import com.qingdy.common.Constant;


public class Upload extends HttpServlet {
	
	private String action = null;
	
	public Upload() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		action = Action.getAction(url);	
		if (action.equals("news")) {
			System.out.println("############" + ServletFileUpload.isMultipartContent(request));
			if (ServletFileUpload.isMultipartContent(request)) {
				FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(factory);
				List items;
				try {
					items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
					
	            	FileItem item = (FileItem) iterator.next();
	            	if (!item.isFormField()) {
	            		String fileName = item.getName();

	                    String root = Constant.NEWS_IMAGE_PATH;

	                    Date date = new Date();
	        			Timestamp regdate = new Timestamp(date.getTime());

	                    File uploadedFile = new File(root + regdate.getTime() + fileName);
	                    item.write(uploadedFile);

	                    response.getWriter().write("{\"err\":\"" + "" + "\",\"msg\":\"" + "/news/" + regdate.getTime() + fileName + "\"}");
	            	}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
/*			    ServletFileUpload fileUpload = new ServletFileUpload();
			    FileItemIterator items = null;
				try {
					items = fileUpload.getItemIterator(request);
					while (items.hasNext()) {
						FileItem item = (FileItem) items.next();
						 
					}
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			/*
			 * try { List<FileItem> items = new ServletFileUpload(new
			 * DiskFileItemFactory()).parseRequest(request); for (FileItem item
			 * : items) { if (item.isFormField()) { // Process regular form
			 * field (input type="text|radio|checkbox|etc", select, etc). String
			 * fieldname = item.getFieldName(); String fieldvalue =
			 * item.getString(); // ... (do your job here) } else { // Process
			 * form file field (input type="file"). String fieldname =
			 * item.getFieldName(); String filename =
			 * FilenameUtils.getName(item.getName()); InputStream filecontent =
			 * item.getInputStream(); // ... (do your job here) } } } catch
			 * (FileUploadException e) { throw new
			 * ServletException("Cannot parse multipart request.", e); }
			 */

		}
	}

}
