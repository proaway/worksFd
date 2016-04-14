package com.fd.fashion.modules.actions;

/**
 * @author LiuXu
 * @date 2005-11-29
 *
 */
import org.apache.turbine.modules.actions.VelocityAction;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fd.util.AppContextUtil;

public class BaseAction extends VelocityAction {

	private WebApplicationContext ctx = null;

	public BaseAction() {
	}

	public Object getBean(String name) {
		return AppContextUtil.getBean(name);
	}

	public void doBaseAction(RunData data, Context context) throws Exception {
	}

	public void doPerform(RunData data, Context context) throws Exception {
		return;
	}

	public boolean validateuser(RunData data, String name, String password)
			throws Exception {
		if (ctx == null) {
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(data.getServletContext());
		}
		return false;
	}

}