package com.fd.b2c.manager.modules.screens.user;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.BaseScreen;

public class Login extends BaseScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
		String returnUrl = data.getParameters().getString("returnUrl");
		context.put("returnUrl", returnUrl);
	}
}
