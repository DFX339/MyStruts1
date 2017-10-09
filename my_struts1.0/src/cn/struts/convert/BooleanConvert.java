package cn.struts.convert;

import com.sun.org.apache.commons.beanutils.Converter;

public class BooleanConvert  implements Converter{

	public Object convert(Class arg0, Object arg1) {
		
		String arg=(String)arg1;
		Boolean flag=new Boolean(true);
	
		
		if(arg.equalsIgnoreCase("y")||
			arg.equalsIgnoreCase("yes")||
			arg.equalsIgnoreCase("t")||
			arg.equalsIgnoreCase("true")||
			arg.equalsIgnoreCase("1")){
			return flag;
		}else if(arg.equalsIgnoreCase("n")||
			arg.equalsIgnoreCase("no")||
			arg.equalsIgnoreCase("f")||
			arg.equalsIgnoreCase("false")||
			arg.equalsIgnoreCase("0")){
			flag=new Boolean(false);
			return flag;
			
		}
		
		return null;
	}

}
