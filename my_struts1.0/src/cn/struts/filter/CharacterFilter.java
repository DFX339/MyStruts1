package cn.struts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ÎÄ×Ö±àÂë¹ýÂËÆ÷
 * @author DFX
 * @version1.0 2017/8/29
 * */
public class CharacterFilter implements Filter {

	private String encoding=null;
	
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		response.setContentType("text/html;charset=UTF-8");
		
	}

	public void init(FilterConfig config) throws ServletException {
		encoding=config.getInitParameter("encoding");
	}

}
