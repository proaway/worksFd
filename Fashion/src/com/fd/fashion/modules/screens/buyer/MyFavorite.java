package com.fd.fashion.modules.screens.buyer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;

public class MyFavorite extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		String loginError = (String)session.getAttribute(SessionConstants.KEY_LOGIN_ERROR);
		int loginErrorCount = 0;
		if (StringUtils.isNotEmpty(loginError)) {
			loginErrorCount = Integer.valueOf(loginError);
		}
		context.put("loginErrorCount", loginErrorCount);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		IProductManager productManager = (IProductManager)getBean("productManager");
		List<ProductVo> favList = productManager.getBuyerFavorite(buyer.getBuyerId(),pageInfo);
		if(favList!=null && favList.size()>0){
			context.put("favoriteList", favList);
		}
		
	}

}
