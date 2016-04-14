package com.fd.b2c.manager.modules.actions.category;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-21 下午12:00:07
 * @author Longli
 * @Description: Ajax方式获取所有下级类目
 * 
 */
public class AjaxGetCustomCats extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String catId = data.getParameters().getString("catId");
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<CustomCategoryBean> categories = sellerManager.getCustomChildrenCats(catId);
		if (categories != null) {
			IProductManager productManager = (IProductManager) getBean("productManager");
			for (CustomCategoryBean cat : categories) {
				int count = productManager.getCustomCatProductCount(cat.getCatId());
				cat.setProductCount(count);
			}
		}
		
		context.put("contentdata", JSONUtil.list2JSON(categories));
	}
}
