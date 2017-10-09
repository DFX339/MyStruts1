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
 * strutDom 用来解析struts-config.xml文件
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
		String webPath=getWIPath.getPath();//得到当前项目相对WEB-INF目录的路径
		
		System.setProperty("sun.net.client.defaultConnectTimeout", "300000"); 
		System.setProperty("sun.net.client.defaultReadTimeout", "300000"); 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();// 1、创建解析工厂
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();// 2、得到具体的解析器
			Document docu;
			try {
				String url=webPath+"WEB-INF/struts-config.xml";
				System.out.println("路径："+url);
				docu = db.parse(new File("F:/Dev_tool/apache-tomcat-7.0.72/webapps/my_struts1.0/WEB-INF/struts-config.xml"));// 3、得到解析文本的根结点
				NodeList list = docu.getElementsByTagName("struts-config");// 4、得到根元素
				for (int i = 0; i < list.getLength(); i++) {
					Element ele = (Element) list.item(i);// 得到struts-config下的所有配置信息

					/*
					 * 得到form-beans标签下的配置 1、循环取出form-beans下的form-bean标签信息
					 * 2、取得form-bean标签中的对应的name和type属性的属性值
					 * 3、将取出的form-bean标签信息封装到对应的FormBeanConfig对象中
					 * 4、将FormBeanConfig对象添加到集合中
					 */
					Element formbeans = (Element) ele.getElementsByTagName(
							"form-beans").item(0);
					int formChild = -1;
					while (formbeans.hasChildNodes()) {
						formChild++;
						// 1、取出form-bean标签信息
						Element formbean = (Element) formbeans.getElementsByTagName("form-bean").item(formChild);
						if (formbean != null) {
							// 2、取出form-bean标签中的name和type属性值
							String name = formbean.getAttribute("name");
							String type = formbean.getAttribute("type");

							// 3、创建FormBeanConfig对象，将标签信息封装到FormBeanConfig中
							FormBeanConfig formBeanConfig = new FormBeanConfig();
							formBeanConfig.setName(name);
							formBeanConfig.setType(type);

							// 4、将FormBeanConfig对象作为value，name属性作为key封装到静态Map集合中
							formConfigs.put(name, formBeanConfig);
							System.out.println("formbean:***" + name
									+ "-while-" + type);
						} else {
							break;
						}
					}

					/*
					 * 得到action-mappings下的所有配置信息
					 * 1、循环取出action-mappings下的action标签信息
					 * 2、取得action标签中的对应的path,scope
					 * ,forward,unknown,input,name和type属性的属性值
					 * 3、将取出的action标签信息封装到对应的ActionMapping对象中
					 * 4、将ActionMapping对象添加到集合中 5、判断action标签下是否有forward子标签
					 * 6、如果有就继续执行，循环取出forward标签信息 7、取得forward标签中的对应的name path
					 * redirect 属性 8、创建ActionForward对象，将取出来的信息封装到该对象中
					 * 9、将ActionForward对象添加到静态map中
					 */
					Element actionMapping = (Element) ele.getElementsByTagName(
							"action-mappings").item(0);
					int actionChild = -1;
					while (actionMapping.hasChildNodes()) {
						actionChild++;
						// 1、循环取出action-mappings下的action标签信息
						Element action = (Element) actionMapping
								.getElementsByTagName("action").item(
										actionChild);
						if (action != null) {
							// 2、取出action标签中的各个属性的属性值
							String path = action.getAttribute("path");
							String name = action.getAttribute("name");
							String type = action.getAttribute("type");
							String scope = action.getAttribute("scope");
							String forward = action.getAttribute("forward");
							String input = action.getAttribute("input");
							String unknown = action.getAttribute("unknown");
							String parameter = action.getAttribute("parameter");

							// 3、创建ActionMapping对象，将action标签中的信息封装到该对象中
							ActionMapping mappings = new ActionMapping();
							mappings.setForward(forward);
							mappings.setInput(input);
							mappings.setName(name);
							mappings.setPath(path);
							mappings.setScope(scope);
							mappings.setType(type);
							mappings.setUnknown(unknown);
							mappings.setParameter(parameter);

							// 4、将封装标签信息的ActionMapping对象添加到静态map中
							actionConfigs.put(path, mappings);
							System.out.println("action:*****" + path + "---"
									+ type + "--" + name + "--" + scope + "--"
									+ forward + "--" + input + "-" + unknown
									+ "--" + parameter);

							int forwardChild = -1;
							// 5、判断该action标签下是否有forward标签
							while (action.hasChildNodes()) {
								forwardChild++;
								// 6、如果有就继续执行，循环取出forward标签信息
								Element forward1 = (Element) action.getElementsByTagName("forward").item(forwardChild);

								if (forward1 != null) {
									// * 7、取得forward标签中的对应的name path redirect 属性
									String name1 = forward1.getAttribute("name");
									String path1 = forward1.getAttribute("path");
									String flag = forward1.getAttribute("redirect");

									// 将String类型的数据转换为boolean类型
									boolean redirect = false;
									if ("true".equals(flag)) {
										redirect = true;
									}

									// * 8、创建ActionForward对象，将取出来的信息封装到该对象中
									ActionForward actionForward = ActionForward.getInstance();
									actionForward.setName(name1);
									actionForward.setPath(path1);
									actionForward.setRedirect(redirect);

									// * 9、将ActionForward对象添加到静态Map中
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
					 * 取得Plug-In插件的配置信息
					 */
					Element plugIn = (Element) ele.getElementsByTagName("plug-in").item(0);
					if (plugIn != null) {
						String className = plugIn.getAttribute("className");
						
						PlugIn plug=new PlugIn();//创建插件类对象
						plug.setClassName(className);
						
						plugIns.put(className, plug);
						
					}else{
						break;
					}
					
					/**
					 * 全局<global-forwards>标签的解析
					 * 1、判断<global-forwards>标签下是否有子标签
					 * 2、如果有就解析子标签
					 * 3、循环取出子标签中的各个属性值
					 * 4、创建GlobalForward对象，将子标签的值赋值给该对象
					 * 5、将该对象作为key,name作为value存储到 map集合中
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
					 * 解析message-resources标签（国际化标签）
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
