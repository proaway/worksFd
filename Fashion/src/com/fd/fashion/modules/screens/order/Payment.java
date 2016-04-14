/**
 * Payment.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderBean;

/**
 * @CreateDate:  2013-4-12 上午10:58:31 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Payment extends SecureScreen {

	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setCharSet("UTF-8");
		data.setLayoutTemplate("/NoLayout.html");
		
	}
}
