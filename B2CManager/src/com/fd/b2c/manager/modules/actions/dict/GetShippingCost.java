package com.fd.b2c.manager.modules.actions.dict;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.ShippingCostBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-29 下午03:21:48
 * @author Longli
 * @Description: 获取指定物流方式对应的国家列表
 * 
 */
public class GetShippingCost extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String shippingCompany = data.getParameters().getString("shippingCompany");
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		if (shippingCompany.startsWith("custCompany")) {
			shippingCompany = "CUSTOM";
		}
		List<ShippingCostBean> shippingCosts = dictManager.getShippingCosts(shippingCompany);
		if (shippingCosts != null && shippingCosts.size()>0) {
			context.put("contentdata", JSONUtil.list2JSON(shippingCosts));
		} else {
			context.put("contentdata", "[]");
		}
	}
}
