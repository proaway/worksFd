package com.fd.fashion.modules.actions.product;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.product.bean.StoceNoticeBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @CreateDate: 2013-3-16 上午11:47:45
 * @author Longli
 * @Description: 添加产品到Stock Notice列表
 * 
 */
public class AjaxAddStockNotice extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		long productId = data.getParameters().getLong("productId", 0);
		long productConfigId = data.getParameters().getLong("productConfigId", 0);
		//判断登录
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		IProductManager productManager = (IProductManager) getBean("productManager");
		//登录缺货记录
		StoceNoticeBean notice = new StoceNoticeBean();
		if(buyer==null){
			notice.setBuyerId(Long.valueOf("1"));
		}else{
			notice.setBuyerId(buyer.getBuyerId());
		}
		notice.setProductId(productId);
		notice.setStandardId(productConfigId);
		notice = productManager.insertStoceNoticeBean(notice);
		if(notice != null){
			context.put("contentdata", JSONUtil.obj2JSON(notice));
		}
	}
}
