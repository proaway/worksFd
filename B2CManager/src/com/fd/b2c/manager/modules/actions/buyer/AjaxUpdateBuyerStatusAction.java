package com.fd.b2c.manager.modules.actions.buyer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.MD5Util;
import com.fd.util.StringUtil;

public class AjaxUpdateBuyerStatusAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		String buyerId = data.getParameters().getString("ids");
		String flag = data.getParameters().getString("flag");
		if (null == buyerId || "".equals(buyerId)) {
			context.put("contentdata", 0);
		} else {
			String[] ids = buyerId.split(",");
			List<BuyerBean> buyerList = new ArrayList<BuyerBean>();
			if (flag.equals("1")) {// flag==1时执行激活操作
				for (String id : ids) {
					BuyerBean buyer = new BuyerBean();
					String code = MD5Util.calcMD5(Math.random()* 10+ StringUtil.getDateString(new Date(),"yyyyMMddHHmmssSSS" + Math.random() * 10));
					buyer.setEmailActive(1);
					buyer.setActiveCode(code);
					buyer.setBuyerId(Long.valueOf(id));
					buyerList.add(buyer);
				}
			} else if (flag.equals("2")) { // flag==2时执行冻结操作
				for (String id : ids) {
					BuyerBean buyer = new BuyerBean();
					buyer.setFreeze(1);
					buyer.setBuyerId(Long.valueOf(id));
					buyerList.add(buyer);
				}
			}else if(flag.equals("3")){//flag==3时执行解冻操作
				for (String id : ids) {
					BuyerBean buyer = new BuyerBean();
					buyer.setFreeze(0);
					buyer.setBuyerId(Long.valueOf(id));
					buyerList.add(buyer);
				}
			}
			IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
			Integer updatCount = buyerManager.updateBuyerBatch(buyerList);
			if(updatCount>0){
				context.put("contentdata", updatCount);
			}
		}
	}
}
