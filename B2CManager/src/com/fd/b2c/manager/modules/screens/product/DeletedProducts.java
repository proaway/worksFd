/**
 * ProductUpload.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.bean.PackageUnitBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.constants.ProductConstants;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductCountVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate:  2013-3-21 上午11:30:40 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class DeletedProducts extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		SearchProductVo searchVo = new SearchProductVo();
		ParametersUtil.initParameters(data, searchVo);
		
		searchVo.setProductStatus(ProductConstants.DELETE);
		
		if (data.getParameters().get("orderType") == null) {
			searchVo.setOrderType("6");
		}
		
		PageInfo pageInfo = new PageInfo();
		ParametersUtil.initParameters(data, pageInfo);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		IProductManager productManager = (IProductManager) getBean("productManager");
		searchVo = productManager.getProductByKey(searchVo, pageInfo);
		if (searchVo.getProductList() != null && searchVo.getProductList().size()>0) {
			for (ProductVo prod : searchVo.getProductList()) {
				String catId = prod.getProduct().getCustomCatId();
				List<CustomCategoryBean> cats = sellerManager.getParentsCats(catId);
				prod.setCats(cats);
			}
		}
		context.put("searchVo", searchVo);
		context.put("pageInfo", pageInfo);
		
		List<ProductCountVo> productCounts = productManager.getProductStatusCount();
		context.put("productCounts", productCounts);
		
		List<PackageUnitBean> packingUnits = dictManager.getPackageUnitBeans(new PackageUnitBean());
		context.put("units", packingUnits);
	}
}
