package com.fd.fashion.modules.actions.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.GetSecurityImg;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-14 下午12:38:55
 * @author Longli
 * @Description: 买家登录方法，使用ajax调用，返回json格式数据
 * 
 */
public class AjaxLogin extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		FdSession session = FdSessionFactory.getFdSession(data);
		Integer loginError = (Integer)session.getAttribute(SessionConstants.KEY_LOGIN_ERROR);
		int loginErrorCount = 0;
		if (loginError != null) {
			loginErrorCount = loginError.intValue();
		}
		boolean securityVerify = true;
		
		if (loginErrorCount > 3) {
			String securityCode = data.getParameters().getString("securityCode");
			String sessionSecurityCode = (String) session.getAttribute(GetSecurityImg.SECURITYCODE);
			securityVerify = StringUtils.isNotEmpty(securityCode) && securityCode.equals(sessionSecurityCode);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (securityVerify) {
			try {
				String email = data.getParameters().getString("email");
				String password = data.getParameters().getString("password");
				IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
				BuyerBean buyer = buyerManager.login(email, password);
				if (buyer != null) {
					map.put("buyerId", buyer.getBuyerId());
					if(buyer.getFreeze()==1){
						map.put("status", -2);
					}else{
						session.setAttribute(SessionConstants.KEY_LOGIN_BUYER,
								buyer);
						map.put("status", 1);
						session.removeAttribute(SessionConstants.KEY_LOGIN_ERROR);
						// 登录成功，读取购物车
						List<CartProductVo> sessionCart = (List<CartProductVo>) session
								.getAttribute(SessionConstants.KEY_CART_PRODUCT);
						if (sessionCart == null) {
							sessionCart = new ArrayList<CartProductVo>();
						}
						IOrderManager orderManager = (IOrderManager) getBean("orderManager");
						List<CartProductVo> cartProducts = orderManager
								.getShoppingCart(buyer.getBuyerId());
						if (cartProducts != null && cartProducts.size() > 0) {
							for (int i = cartProducts.size() - 1; i >= 0; i--) {
								CartProductVo cartProduct = cartProducts.get(i);
								long productId = cartProduct.getProductId();
								long standardId = cartProduct.getStandardId() == null ? 0
										: cartProduct.getStandardId();
								for (CartProductVo cartProd : sessionCart) {
									long pid = cartProd.getProductId();
									long sid = cartProd.getStandardId() == null ? 0
											: cartProd.getStandardId();
									if (pid == productId && sid == standardId) {
										orderManager.deleteShoppingCart(
												cartProduct, buyer.getBuyerId());
										cartProducts.remove(i);
										break;
									}
								}
							}
						}
						for (CartProductVo cartProduct : sessionCart) {
							orderManager.addShoppingCart(cartProduct, buyer
									.getBuyerId());
						}
						if (cartProducts != null && cartProducts.size() > 0) {
							sessionCart.addAll(cartProducts);
							session.setAttribute(SessionConstants.KEY_CART_PRODUCT,
									sessionCart);
						}
					}
				} else {
					map.put("status", 0);
					loginErrorCount++;
					session.setAttribute(SessionConstants.KEY_LOGIN_ERROR,
							loginErrorCount);
				}
			} catch (Exception e) {
				map.put("status", -1);
			}
		} else {
			map.put("status", -1);
			loginErrorCount++;
			session.setAttribute(SessionConstants.KEY_LOGIN_ERROR, loginErrorCount);
		}
		map.put("loginErrorCount", loginErrorCount);
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
