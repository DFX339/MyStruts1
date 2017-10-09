package cn.struts.utils;

import java.util.HashMap;

public class ActionForward {
	
	/*
	 * ���Map���ϵ�value���ActionForward����,keyΪname����
	 */
	static HashMap<String,ActionForward> forwards=ModuleConfig.forwards;
	static private  String name;
	private boolean redirect;//��ʶ��ת��ʽ��falseΪ����ˣ�true Ϊ�ͻ�����ת
	private String path;
	
	
	//����ʽMap����+ͬ������ActionForward����
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
