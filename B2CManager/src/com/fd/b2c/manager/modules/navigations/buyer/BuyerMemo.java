package com.fd.b2c.manager.modules.navigations.buyer;

import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.buyer.bean.BuyerMemoBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.AppContextUtil;

public class BuyerMemo extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		IBuyerManager buyerManager = (IBuyerManager)AppContextUtil.getBean("buyerManager");
		long buyerId =  ((Long)context.get("buyerId")).longValue();
		/**买家备注列表*/
		List<BuyerMemoBean> listMemo = buyerManager.getBuyerMemoList(buyerId);
		if(listMemo!=null && listMemo.size()>0){
			context.put("buyerMemoList", listMemo);
		}
	}

}
