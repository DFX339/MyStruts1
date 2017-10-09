package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class ByteConvert implements Converter{
	
	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Byte by=new  Byte(arg);
			return by;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}
}
