package com.fd.fashion.modules.layouts;

import org.apache.turbine.modules.layouts.VelocityOnlyLayout;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

/**
 * @author zhangling
 * 
 */
public class BuyerAccountLayout extends VelocityOnlyLayout {
	protected void doBuildTemplate(RunData data, Context context)throws Exception {
		data.setCharSet("UTF-8");
	}
}
