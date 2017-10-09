package cn.struts.utils;

import java.util.HashMap;

public class ActionMapping {

	HashMap<String,ActionForward> forwards=ModuleConfig.forwards;
	static HashMap<String,ActionMapping> actionConfigs=ModuleConfig.actionConfigs;
	private String path;//请求路径
	private String name;//引用的ActionForm
	private String scope="session";//存储域
	private String type;//Action的类路径名
	private String forward;//跳转页面路径
	private String unknown;//容错处理
	private String input;//出错页面
	private String parameter;//使用DispatchAction时指定方法名的属性
	
	
	//通过单例创建ActionMapping 对象（需要传递参数 path）
	private static ActionMapping instance=null;
	public ActionMapping(){}
	public static ActionMapping getInstance(String path){
		instance=actionConfigs.get(path);
		if(instance==null){
			instance=new ActionMapping();
			actionConfigs.put(path,instance);
		}
		return instance;
		
	}
	
	
	/*
	 * 根据<forward>标签中的name属性找到ActionForward对象
	 * forwards：存放ActionForward对象的集合
	 */
	public ActionForward findForward(String tarName){
		ActionForward forward=forwards.get(tarName);
		return forward;
	}
	
	
	
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getUnknown() {
		return unknown;
	}
	public void setUnknown(String unknown) {
		this.unknown = unknown;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public static ActionMapping getInstance() {
		return instance;
	}
	public static void setInstance(ActionMapping instance) {
		ActionMapping.instance = instance;
	}
	
}
