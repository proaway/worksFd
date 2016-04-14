package com.fd.fashion.modules.layouts;

import org.apache.turbine.modules.layouts.VelocityOnlyLayout;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

/**
 * @CreateDate:  2013-4-09 
 * @author:  zhangling
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductsListLayout extends VelocityOnlyLayout {
	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");

	}
}
