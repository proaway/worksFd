package com.fd.fashion.modules.actions.product;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.JSONUtil;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate: 2013-3-16 上午11:47:45
 * @author Longli
 * @Description: 获取一个分组产品的所有配置属性的图片信息
 * 
 */
public class AjaxGetFirstConfigImage extends BaseAction {
	public void doPerform(RunData data, Context context) throws Exception {
		long productConfigId = data.getParameters().getLong("productConfigId", 0);

		IProductManager productManager = (IProductManager) getBean("productManager");
		
		ImageBean image = productManager.getFirstConfigImg(productConfigId);
		if (image != null && image.getImageUrl()!=null && (!"".equals(image.getImageUrl()))) {
			image.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "m"));
			context.put("contentdata", JSONUtil.obj2JSON(image));
		}
	}
}
