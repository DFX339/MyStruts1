package cn.struts.extraUtils;

import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.ServletException;
import com.sun.org.apache.commons.beanutils.ConvertUtils;

import cn.struts.convert.*;
import cn.struts.extraUtils.PlugIn;
import cn.struts.utils.ActionServlet;
import cn.struts.utils.ModuleConfig;

public class MyPlugIn extends PlugIn{

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
		
		System.out.println("MyPlug----²å¼þ×¢²ástart");
		
		//×¢²á×ª»»Æ÷
		ConvertUtils.register(new UtilDateConvert(), Date.class);
		ConvertUtils.register(new IntegerConvert(), Integer.class);
		ConvertUtils.register(new BigDecimalConvert(), BigDecimal.class);
		ConvertUtils.register(new BooleanConvert(), Boolean.class);
		ConvertUtils.register(new ByteConvert(), Byte.class);
		ConvertUtils.register(new CharacterConverter(), Character.class);
		ConvertUtils.register(new DoubleConvert(), Double.class);
		ConvertUtils.register(new FloatConvert(), Float.class);
		ConvertUtils.register(new LongConvert(),Long.class);
		ConvertUtils.register(new ShortConvert(), Short.class);
		
		System.out.println("MyPlug----²å¼þ×¢²áover");
	}
	
	
}
