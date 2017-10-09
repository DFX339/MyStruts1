package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class LongConvert  implements Converter{

	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Long lo=new  Long(arg);
			return lo;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}
}
