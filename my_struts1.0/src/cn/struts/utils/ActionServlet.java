package cn.struts.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.struts.dom.DomStruts;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class ActionServlet extends HttpServlet {

	/*
	 * 初始化方法，启动tomcat就读取struts-config.xml文件
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		DomStruts domStruts = new DomStruts();// 创建Dom解析struts-config.xml文件的解析对象
		domStruts.readStrutsConfigByXml();// 调用解析方法，对struts-config.xml文件进行解析
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/*
		 * 调用RequestProcess的process方法，对请求进行处理
		 */
		RequestProcess reqProcess = RequestProcess.getInstance();
		reqProcess.process(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		doPost(request, response);

	}
}
