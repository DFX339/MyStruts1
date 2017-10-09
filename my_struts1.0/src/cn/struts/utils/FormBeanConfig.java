package cn.struts.utils;

import java.util.HashMap;

public class FormBeanConfig {
	
	//���FormBeanConfig����ļ��ϣ�FormBeanConfig������Ϊkey����ô��Ϊvalue
	static HashMap<String,FormBeanConfig> formConfigs=ModuleConfig.formConfigs;
	private String name;
	private String type;
	
	/*
	 * ��������ActionForm����
	 */
	private static FormBeanConfig instance=null;
	public FormBeanConfig(){}
	public static FormBeanConfig getInstance(String name){
		instance = formConfigs.get(name);
		if(instance==null){
			instance=new FormBeanConfig();
			formConfigs.put(name, instance);
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
