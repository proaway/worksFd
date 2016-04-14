package com.fd.fashion.modules.screens;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

public class Default extends BaseScreen {

	public void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("utf-8");
	}
}
