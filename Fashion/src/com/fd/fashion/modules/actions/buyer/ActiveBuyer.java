package com.fd.fashion.modules.actions.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.BaseAction;

/**
 * @CreateDate: 2013-3-14 下午12:38:55
 * @author Longli
 * @Description: 买家激活邮箱，激活成功后跳转到登录页，失败返回失败信息
 * 
 */
public class ActiveBuyer extends BaseAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String activeCode = data.getParameters().getString("activeCode");
		
		IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
		
		int status = buyerManager.activeBuyerEmail(activeCode);
		if (status >= 1) {
			setTemplate(data, "buyer,Login.html");
			return;
		}
		
		context.put("contentdata", "Active email failed!");
	}
}
