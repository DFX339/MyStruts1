package cn.struts.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.struts.utils.Action;
import cn.struts.utils.ActionForm;
import cn.struts.utils.ActionForward;
import cn.struts.utils.ActionMapping;

public class UserAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward=mapping.findForward("suc");
		return forward;
	}

	
	
	
}
