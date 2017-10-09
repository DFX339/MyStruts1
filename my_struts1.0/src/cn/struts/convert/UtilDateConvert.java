package cn.struts.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.commons.beanutils.Converter;

public class UtilDateConvert implements Converter {

	public Object convert(Class arg0, Object arg1) {

		Date date=null;
		//格式化输出日期类
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy:MM:dd");
		try {
			//将字符串arg1转换为java.util.Date类型
			date=sdf.parse(arg1.toString());
		} catch (ParseException e) {
			return null;//当转换异常时，就返回null，让表单不收集这个参数对应的参数值
		}
		return date;
	}

}
