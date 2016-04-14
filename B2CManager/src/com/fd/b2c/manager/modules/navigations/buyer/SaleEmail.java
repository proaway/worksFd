package com.fd.b2c.manager.modules.navigations.buyer;

import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.buyer.bean.EmailInfoBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.util.AppContextUtil;
import com.fd.util.PageInfo;

public class SaleEmail extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		/**注入查询信息*/
		long buyerId = (Long)context.get("buyerId");
		IBuyerManager buyerManager = (IBuyerManager)AppContextUtil.getBean("buyerManager");
		EmailInfoBean emailBean = new EmailInfoBean();
		emailBean.setBuyerId(buyerId);
		emailBean.setEmailType(String.valueOf(2));  //2表示营销邮件类型
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		/*int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);*/
		pageInfo.setPageIndex(1);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		List<EmailInfoBean> emailInfos = buyerManager.buyerEmailList(emailBean, pageInfo);
		if(emailInfos!=null && emailInfos.size()>0){
			context.put("emailInfoList", emailInfos);
			context.put("emailListCount", pageInfo.getRecordCount());
		}
	}

}
