/**
 * ProductUploadAction.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ProductImageBean;
import com.fd.fashion.product.manager.IProductManager;

/**
 * @CreateDate:  2013-3-26 下午8:12:26 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AjaxProductImageDelete extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		
		long productId = data.getParameters().getLong("productId");
		long imgId = data.getParameters().getLong("imgId");
		
		IProductManager productManager = (IProductManager)this.getBean("productManager");
		ProductImageBean pib = new ProductImageBean();
		pib.setImageId(imgId);
		pib.setProductId(productId);
		productManager.deleteProductImage(pib);
	}

}
