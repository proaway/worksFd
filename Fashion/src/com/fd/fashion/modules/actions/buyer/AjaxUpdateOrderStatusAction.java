package com.fd.fashion.modules.actions.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;

/**
 * @author zhangling
 *
 */
public class AjaxUpdateOrderStatusAction extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		String actionFlag = data.getParameters().getString("actionFlag");
		Long orderId = data.getParameters().getLongObject("orderId");
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)getBean("orderStatusManager");
		OrderVo orderVo  = null;
		if(actionFlag.equals("refundOrder")){
			OrderBean order = new OrderBean();
			order.setOrderId(orderId);
			orderVo = orderStatusManager.statusToOR201(order, buyer.getUserId());
		}else if(actionFlag.equals("cancelOrder")){
			OrderBean order = new OrderBean();
			order.setOrderId(orderId);
			 orderVo = orderStatusManager.cancelOrder(order, buyer.getUserId());
		}else if(actionFlag.equals("completeOrder")){
			OrderBean order = new OrderBean();
			order.setOrderId(orderId);
			 orderVo = orderStatusManager.os005ToOc101(order, buyer.getUserId());
		}
		
		if(orderVo!=null){
			context.put("ord", orderVo);
			setTemplate(data, "buyer,StatusChangeOpertor.html");
		}
	}

}
