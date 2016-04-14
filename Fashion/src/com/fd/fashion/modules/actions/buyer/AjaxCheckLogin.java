package com.fd.fashion.modules.actions.buyer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-14 下午12:38:55
 * @author Longli
 * @Description: 买家注册是校验买家是否登录的方法，使用ajax调用，返回json格式数据，同时返回买家购物车产品数量
 * 
 */
public class AjaxCheckLogin extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (buyer == null) {
			// 未登录
			map.put("LoginFlag", 0);
		} else {
			map.put("LoginFlag", 1);
			map.put("userId", buyer.getUserId());
		}
		
		// 获取购物车数量
		List<HashMap<String,Object>> cartProducts = (List<HashMap<String,Object>>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		int size = 0;
		if (cartProducts != null) {
			size = cartProducts.size();
		}
		map.put("Quantity", size);
		
		context.put("contentdata", "var info = " + JSONUtil.obj2JSON(map) + ";");
	}
}
