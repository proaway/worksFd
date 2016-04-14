package com.fd.fashion.modules.layouts;

import org.apache.turbine.modules.layouts.VelocityOnlyLayout;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

/**
 * @CreateDate:  2013-3-13 下午07:14:12 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CartLayout extends VelocityOnlyLayout {
	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");

	}
}
