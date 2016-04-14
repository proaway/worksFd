package com.fd.fashion.modules.actions.buyer;

import java.util.ArrayList;
import java.util.Date;
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
import com.fd.fashion.util.Context2Map;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.GetSecurityImg;
import com.fd.util.IPAddrUtil;
import com.fd.util.JSONUtil;
import com.fd.util.MailSendServer;
import com.fd.util.ParametersUtil;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate: 2013-3-14 下午12:38:55
 * @author Longli
 * @Description: 买家登录方法，使用ajax调用，返回json格式数据
 * 
 */
public class AjaxRegister extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = new BuyerBean();
		ParametersUtil.initParameters(data, buyer);
		buyer.setIpAddress(IPAddrUtil.getIpAddr(data.getRequest()));
		buyer.setRegDate(new Date());
		String securityCode = data.getParameters().getString("securityCode");
		String sessionSecurityCode = (String) session.getAttribute(GetSecurityImg.SECURITYCODE);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(securityCode) && securityCode.equals(sessionSecurityCode)) {
			IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
			int status = buyerManager.register(buyer);
			if (status == 1) {
				session.setAttribute(SessionConstants.KEY_LOGIN_BUYER, buyer);
				sendRegisterEmail(context, buyer);
				// 注册成功，读取购物车
				List<CartProductVo> sessionCart = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
				if (sessionCart == null) {
					sessionCart = new ArrayList<CartProductVo>();
				}
				IOrderManager orderManager = (IOrderManager) getBean("orderManager");
				List<CartProductVo> cartProducts = orderManager.getShoppingCart(buyer.getBuyerId());
				if (cartProducts != null && cartProducts.size()>0) {
					for (int i=cartProducts.size()-1; i>=0; i--) {
						CartProductVo cartProduct = cartProducts.get(i);
						long productId = cartProduct.getProductId();
						long standardId = cartProduct.getStandardId()==null ? 0 : cartProduct.getStandardId();
						for (CartProductVo cartProd : sessionCart) {
							long pid = cartProd.getProductId();
							long sid = cartProd.getStandardId()==null ? 0 : cartProd.getStandardId();
							if (pid==productId && sid==standardId) {
								orderManager.deleteShoppingCart(cartProduct, buyer.getBuyerId());
								cartProducts.remove(i);
								break;
							}
						}
					}
				}
				for (CartProductVo cartProduct : sessionCart) {
					orderManager.addShoppingCart(cartProduct, buyer.getBuyerId());
				}
				if (cartProducts != null && cartProducts.size()>0) {
					sessionCart.addAll(cartProducts);
					session.setAttribute(SessionConstants.KEY_CART_PRODUCT, sessionCart);
				}
			}
			map.put("status", status);
			map.put("buyer", buyer.getUserId());
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

	/**
	 * @param context
	 * @param buyer
	 * @throws Exception
	 */
	private void sendRegisterEmail(Context context, BuyerBean buyer)
			throws Exception {
		WebPropUtil propUtil = new WebPropUtil();
		context.put("buyer", buyer);
		context.put("propUtil", propUtil);
		String sendTo[] = new String[]{buyer.getEmail()};
		Map<String, Object> params = Context2Map.context2Map(context);
		String title = "Congratulations on successfully registering at " + propUtil.getProperty("website.webname");
		String templatePath = propUtil.getProperty("email.template.root") + "/" + propUtil.getProperty("email.buyer.register");
		MailSendServer mailSendServer = (MailSendServer) getBean("serviceMailSender");
		mailSendServer.sendEmail(sendTo, title, templatePath, params);
	}
}
