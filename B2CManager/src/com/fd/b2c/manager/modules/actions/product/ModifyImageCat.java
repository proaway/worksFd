/**
 * ModifyImageCat.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ImageCatBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;

/**
 * @CreateDate:  2013-3-26 下午12:36:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ModifyImageCat  extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		//1：修改  2：删除
		String status = data.getParameters().getString("status");
		String catName = data.getParameters().getString("catName");
		String catId = data.getParameters().getString("catId");
		
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		ImageCatBean icb = new ImageCatBean();
		icb.setCatId(Long.valueOf(catId));
		icb.setCatName(catName);
		if("1".equals(status)){
			productManager.updateImagesCat(icb);
		}else if("2".equals(status)){
			productManager.deleteImagesCat(icb);
		}
	}

}
