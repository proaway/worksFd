package com.fd.b2c.manager.modules.screens.buyer;

import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.buyer.bean.EmailInfoBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

public class SystemEmailList extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		Long buyerId = data.getParameters().getLongObject("buyerId");
		context.put("buyerId", buyerId);
		
		IBuyerManager buyerManager = (IBuyerManager)AppContextUtil.getBean("buyerManager");
		EmailInfoBean emailBean = new EmailInfoBean();
		emailBean.setBuyerId(buyerId);
		emailBean.setEmailType(String.valueOf(1));  //1表示系统邮件类型 
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		List<EmailInfoBean> emailInfos = buyerManager.buyerEmailList(emailBean, pageInfo);
		if(emailInfos!=null && emailInfos.size()>0){
			context.put("emailInfos", emailInfos);
		}
	}

}
