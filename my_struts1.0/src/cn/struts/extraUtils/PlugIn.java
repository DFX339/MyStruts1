package cn.struts.extraUtils;

import java.util.HashMap;

import javax.servlet.ServletException;

import cn.struts.utils.ActionServlet;
import cn.struts.utils.ModuleConfig;


/**
 * ��������ӿ�
 * @author Administrator
 *
 */
public class PlugIn {
	
	static HashMap<String,PlugIn> plugIns=ModuleConfig.plugIns;
	private static PlugIn plug=null;
	public PlugIn(){}
	public static PlugIn getInstance(String className){
		plug=plugIns.get(className);
		if(plug==null){
			plug=new PlugIn();
			plugIns.put(className,plug);
		}
		return plug;
	}
	
	private String className;//��ǰ�����·����
	
	//��ʼ������
	public void init(ActionServlet servlet, ModuleConfig config)throws ServletException{}
	
	//���ٵķ���
	public void destroy(){}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
