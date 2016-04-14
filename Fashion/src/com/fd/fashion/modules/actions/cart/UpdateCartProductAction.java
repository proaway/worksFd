/**
 * AddToCartAction.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.actions.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate:  2013-4-1 下午2:49:03 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class UpdateCartProductAction extends BaseAction {
	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		long productId = data.getParameters().getLong("productId");
		long standardId = data.getParameters().getLong("standardId");
		int quantity = data.getParameters().getInt("quantity");
		
		if (quantity <= 0) {
			return;
		}
		
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		List<CartProductVo> cartProducts = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		if(cartProducts != null && cartProducts.size() > 0){
			for(int i=0;i<cartProducts.size();i++){
				CartProductVo cartProd = cartProducts.get(i);
				long pid = cartProd.getProductId();
				long sid = cartProd.getStandardId()==null?0:cartProd.getStandardId();
				if(productId == pid && standardId==sid){
					cartProd.setQuantity(quantity);
					if (buyer != null) {
						IOrderManager orderManager = (IOrderManager) getBean("orderManager");
						orderManager.updateShoppingCart(cartProd, buyer.getBuyerId());
					}
					break;
				}
			}
		}
		session.setAttribute(SessionConstants.KEY_CART_PRODUCT, cartProducts);
		context.put("contentdata", cartProducts.size());

	}
	
}
