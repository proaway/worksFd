package com.fd.fashion.modules.screens.buyer;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @CreateDate: 2013-3-14 下午12:13:57
 * @author Longli
 * @Description: 登录页面 buyer,Login.html
 * 
 */
public class Login extends BaseScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		String returnUrl = data.getParameters().getString("returnUrl");
		context.put("returnUrl", returnUrl);
		
		Integer loginError = (Integer)session.getAttribute(SessionConstants.KEY_LOGIN_ERROR);
		int loginErrorCount = 0;
		if (loginError != null) {
			loginErrorCount = loginError.intValue();
		}
		context.put("loginErrorCount", loginErrorCount);
	}
}
