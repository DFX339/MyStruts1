package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class CharacterConverter  implements Converter{
	
   public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		
		try{
			Character ch=new  Character(arg.charAt(0));
			return ch;
		}catch(Exception e){
			return null;//»›¥Ì¥¶¿Ì
		}
	}
}
