package cn.struts.utils;

import java.util.HashMap;

public class ActionMapping {

	HashMap<String,ActionForward> forwards=ModuleConfig.forwards;
	static HashMap<String,ActionMapping> actionConfigs=ModuleConfig.actionConfigs;
	private String path;//����·��
	private String name;//���õ�ActionForm
	private String scope="session";//�洢��
	private String type;//Action����·����
	private String forward;//��תҳ��·��
	private String unknown;//�ݴ���
	private String input;//����ҳ��
	private String parameter;//ʹ��DispatchActionʱָ��������������
	
	
	//ͨ����������ActionMapping ������Ҫ���ݲ��� path��
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
	 * ����<forward>��ǩ�е�name�����ҵ�ActionForward����
	 * forwards�����ActionForward����ļ���
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
