package cn.struts.extraUtils;

import java.util.HashMap;

import cn.struts.utils.ActionForward;
import cn.struts.utils.ModuleConfig;

/**
 * ȫ����ת����
 * @author Administrator
 *
 */
public class GlobalForward {

	/*
	 * ���Map���ϵ�value���GlobalForward����,keyΪname����
	 */
	static HashMap<String,GlobalForward> globalForwards=ModuleConfig.globalForwards;
	static private  String name;
	private boolean redirect;//��ʶ��ת��ʽ��falseΪ����ˣ�true Ϊ�ͻ�����ת
	private String path;
	
	
	//����ʽMap����+ͬ������ActionForward����
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
