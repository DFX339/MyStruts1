package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;
import com.sun.org.apache.xpath.internal.Arg;

/**
 * �������ݵ�ת����
 * @author Administrator
 *
 */
public class IntegerConvert implements Converter {
	
	/**
	 * arg0:
	 * arg1:��ת��������
	 */
	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Integer integer=new  Integer(arg);
			return integer;
		}catch(Exception e){
			return null;//�ݴ���
		}
		
	}

}
