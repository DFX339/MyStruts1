package cn.struts.utils;

import java.util.HashMap;

import cn.struts.extraUtils.GlobalForward;
import cn.struts.extraUtils.PlugIn;

public class ModuleConfig {

	public static HashMap<String,ActionMapping> actionConfigs=new HashMap<String,ActionMapping>();
	
	public static HashMap<String,FormBeanConfig> formConfigs=new HashMap<String ,FormBeanConfig>();
	
	public static HashMap<String,ActionForward> forwards=new HashMap<String ,ActionForward>();
	
	public static HashMap<String,GlobalForward> globalForwards=new HashMap<String ,GlobalForward>();
	
	public static HashMap<String,PlugIn> plugIns=new HashMap<String,PlugIn>();
	
	
}
