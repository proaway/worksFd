package com.fd.b2c.manager.modules.screens.category;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;

/**
 * @CreateDate: 2013-4-23 下午02:30:41
 * @author Longli
 * @Description: 自定义类目管理
 * 
 */
public class CategoryManage extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		String condition = data.getParameters().getString("condition");
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<CustomCategoryBean> cats = sellerManager.getCustomChildrenCats(null);
//		List<CustomCategoryBean> cats = sellerManager.getAllCustomCategories();
		int catCount = sellerManager.getAllCustomCategoryCount();
		context.put("catCount", catCount);
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		int productCount = 0;
		for (CustomCategoryBean cat : cats) {
			int count = productManager.getCustomCatProductCount(cat.getCatId());
			cat.setProductCount(count);
			productCount += count;
		}
		context.put("productCount", productCount);
		
//		SearchProductVo searchVo = new SearchProductVo();
//		searchVo.setCatId("");
//		List<CustomCategoryBean> cats = productManager.getSearchProductCatByKey(searchVo);
		
		context.put("cats", cats);
	}
}
