package com.fd.b2c.manager.modules.actions.buyer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.JSONUtil;

/**
 * @author zhangling
 * 
 * 检测买家是否存在
 *
 */
public class AjaxCheckUserIdAction extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String userId = data.getParameters().getString("userId");
		
		IBuyerManager buyerManager = (IBuyerManager) this.getBean("buyerManager");
		BuyerBean buyer = null;
		if(StringUtils.isNotEmpty(userId)) {
			buyer = buyerManager.getBuyerUseUserId(userId);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (buyer == null) {
			// 没查到数据，买家不存在
			map.put("status", 0);
		} else {
			map.put("status", 1);
			map.put("buyerId", buyer.getBuyerId());
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

}
