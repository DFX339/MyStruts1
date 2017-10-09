package cn.struts.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class RequestProcess {

	HashMap<String, FormBeanConfig> formConfigs = ModuleConfig.formConfigs;
	HashMap<String, ActionMapping> actionConfigs = ModuleConfig.actionConfigs;
	HashMap<String, ActionForward> forwards = ModuleConfig.forwards;

	private static RequestProcess instance = null;

	private RequestProcess() {
	}

	public static RequestProcess getInstance() {
		if (instance == null) {
			instance = new RequestProcess();
		}
		return instance;
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1���õ�����·��
		String path = processPath(request, response);

		//2�� �õ�ActionMapping����
		ActionMapping mapping = processMapping(request, response, path);
		if (mapping == null)
			return;

		String name = mapping.getName();// �õ�name����

		
		//3��ͨ��name���Եõ�ActionForm����
		ActionForm form = processActionForm(request, response, mapping, name);

		//4���ռ������� 
		populate(request, response, form);

		//5���ж�forward�����Ƿ�Ϊnull ,���Ϊnull�ͼ������£� �����Ϊnull�͸���forward���������ת(ָ��Ϊ�����)
		String forward=findForwardAttr(request, response, mapping);
		if (forward != null&&forward!="") {
			try {
				request.getRequestDispatcher(forward)
						.forward(request, response);
				return;
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		//6������Action�����
		Action action = processCreateAction(request, response, mapping);

		//7��ִ��Action��execute����
		ActionForward forward1 = processActionPerform(request, action,
				response, mapping, form);


		//8������forward1�����ת
		processForward(request, response, forward1);

	}

	/*
	 * 1�� ��ȡURL--path
	 */
	public String processPath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = request.getServletPath();
		int start = url.lastIndexOf(".");
		int end = url.lastIndexOf("/");
		String path = url.substring(end, start);
		System.out.println(url+"-1--��ȡ����������·������"+start+"--"+end+"--"+path);
		return path;
	}

	/*
	 * 2�� ����path���Եõ�ActionMapping����
	 */
	public ActionMapping processMapping(HttpServletRequest request,
			HttpServletResponse response, String path) throws ServletException,
			IOException {

		ActionMapping mapping = actionConfigs.get(path);
		System.out.println("-2--�õ���ActionMapping���󡪡�"+mapping);
		return mapping;
	}

	/*
	 * 3���õ�ActionForm���� ͨ��name���Եõ�FormBeanConfig����
	 * ͨ��FormBeanConfig����õ�ActionForm����
	 * ���ж�scope������û��ActionForm�����еĻ���ֱ��ȡ����û�еľʹ���
	 */
	public ActionForm processActionForm(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping, String name)
			throws ServletException, IOException {

		FormBeanConfig formConfig = formConfigs.get(name);
		String formType = formConfig.getType();

		ActionForm form = null;
		String scope = mapping.getScope();
		if ("request".equals(scope)) {
			form = (ActionForm) request.getAttribute(name);
		} else {
			form = (ActionForm) request.getSession().getAttribute(name);
		}
		System.out.println("-3-�ҵ�scope�洢��----" + scope);

		// ���formΪnull���ʹ���ActionForm����󣬲��ҽ���������Ӧ������
		if (form == null) {
			try {
				form = (ActionForm) Class.forName(formType).newInstance();// ͨ�����䴴������
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//������ActionForm����洢��scope����
			if ("request".equals(scope)) {
				request.setAttribute(name, form);
			} else {
				request.getSession().setAttribute(name, form);
			}
		}
		System.out.println("-3-������ActionForm����----" + form);

		return form;
	}

	/*
	 * 4���ռ������� Enumeration�ռ������� request.getParamterNames();�ռ����е����в�����
	 * request.getParameterValues(name);ȡ����������Ӧ�Ĳ���ֵ ���ӱ���ȡ���Ĳ������Ͳ���ֵ
	 * ͨ��BeanUtils.setProperty()������ŵ�bean����
	 */
	public void populate(HttpServletRequest request,
			HttpServletResponse response, ActionForm form)
			throws ServletException, IOException {

		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String reqName = (String) names.nextElement();// �õ����еĲ�����
			String name=reqName;
			Object[] reqValue = request.getParameterValues(reqName);// �õ����еĲ�������Ӧ�Ĳ���ֵ
			Object value=null;
			for(int i=0;i<reqValue.length;i++){
				value=reqValue[i];
			}
			try {
				BeanUtils.setProperty(form, name, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.println("-4-�ռ�������----" + reqName + ":" + reqValue+"***"+value);
		}
		
		
		}
	

	/*
	 * 5���ж�forward�����Ƿ�Ϊnull ,���Ϊnull�ͼ������£� �����Ϊnull�͸���forward���������ת(ָ��Ϊ�����)
	 */
	public String findForwardAttr(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws ServletException, IOException {
		String forward = mapping.getForward();
		System.out.println("-5 <action>��ǩ�е�forward����ֵ----" + forward);
		return forward;
	}

	/*
	 * 6������Action�����
	 */
	public Action processCreateAction(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws ServletException, IOException {
		String actionType = mapping.getType();
		Action action = null;
		try {
			action = (Action) Class.forName(actionType).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("-6-����action�����----" + action);
		return action;
	}

	/*
	 * 7��ִ��Action��execute����
	 */
	public ActionForward processActionPerform(HttpServletRequest request,
			Action action, HttpServletResponse response, ActionMapping mapping,
			ActionForm form) throws ServletException, IOException {
		
		ActionForward forward = action
				.execute(mapping, form, request, response);
		System.out.println("-7-execute�����еķ��ص�ActionForward����----" + forward);
		return forward;
	}

	/*
	 * 8������forward1�����ת
	 */
	public void processForward(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward)
			throws ServletException, IOException {

		String forwardPath = forward.getPath();// �õ���ת·��
		boolean redirect = forward.isRedirect();// �õ���ת��ʽ
		System.out.println("-8-forward1path"+forwardPath+";forwardRedirect----" + redirect);
		if (redirect == true) {
			response.sendRedirect(request.getContextPath()+forwardPath);
		} else {
			request.getRequestDispatcher(forwardPath)
					.forward(request, response);
		}
	}
}
