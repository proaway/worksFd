package com.fd.fashion.modules.actions.buyer;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.buyer.service.IBuyerService;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.util.GetSecurityImg;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-3-14 下午12:38:55
 * @author Longli
 * @Description: 买家注册是校验买家email的方法，使用ajax调用，返回json格式数据
 * 
 */
public class AjaxCheckEmail extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String email = data.getParameters().getString("email");
		
		IBuyerManager buyerManager = (IBuyerManager) this.getBean("buyerManager");
		BuyerBean buyer = null;
		if(StringUtils.isNotEmpty(email)) {
			buyer = buyerManager.getBuyerUseEmail(email.toLowerCase());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (buyer == null) {
			// 没查到数据，校验通过
			map.put("status", 1);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
