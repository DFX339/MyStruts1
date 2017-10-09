package cn.struts.convert;

import java.math.BigDecimal;

import com.sun.org.apache.commons.beanutils.Converter;

public class BigDecimalConvert implements Converter {
	
	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			BigDecimal dou=new  BigDecimal(arg);
			return dou;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}
}
