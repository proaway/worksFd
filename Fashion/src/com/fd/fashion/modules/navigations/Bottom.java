package com.fd.fashion.modules.navigations;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

/**
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Bottom extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		
	}
}
