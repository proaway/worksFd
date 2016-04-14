/**
 * AddImageCat.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import java.net.URLDecoder;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.BaseScreen;
import com.fd.fashion.product.bean.ImageCatBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;
import com.fd.util.JSONUtil;

/**
 * @CreateDate:  2013-3-26 上午11:17:58 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AddImageCat extends BaseScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		String catName = data.getParameters().getString("catName");
		catName = URLDecoder.decode(catName, "utf-8");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		ImageCatBean icb = productManager.addImagesCat(catName);
		context.put("imageCat", JSONUtil.obj2JSON(icb));
	}

}
