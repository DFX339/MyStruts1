package cn.struts.extraUtils;

import java.util.HashMap;

import cn.struts.utils.ActionForward;
import cn.struts.utils.ModuleConfig;

/**
 * 全局跳转对象
 * @author Administrator
 *
 */
public class GlobalForward {

	/*
	 * 这个Map集合的value存放GlobalForward对象,key为name属性
	 */
	static HashMap<String,GlobalForward> globalForwards=ModuleConfig.globalForwards;
	static private  String name;
	private boolean redirect;//标识跳转方式，false为服务端，true 为客户端跳转
	private String path;
	
	
	//饱汉式Map单例+同步创建ActionForward对象
	private static GlobalForward instance=null;
	public GlobalForward(){}
	public static GlobalForward getInstance(){
		instance=globalForwards.get(name);
		if(instance==null){
			instance=new GlobalForward();
			globalForwards.put(name, instance);
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
