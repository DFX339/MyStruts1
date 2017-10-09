package cn.struts.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action {

	static HashMap actions = new HashMap();// keyΪAction����·������valueΪAction����
	private static Action instance = null;

	public Action() {
	}

	public static Action getInstance(String className) {
		instance = (Action) actions.get(className);
		if (instance == null) {
			instance = new Action();
			actions.put(className, instance);
		}
		return instance;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return null;
	}
}
