/**
 * ProductStandard.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.navigations.product;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

/**
 * @CreateDate:  2013-3-21 下午3:52:37 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductStandard extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		
	}

}
