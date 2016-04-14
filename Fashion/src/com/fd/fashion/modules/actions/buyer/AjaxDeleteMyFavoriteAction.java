package com.fd.fashion.modules.actions.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * @author zhangling
 * 某个用户从所喜欢的产品中移除
 *
 */
public class AjaxDeleteMyFavoriteAction extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		Integer productId = data.getParameters().getIntObject("productId");
		if(productId!=null && productId>0){
			IProductManager productManager = (IProductManager)getBean("productManager");
			Integer count = productManager.deleteBuyerFavorite(buyer.getBuyerId(), productId);
			if(count!=null&&count>0){
				context.put("contentdata", 1);
			}
		}
	}


}
