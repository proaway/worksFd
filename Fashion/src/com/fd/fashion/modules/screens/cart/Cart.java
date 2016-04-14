/**
 * Cart.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.modules.screens.cart;

import java.util.Calendar;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate:  2013-3-29 下午5:11:18 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class Cart extends BaseScreen{
	@SuppressWarnings("unchecked")
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/CartLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		List<CartProductVo> cartProducts = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		if(cartProducts != null && cartProducts.size() > 0){
			context.put("cartProducts", cartProducts);
		}
		RewriteUtil ru = new RewriteUtil();
		context.put("ru", ru);
		
		IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
		List<PaymentTypeBean> paymentTypes = paymentManager.getEnabledPayments();
		for (PaymentTypeBean payment : paymentTypes) {
			if (payment.getPaymentType() == 1) {
				context.put("usePaypal", true);
				break;
			}
		}
		//获取最近15天内的5个热销产品
		IProductManager productManager = (IProductManager)getBean("productManager");
		SearchProductVo searchProductVo = new SearchProductVo();
		Calendar cal = Calendar.getInstance();
		searchProductVo.setCreateDateEnd(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, -15);
		searchProductVo.setCreateDateStart(cal.getTime());
		List<ProductVo> hotProducts = productManager.getHotProductsByTime(searchProductVo);
		if(hotProducts!=null && hotProducts.size()>0){
			context.put("hotProducts", hotProducts);
		}
	}

}
