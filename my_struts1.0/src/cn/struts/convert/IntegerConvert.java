package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;
import com.sun.org.apache.xpath.internal.Arg;

/**
 * 整型数据的转换器
 * @author Administrator
 *
 */
public class IntegerConvert implements Converter {
	
	/**
	 * arg0:
	 * arg1:被转换的数据
	 */
	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Integer integer=new  Integer(arg);
			return integer;
		}catch(Exception e){
			return null;//容错处理
		}
		
	}

}
