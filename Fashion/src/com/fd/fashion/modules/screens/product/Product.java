package com.fd.fashion.modules.screens.product;


import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.product.constants.ProductConstants;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductConfigsVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.PromotionImageVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.fashion.vo.BreadCrumb;
import com.fd.util.RewriteUtil;

/**
 * ParametersUtil.initParameters(.....);包装参数
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Product extends BaseScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/ProductLayout.html");
		
		ProductVo prod = new ProductVo();
		IProductManager productManager = (IProductManager)getBean("productManager");
		long productId = data.getParameters().getLong("productId");
		prod = productManager.getProductVo(productId);
		
		boolean snapshot = data.getParameters().getBoolean("snapshot", false);
		context.put("snapshot", snapshot);
//		if (prod == null || prod.getProduct().getProductStatus() < 0 || prod.getProduct().getProductStatus() > 1) {
//			setTemplate(data, "404.html");
//			return;
//		}
		
		//赠品
		List<PromotionImageVo> images = productManager.getPromotionImage(productId);
		if(null !=images && images.size()>0){
			context.put("promotionImages", images);
		}
		PriceVo price = productManager.getPriceVo(productId);
		prod.setPriceVo(price);
			
		ProductConfigsVo productConfigs = productManager.getProductConfigsVo(productId, prod.getProduct().getCatId());
		if (productConfigs != null) {
			context.put("productConfigs", productConfigs);
			prod.setProductConfigs(productConfigs);
			productConfigs.setPrice(price);
			if (prod.getProduct().getProductStatus() == null 
					|| ProductConstants.ONSALE != prod.getProduct().getProductStatus()
					|| (prod.getProduct().getExpiredDay()>0 && prod.getExpiredDays()<=0)) {
				productConfigs.emptyStock();
			}
		}
		

		context.put("productVo", prod);
		
		if(null==productConfigs){
			context.put("stockNumber", prod.getProduct().getStock());
		}else{
			context.put("stockNumber", productConfigs.getStockNum());
		}
		
		
		//获取目录导航面包屑
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<CustomCategoryBean> cats = sellerManager.getParentsCats(prod.getProduct().getCustomCatId());
		List<BreadCrumb> breadCrumbs = new ArrayList<BreadCrumb>();
		for (CustomCategoryBean c : cats) {
			BreadCrumb bc = new BreadCrumb();
			bc.setUrl(RewriteUtil.getCategoryUrl(c.getCatName(), c.getCatId()));
			bc.setName(c.getCatName());
			breadCrumbs.add(bc);
		}
		context.put("breadCrumbs", breadCrumbs);
		
	}

}
