package com.fd.fashion.modules.navigations.buyer;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

public class BuyerPath extends VelocityNavigation {
	
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		if(buyer!=null){
			context.put("buyerVo", buyer);
		}
		
		String flag = data.getParameters().getString("pageFlag","");
		if(!flag.equals("")){
			context.put("pageFlag", flag);
		}
	}

}
