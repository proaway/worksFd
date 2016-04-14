/**
 * ModifyImageCat.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.io.PrintWriter;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;
import com.fd.util.JSONUtil;

/**
 * @CreateDate:  2013-3-26 下午12:36:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxHadSamePropertyAction  extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		//flgg 1：productname  2：sku
		String productName = data.getParameters().getString("productName");
		String sku = data.getParameters().getString("sku");
		String flag = data.getParameters().getString("flag","1");
		
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		ProductBean product = null;
		if(flag.equals("1")){
			product = productManager.getProductBeanByName(productName);
		}else{
			product  = productManager.getProductBeanBySku(sku);
		}
		context.put("contentdata", JSONUtil.obj2JSON(product));
	}

}
