package com.fd.fashion.modules.navigations.search;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.BaseScreen;


/** 2013-04-09
 * @author zhangling
 *
 */
public class TooltipChrome  extends VelocityNavigation{
	
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
	}
}
