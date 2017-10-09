package cn.struts.dom;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.struts.extraUtils.GlobalForward;
import cn.struts.extraUtils.PlugIn;
import cn.struts.utils.ActionForward;
import cn.struts.utils.ActionMapping;
import cn.struts.utils.FormBeanConfig;
import cn.struts.utils.ModuleConfig;

/**
 * strutDom ��������struts-config.xml�ļ�
 * 
 * @author Administrator
 * 
 */
public class DomStruts {

	public void readStrutsConfigByXml() {

		HashMap<String, FormBeanConfig> formConfigs = ModuleConfig.formConfigs;
		HashMap<String, ActionMapping> actionConfigs = ModuleConfig.actionConfigs;
		HashMap<String, ActionForward> forwards = ModuleConfig.forwards;
		HashMap<String, GlobalForward> gloForwards = ModuleConfig.globalForwards;
		HashMap<String,PlugIn> plugIns=ModuleConfig.plugIns;
		
		GetWebInfPath getWIPath=new GetWebInfPath();
		String webPath=getWIPath.getPath();//�õ���ǰ��Ŀ���WEB-INFĿ¼��·��
		
		System.setProperty("sun.net.client.defaultConnectTimeout", "300000"); 
		System.setProperty("sun.net.client.defaultReadTimeout", "300000"); 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// 1��������������
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();// 2���õ�����Ľ�����
			Document docu;
			try {
				String url=webPath+"WEB-INF/struts-config.xml";
				System.out.println("·����"+url);
				docu = db.parse(new File("F:/Dev_tool/apache-tomcat-7.0.72/webapps/my_struts1.0/WEB-INF/struts-config.xml"));// 3���õ������ı��ĸ����
				NodeList list = docu.getElementsByTagName("struts-config");// 4���õ���Ԫ��
				for (int i = 0; i < list.getLength(); i++) {
					Element ele = (Element) list.item(i);// �õ�struts-config�µ�����������Ϣ

					/*
					 * �õ�form-beans��ǩ�µ����� 1��ѭ��ȡ��form-beans�µ�form-bean��ǩ��Ϣ
					 * 2��ȡ��form-bean��ǩ�еĶ�Ӧ��name��type���Ե�����ֵ
					 * 3����ȡ����form-bean��ǩ��Ϣ��װ����Ӧ��FormBeanConfig������
					 * 4����FormBeanConfig������ӵ�������
					 */
					Element formbeans = (Element) ele.getElementsByTagName(
							"form-beans").item(0);
					int formChild = -1;
					while (formbeans.hasChildNodes()) {
						formChild++;
						// 1��ȡ��form-bean��ǩ��Ϣ
						Element formbean = (Element) formbeans.getElementsByTagName("form-bean").item(formChild);
						if (formbean != null) {
							// 2��ȡ��form-bean��ǩ�е�name��type����ֵ
							String name = formbean.getAttribute("name");
							String type = formbean.getAttribute("type");

							// 3������FormBeanConfig���󣬽���ǩ��Ϣ��װ��FormBeanConfig��
							FormBeanConfig formBeanConfig = new FormBeanConfig();
							formBeanConfig.setName(name);
							formBeanConfig.setType(type);

							// 4����FormBeanConfig������Ϊvalue��name������Ϊkey��װ����̬Map������
							formConfigs.put(name, formBeanConfig);
							System.out.println("formbean:***" + name
									+ "-while-" + type);
						} else {
							break;
						}
					}

					/*
					 * �õ�action-mappings�µ�����������Ϣ
					 * 1��ѭ��ȡ��action-mappings�µ�action��ǩ��Ϣ
					 * 2��ȡ��action��ǩ�еĶ�Ӧ��path,scope
					 * ,forward,unknown,input,name��type���Ե�����ֵ
					 * 3����ȡ����action��ǩ��Ϣ��װ����Ӧ��ActionMapping������
					 * 4����ActionMapping������ӵ������� 5���ж�action��ǩ���Ƿ���forward�ӱ�ǩ
					 * 6������оͼ���ִ�У�ѭ��ȡ��forward��ǩ��Ϣ 7��ȡ��forward��ǩ�еĶ�Ӧ��name path
					 * redirect ���� 8������ActionForward���󣬽�ȡ��������Ϣ��װ���ö�����
					 * 9����ActionForward������ӵ���̬map��
					 */
					Element actionMapping = (Element) ele.getElementsByTagName(
							"action-mappings").item(0);
					int actionChild = -1;
					while (actionMapping.hasChildNodes()) {
						actionChild++;
						// 1��ѭ��ȡ��action-mappings�µ�action��ǩ��Ϣ
						Element action = (Element) actionMapping
								.getElementsByTagName("action").item(
										actionChild);
						if (action != null) {
							// 2��ȡ��action��ǩ�еĸ������Ե�����ֵ
							String path = action.getAttribute("path");
							String name = action.getAttribute("name");
							String type = action.getAttribute("type");
							String scope = action.getAttribute("scope");
							String forward = action.getAttribute("forward");
							String input = action.getAttribute("input");
							String unknown = action.getAttribute("unknown");
							String parameter = action.getAttribute("parameter");

							// 3������ActionMapping���󣬽�action��ǩ�е���Ϣ��װ���ö�����
							ActionMapping mappings = new ActionMapping();
							mappings.setForward(forward);
							mappings.setInput(input);
							mappings.setName(name);
							mappings.setPath(path);
							mappings.setScope(scope);
							mappings.setType(type);
							mappings.setUnknown(unknown);
							mappings.setParameter(parameter);

							// 4������װ��ǩ��Ϣ��ActionMapping������ӵ���̬map��
							actionConfigs.put(path, mappings);
							System.out.println("action:*****" + path + "---"
									+ type + "--" + name + "--" + scope + "--"
									+ forward + "--" + input + "-" + unknown
									+ "--" + parameter);

							int forwardChild = -1;
							// 5���жϸ�action��ǩ���Ƿ���forward��ǩ
							while (action.hasChildNodes()) {
								forwardChild++;
								// 6������оͼ���ִ�У�ѭ��ȡ��forward��ǩ��Ϣ
								Element forward1 = (Element) action.getElementsByTagName("forward").item(forwardChild);

								if (forward1 != null) {
									// * 7��ȡ��forward��ǩ�еĶ�Ӧ��name path redirect ����
									String name1 = forward1.getAttribute("name");
									String path1 = forward1.getAttribute("path");
									String flag = forward1.getAttribute("redirect");

									// ��String���͵�����ת��Ϊboolean����
									boolean redirect = false;
									if ("true".equals(flag)) {
										redirect = true;
									}

									// * 8������ActionForward���󣬽�ȡ��������Ϣ��װ���ö�����
									ActionForward actionForward = ActionForward.getInstance();
									actionForward.setName(name1);
									actionForward.setPath(path1);
									actionForward.setRedirect(redirect);

									// * 9����ActionForward������ӵ���̬Map��
									forwards.put(name1, actionForward);

									System.out.println("forward:***" + name1
											+ "--" + path1 + "--" + redirect);
								} else {
									break;
								}
							}

						} else {
							break;
						}
					}

					/*
					 * ȡ��Plug-In�����������Ϣ
					 */
					Element plugIn = (Element) ele.getElementsByTagName("plug-in").item(0);
					if (plugIn != null) {
						String className = plugIn.getAttribute("className");
						
						PlugIn plug=new PlugIn();//������������
						plug.setClassName(className);
						
						plugIns.put(className, plug);
						
					}else{
						break;
					}
					
					/**
					 * ȫ��<global-forwards>��ǩ�Ľ���
					 * 1���ж�<global-forwards>��ǩ���Ƿ����ӱ�ǩ
					 * 2������оͽ����ӱ�ǩ
					 * 3��ѭ��ȡ���ӱ�ǩ�еĸ�������ֵ
					 * 4������GlobalForward���󣬽��ӱ�ǩ��ֵ��ֵ���ö���
					 * 5�����ö�����Ϊkey,name��Ϊvalue�洢�� map������
					 */
					Element globalForwards=(Element)ele.getElementsByTagName("global-forwards").item(0);
					int globalFowardNum=-1;
					if(globalForwards!=null){
					while(globalForwards.hasChildNodes()){
						globalFowardNum++;
						Element gloForward=(Element)globalForwards.getElementsByTagName("global-forward").item(globalFowardNum);
						
						if(gloForward!=null){
						String name=gloForward.getAttribute("name");
						String path=gloForward.getAttribute("path");
						String gredirect=gloForward.getAttribute("redirect");
						
						GlobalForward globalFor=new GlobalForward();
						globalFor.setName(name);
						globalFor.setPath(path);
						
						boolean gloRedirect=false;
						if("true".equals(gredirect)){
							gloRedirect=true;
						}
						globalFor.setRedirect(gloRedirect);
						
						gloForwards.put(name,globalFor);
					}else{
						break;
					}
				}
			}
					
					
					/**
					 * ����message-resources��ǩ�����ʻ���ǩ��
					 */
					
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

}
