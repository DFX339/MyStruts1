package cn.struts.extraUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.struts.exception.MethodNotDefinedException;
import cn.struts.utils.Action;
import cn.struts.utils.ActionForm;
import cn.struts.utils.ActionForward;
import cn.struts.utils.ActionMapping;

/**
 * DispatchAction��
 * һ��Action�ദ��������
 * ʵ��һ��ģ��һ��Action
 * @author Administrator
 * ��ʹ�õ�ʱ��ֱ��extends DispatchAction�Ϳ����ˣ���������������ã�
 *
 */
public class DispatchAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1���õ�ActionMapping�����е�parameter���Զ�Ӧ������
		 * 2���ж�parameter�Ƿ�Ϊnull�����Ϊnull��Ĭ�ϵ���defaultMethod����
		 * 3����Ϊnull�ͼ���ִ�У�ͨ��parameter�õ����е�ͬ�������������Ĳ���ֵ����Ҫִ�еķ�������
		 */
		String parameter=mapping.getParameter();//�õ�ActionMapping�����е�parameter���Զ�Ӧ������ֵ
		
		if(parameter==null&&parameter==""){
			defaultMethod(mapping, form, request, response);
		}
		
		String methodName=request.getParameter(parameter);//�õ����в�����Ϊparameter���Զ�Ӧ������ֵ�Ĳ����Ĳ���ֵ
	
		/*
		 * ͨ���������䣬ȷ�����õľ���ķ���
		 * 1���õ����ø÷�����ķ������
		 * 2��ʹ�ø÷���������getDeclaredMethod���������ģ��õ�Method����
		 * 3�����������ʵ������
		 * 4������ִ�� invoke
		 */
			Class c=this.getClass();
			Method method=null;
			ActionForward forward=null;
			try {
				method=c.getDeclaredMethod(methodName, ActionMapping.class,ActionForm.class,HttpServletRequest.class,HttpServletResponse.class);
				forward=(ActionForward) method.invoke(this, mapping,form,request,response);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				throw new MethodNotDefinedException("����δ�����쳣");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		return forward;
	}
	
	
	/**
	 * Ĭ��ִ�еķ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward defaultMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		throw new MethodNotDefinedException("û��ָ��������"); 
	}
}
