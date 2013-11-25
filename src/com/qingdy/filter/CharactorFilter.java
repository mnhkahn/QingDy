package com.qingdy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cyeam.util.ConvertUtil;

public class CharactorFilter implements Filter {

	//字符编码
	String encoding = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//获取初始化参数
		encoding = filterConfig.getInitParameter("encoding");
		System.out.println("encoding"+encoding);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 判断字符编码是否为空
		if (encoding != null) {
			// 设置request编码格式
			request.setCharacterEncoding(encoding);
			// 设置response编码格式
			// response.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}
		//传递给下一个过滤器
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		encoding = null;
	}

}
