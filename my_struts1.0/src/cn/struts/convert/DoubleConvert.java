package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class DoubleConvert implements Converter {

	public Object convert(Class arg0, Object arg1) {
			
		String arg=(String)arg1;
		
		try{
			Double dou=new  Double(arg);
			return dou;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}

}
