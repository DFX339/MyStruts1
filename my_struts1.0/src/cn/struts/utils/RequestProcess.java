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
		
		//1、得到请求路径
		String path = processPath(request, response);

		//2、 得到ActionMapping对象
		ActionMapping mapping = processMapping(request, response, path);
		if (mapping == null)
			return;

		String name = mapping.getName();// 得到name属性

		
		//3、通过name属性得到ActionForm对象
		ActionForm form = processActionForm(request, response, mapping, name);

		//4、收集表单数据 
		populate(request, response, form);

		//5、判断forward属性是否为null ,如果为null就继续往下， 如果不为null就根据forward属性完成跳转(指定为服务端)
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
		

		//6、创建Action类对象
		Action action = processCreateAction(request, response, mapping);

		//7、执行Action的execute方法
		ActionForward forward1 = processActionPerform(request, action,
				response, mapping, form);


		//8、根据forward1完成跳转
		processForward(request, response, forward1);

	}

	/*
	 * 1、 截取URL--path
	 */
	public String processPath(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = request.getServletPath();
		int start = url.lastIndexOf(".");
		int end = url.lastIndexOf("/");
		String path = url.substring(end, start);
		System.out.println(url+"-1--截取出来的请求路径——"+start+"--"+end+"--"+path);
		return path;
	}

	/*
	 * 2、 根据path属性得到ActionMapping对象
	 */
	public ActionMapping processMapping(HttpServletRequest request,
			HttpServletResponse response, String path) throws ServletException,
			IOException {

		ActionMapping mapping = actionConfigs.get(path);
		System.out.println("-2--得到的ActionMapping对象——"+mapping);
		return mapping;
	}

	/*
	 * 3、得到ActionForm对象 通过name属性得到FormBeanConfig对象
	 * 通过FormBeanConfig对象得到ActionForm对象
	 * 先判断scope域中有没有ActionForm对象，有的话就直接取出，没有的就创建
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
		System.out.println("-3-找到scope存储域----" + scope);

		// 如果form为null，就创建ActionForm类对象，并且将对象存入对应的域中
		if (form == null) {
			try {
				form = (ActionForm) Class.forName(formType).newInstance();// 通过反射创建对象
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//将创建ActionForm对象存储到scope域中
			if ("request".equals(scope)) {
				request.setAttribute(name, form);
			} else {
				request.getSession().setAttribute(name, form);
			}
		}
		System.out.println("-3-创建的ActionForm对象----" + form);

		return form;
	}

	/*
	 * 4、收集表单数据 Enumeration收集表单数据 request.getParamterNames();收集表单中的所有参数名
	 * request.getParameterValues(name);取出参数名对应的参数值 将从表单中取出的参数名和参数值
	 * 通过BeanUtils.setProperty()方法存放到bean里面
	 */
	public void populate(HttpServletRequest request,
			HttpServletResponse response, ActionForm form)
			throws ServletException, IOException {

		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String reqName = (String) names.nextElement();// 得到表单中的参数名
			String name=reqName;
			Object[] reqValue = request.getParameterValues(reqName);// 得到表单中的参数名对应的参数值
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
			System.out.println("-4-收集表单数据----" + reqName + ":" + reqValue+"***"+value);
		}
		
		
		}
	

	/*
	 * 5、判断forward属性是否为null ,如果为null就继续往下， 如果不为null就根据forward属性完成跳转(指定为服务端)
	 */
	public String findForwardAttr(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws ServletException, IOException {
		String forward = mapping.getForward();
		System.out.println("-5 <action>标签中的forward属性值----" + forward);
		return forward;
	}

	/*
	 * 6、创建Action类对象
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
		System.out.println("-6-创建action类对象----" + action);
		return action;
	}

	/*
	 * 7、执行Action的execute方法
	 */
	public ActionForward processActionPerform(HttpServletRequest request,
			Action action, HttpServletResponse response, ActionMapping mapping,
			ActionForm form) throws ServletException, IOException {
		
		ActionForward forward = action
				.execute(mapping, form, request, response);
		System.out.println("-7-execute方法中的返回的ActionForward对象----" + forward);
		return forward;
	}

	/*
	 * 8、根据forward1完成跳转
	 */
	public void processForward(HttpServletRequest request,
			HttpServletResponse response, ActionForward forward)
			throws ServletException, IOException {

		String forwardPath = forward.getPath();// 得到跳转路径
		boolean redirect = forward.isRedirect();// 得到跳转方式
		System.out.println("-8-forward1path"+forwardPath+";forwardRedirect----" + redirect);
		if (redirect == true) {
			response.sendRedirect(request.getContextPath()+forwardPath);
		} else {
			request.getRequestDispatcher(forwardPath)
					.forward(request, response);
		}
	}
}
