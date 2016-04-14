package com.fd.b2c.manager.modules.screens.user;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

public class ChangeUserPSW extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		long userId = data.getParameters().getLong("userId");
		String loginName = data.getParameters().getString("loginName");
		
		context.put("userId", userId);
		context.put("loginName", loginName);
	}
}
