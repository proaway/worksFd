package com.fd.b2c.manager.modules.actions.user;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.BaseAction;
import com.fd.constants.SessionConstants;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

public class LogoutAction extends BaseAction {
	
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		FdSession session = FdSessionFactory.getFdSession(data);
		session.removeAttribute(SessionConstants.KEY_LOGIN_USER);
		data.getResponse().sendRedirect(data.getRequest().getContextPath()
								+ "/template/user,Login.html");
	}
}
