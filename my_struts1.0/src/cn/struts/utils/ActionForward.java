package cn.struts.utils;

import java.util.HashMap;

public class ActionForward {
	
	/*
	 * 这个Map集合的value存放ActionForward对象,key为name属性
	 */
	static HashMap<String,ActionForward> forwards=ModuleConfig.forwards;
	static private  String name;
	private boolean redirect;//标识跳转方式，false为服务端，true 为客户端跳转
	private String path;
	
	
	//饱汉式Map单例+同步创建ActionForward对象
	private static ActionForward instance=null;
	public ActionForward(){}
	public static ActionForward getInstance(){
		instance=forwards.get(name);
		if(instance==null){
			instance=new ActionForward();
			forwards.put(name, instance);
		}
		return instance;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
