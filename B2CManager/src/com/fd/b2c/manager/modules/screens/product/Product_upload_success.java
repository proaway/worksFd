/**
 * Product_upload_success.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;

/**
 * @CreateDate:  2013-3-29 下午4:19:19 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Product_upload_success extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
	}

}
