package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class FloatConvert implements Converter {

	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Float flo=new  Float(arg);
			return flo;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}

}
