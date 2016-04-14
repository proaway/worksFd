/**
 * ConfirmOrder.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.order;

import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.fashion.seller.vo.ShippingDetailVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;

/**
 * @CreateDate:  2013-4-2 下午4:24:31 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ConfirmOrder extends SecureScreen{
	@SuppressWarnings("unchecked")
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		IBuyerManager buyerManager = (IBuyerManager) AppContextUtil.getBean("buyerManager");
		
		List<BuyerAddressBookBean> books = buyerManager.getBuyerAddressBooks(buyer.getBuyerId());
		String countryId = "230";
		if (books != null && books.size()>0) {
			String defaultAddrId = null;
			for (BuyerAddressBookBean book : books) {
				if(book.getIsDefault() == 1) {
					countryId = String.valueOf(book.getCountry());
					long id = book.getAddressId();
					defaultAddrId = String.valueOf(id);
					break;
				}
			}
			if (StringUtil.isEmpty(defaultAddrId)) {
				countryId = String.valueOf(books.get(0).getCountry());
				defaultAddrId = String.valueOf(books.get(0).getAddressId());
			}
			context.put("defaultAddrId", defaultAddrId);
		}
		context.put("countryId", countryId);
		context.put("books", books);
		
		List<CartProductVo> cartProducts = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		if(cartProducts != null && cartProducts.size() > 0){
			context.put("cartProducts", cartProducts);
		}
		RewriteUtil ru = new RewriteUtil();
		context.put("ru", ru);
		context.put("cartStep", 2);
	}
}
