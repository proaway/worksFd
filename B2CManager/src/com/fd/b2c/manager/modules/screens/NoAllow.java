package com.fd.b2c.manager.modules.screens;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class NoAllow extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		
	}	
}
