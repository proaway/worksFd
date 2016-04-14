package com.fd.b2c.manager.modules.actions.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.PriceVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate: 2013-3-26 上午09:58:46
 * @author Longli
 * @Description: 获取赠品产品信息
 * 
 */
public class AjaxGetGiftProduct extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		long productIds[] = data.getParameters().getLongs("giftProductId");
		if (productIds == null) {
			productIds = data.getParameters().getLongs("groupProdId");
		}
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		List<ProductVo> products = new ArrayList<ProductVo>();
		
		for (long productId : productIds) {
			ProductVo product = productManager.getProductVo(productId);
			product.getProduct().setProductName(StringUtil.stringformat(product.getProduct().getProductName(), 20));
			PriceVo price = productManager.getPriceVo(productId);
			product.setPriceVo(price);
			ImageBean image = productManager.getFirstProdImageBean(productId);
			product.setFirstImage(image);
			products.add(product);
		}
		context.put("contentdata", JSONUtil.list2JSON(products));
	}
}
