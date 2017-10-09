package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class ShortConvert implements Converter {

	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Short sho=new  Short(arg);
			return sho;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}
}
