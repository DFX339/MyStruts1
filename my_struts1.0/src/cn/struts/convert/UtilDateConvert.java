package cn.struts.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.commons.beanutils.Converter;

public class UtilDateConvert implements Converter {

	public Object convert(Class arg0, Object arg1) {

		Date date=null;
		//��ʽ�����������
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy:MM:dd");
		try {
			//���ַ���arg1ת��Ϊjava.util.Date����
			date=sdf.parse(arg1.toString());
		} catch (ParseException e) {
			return null;//��ת���쳣ʱ���ͷ���null���ñ����ռ����������Ӧ�Ĳ���ֵ
		}
		return date;
	}

}
