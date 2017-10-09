package cn.struts.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.struts.extraUtils.DispatchAction;
import cn.struts.utils.Action;
import cn.struts.utils.ActionForm;
import cn.struts.utils.ActionForward;
import cn.struts.utils.ActionMapping;

public class UserDispatchAction extends DispatchAction{

	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
//		ActionForward forward=mapping.findForward("suc");
		ActionForward forward=new ActionForward();
		forward.setPath("/main.jsp");
		return forward;
	}

	
	
	
}
