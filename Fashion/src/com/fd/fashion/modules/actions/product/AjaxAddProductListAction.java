package com.fd.fashion.modules.actions.product;

import java.util.Calendar;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.product.bean.BuyerProductListBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

public class AjaxAddProductListAction extends SecureAction {

	public void doPerform(RunData data, Context context) throws Exception {
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		long buyerId = 0;
		if(buyer==null){
			buyerId = 1;
		}else{
			buyerId = buyer.getBuyerId();
		}
		long productId = data.getParameters().getLong("productId", 0);
		
		BuyerProductListBean buyerProductList = new BuyerProductListBean();
		buyerProductList.setBuyerId(buyerId);
		buyerProductList.setProductId(productId);
		Calendar calendar = Calendar.getInstance();
		buyerProductList.setCreateTime(calendar.getTime());
		buyerProductList.setState(0);
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		buyerProductList = productManager.insertBuyerProductListBean(buyerProductList);
		
		if (buyerProductList != null && buyerProductList.getListId() >=0) {
			context.put("contentdata", 1);
		}
	}

}
