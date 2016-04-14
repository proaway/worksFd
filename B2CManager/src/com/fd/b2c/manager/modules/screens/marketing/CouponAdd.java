/**
 * CouponAdd.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.marketing;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @CreateDate:  2013-4-24 上午10:30:49 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class CouponAdd  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		
	}

}
