/**
 * ImageZoneLayout.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.layouts;

import org.apache.turbine.modules.screens.VelocityScreen;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

/**
 * @CreateDate:  2013-3-25 上午9:50:01 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ImageZoneLayout extends VelocityScreen{
	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");

	}
}
