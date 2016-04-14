package com.fd.b2c.manager.modules.actions.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.JSONUtil;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;
import com.fd.util.StringUtil;

/**
 * 
 * @CreateDate: 2013-3-25 下午03:03:44
 * @author Longli
 * @Description: 产品上传页中，选择产品时的查询产品方法
 * 
 */
public class AjaxSearchProduct extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		SearchProductVo searchVo = new SearchProductVo();
		ParametersUtil.initParameters(data, searchVo);
		
		if (data.getParameters().get("orderType") == null) {
			searchVo.setOrderType("2");
		}
		PageInfo pageInfo = new PageInfo();
		ParametersUtil.initParameters(data, pageInfo);
		pageInfo.setPageSize(5);
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");

		IProductManager productManager = (IProductManager) getBean("productManager");
		searchVo = productManager.getProductByKey(searchVo, pageInfo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (ProductVo prod : searchVo.getProductList()) {
			prod.setProductName(StringUtil.substring(prod.getProduct().getProductName(), 60));
			List<CustomCategoryBean> cats = sellerManager.getParentsCats(prod.getProduct().getCustomCatId());
			prod.setCats(cats);
		}
		map.put("products", searchVo.getProductList());
		map.put("pageInfo", pageInfo);
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
