package cn.struts.dom;

import java.net.URL;

/**
 * ��ȡWEB-INF��·������
 * @author Administrator
 *
 */
public class GetWebInfPath {
	
	public  String getPath(){
		//�õ���ǰ�����ŵ�·��/web-inf/classes
		URL url = getClass().getProtectionDomain().getCodeSource().getLocation();    
		String path = url.toString();    
		int index = path.indexOf("WEB-INF");    
		int start=path.indexOf("/");
		System.out.println(index+"---"+start+"---"+path);
		System.out.println(index+"&***");
		//��ȡ����ǰ��Ŀ��WEB-INF��ǰ��·��
		path = path.substring(start+1, index);   
		System.out.println(path);
		return path;
	}
	
	public static void main(String[] args){
		GetWebInfPath af=new GetWebInfPath();
		af.getPath();
	}
}
