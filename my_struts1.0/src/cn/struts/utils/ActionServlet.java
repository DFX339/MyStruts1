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
	 * ��ʼ������������tomcat�Ͷ�ȡstruts-config.xml�ļ�
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		DomStruts domStruts = new DomStruts();// ����Dom����struts-config.xml�ļ��Ľ�������
		domStruts.readStrutsConfigByXml();// ���ý�����������struts-config.xml�ļ����н���
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/*
		 * ����RequestProcess��process��������������д���
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
