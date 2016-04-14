package com.fd.fashion.modules.actions.cart;

import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.fashion.seller.vo.ShippingDetailVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-4-15 下午02:40:09
 * @author Longli
 * @Description: 获取购物车的运费信息
 * 
 */
public class GetCartShippingCost extends SecureAction {
	@SuppressWarnings("unchecked")
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		String country = data.getParameters().getString("country");

		double weight = 0;
		int length = 0;
		int width = 0;
		int height = 0;
		List<CartProductVo> cartProducts = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		if(cartProducts != null && cartProducts.size() > 0){
			for (CartProductVo cartProd : cartProducts) {
				weight += cartProd.getWeight()*cartProd.getQuantity();
				length += cartProd.getLength()*cartProd.getQuantity();
				width += cartProd.getWidth()*cartProd.getQuantity();
				height += cartProd.getHeight()*cartProd.getQuantity();
			}
			IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
			List<ShippingDetailVo> shipppingDetails = shippingManager.getShippingDetails(weight, length, width, height, country);
			context.put("contentdata", JSONUtil.list2JSON(shipppingDetails));
		}
	}
}
