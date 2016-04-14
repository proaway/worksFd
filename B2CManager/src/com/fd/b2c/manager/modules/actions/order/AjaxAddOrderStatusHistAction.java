package com.fd.b2c.manager.modules.actions.order;

import java.net.URLDecoder;
import java.util.Date;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.JSONUtil;
import com.fd.util.MailSendServer;

/**
 * 订单详情页写历史状态信息
 * @author zhangling
 *
 */
public class AjaxAddOrderStatusHistAction extends SecureAction {
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		//后台操作人员信息
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		
		long orderId = data.getParameters().getLongObject("orderId");
		String statusCode = data.getParameters().getString("orderStatus");
		String isBuyerSee = data.getParameters().getString("buyerSeeFlag","0");
		String isEmailSend = data.getParameters().getString("emailSendFlag","0");
		String message = data.getParameters().getString("message","");
		message = URLDecoder.decode(message, "utf-8");
		
		OrderstatusInfoBean orderStatusInfo = new OrderstatusInfoBean();
		orderStatusInfo.setCreateTime(new Date());
		orderStatusInfo.setMemo(message);
		orderStatusInfo.setOrderId(orderId);
		orderStatusInfo.setStatus(Integer.parseInt(isBuyerSee));
		orderStatusInfo.setOrderStatus(statusCode);
		orderStatusInfo.setOperator(user.getLoginName());
		
		IOrderStatusManager orderStatusManager = (IOrderStatusManager)getBean("orderStatusManager");
		orderStatusInfo = orderStatusManager.insertOrderStatusInfo(orderStatusInfo);
		
		if(isEmailSend.equals("1")){
			/**写邮件*/
			OrderBean order = new OrderBean();
			order.setOrderId(orderId);
			order = orderStatusManager.getOrder(orderId);
			IBuyerManager buyerManager = (IBuyerManager)this.getBean("buyerManager");
			BuyerBean buyer = buyerManager.getBuyerByBuyerId(order.getBuyerId());
			String email = buyer.getEmail();
			String[] emails = new String[1];
			emails[0] = email;
			MailSendServer server = (MailSendServer) AppContextUtil.getBean("serviceMailSender");
			String subject = "尊敬的买家，"+order.getUserId()+",关于您的订单"+orderId+"有新的回应：";
			server.sendEmail(emails, subject, message, null, null);
		}
		context.put("contentdata", JSONUtil.obj2JSON(orderStatusInfo));
	}

}
