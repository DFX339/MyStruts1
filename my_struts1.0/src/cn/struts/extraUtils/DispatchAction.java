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
 * DispatchAction类
 * 一个Action类处理多个请求
 * 实现一个模块一个Action
 * @author Administrator
 * （使用的时候直接extends DispatchAction就可以了，不用再做别的配置）
 *
 */
public class DispatchAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1、得到ActionMapping对象中的parameter属性对应的属性
		 * 2、判断parameter是否为null，如果为null就默认调用defaultMethod方法
		 * 3、不为null就继续执行，通过parameter得到表单中的同名参数传过来的参数值（需要执行的方法名）
		 */
		String parameter=mapping.getParameter();//得到ActionMapping对象中的parameter属性对应的属性值
		
		if(parameter==null&&parameter==""){
			defaultMethod(mapping, form, request, response);
		}
		
		String methodName=request.getParameter(parameter);//得到表单中参数名为parameter属性对应的属性值的参数的参数值
	
		/*
		 * 通过方法反射，确定调用的具体的方法
		 * 1、得到调用该方法类的反射对象
		 * 2、使用该反射对象调用getDeclaredMethod（）方法的，得到Method对象
		 * 3、创建该类的实例对象
		 * 4、方法执行 invoke
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
				throw new MethodNotDefinedException("方法未定义异常");
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
	 * 默认执行的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward defaultMethod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		throw new MethodNotDefinedException("没有指定方法名"); 
	}
}
