package cn.struts.dom;

import java.net.URL;

/**
 * 获取WEB-INF的路径的类
 * @author Administrator
 *
 */
public class GetWebInfPath {
	
	public  String getPath(){
		//得到当前代码存放的路径/web-inf/classes
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();    
		String path = url.toString();    
		int index = path.indexOf("WEB-INF");    
		int start=path.indexOf("/");
		System.out.println(index+"---"+start+"---"+path);
		System.out.println(index+"&***");
		//截取出当前项目的WEB-INF以前的路径
		path = path.substring(start+1, index);   
		System.out.println(path);
		return path;
	}
	
	public static void main(String[] args){
		GetWebInfPath af=new GetWebInfPath();
		af.getPath();
	}
}
