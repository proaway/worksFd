package com.fd.fashion.modules.actions.buyer;

import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.fashion.util.Context2Map;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.DESadeUtil;
import com.fd.util.JSONUtil;
import com.fd.util.MailSendServer;
import com.fd.util.WebPropUtil;

public class AjaxChangeEmailAction  extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
		String password = data.getParameters().getString("password");
		String emailAddr = data.getParameters().getString("emailAddr");
		System.out.println(DESadeUtil.decryptMode(buyer.getPassword()));
		if (DESadeUtil.decryptMode(buyer.getPassword()).equals(password)) {
			BuyerBean buyerBean = new BuyerBean();
			buyerBean.setBuyerId(buyer.getBuyerId());
			buyerBean.setEmail(emailAddr);
			buyerBean.setEmailActive(0);
			Integer count = buyerManager.updateBuyer(buyerBean);
			if (count != null && count > 0) {
				buyer.setEmail(emailAddr);
				buyer.setEmailActive(null);
				session.setAttribute(SessionConstants.KEY_LOGIN_BUYER,
						buyer);
				sendRegisterEmail(context, buyer);
				context.put("contextdata", JSONUtil.obj2JSON(buyer));
			} 
		}
	}
	
	/**
	 * @param context
	 * @param buyer
	 * @throws Exception
	 */
	private void sendRegisterEmail(Context context, BuyerBean buyer)
			throws Exception {
		WebPropUtil propUtil = new WebPropUtil();
		context.put("buyer", buyer);
		context.put("propUtil", propUtil);
		String sendTo[] = new String[]{buyer.getEmail()};
		Map<String, Object> params = Context2Map.context2Map(context);
		String title = "Congratulations on change email successfully  at " + propUtil.getProperty("website.webname");
		String templatePath = propUtil.getProperty("email.template.root") + "/" + propUtil.getProperty("email.buyer.register");
		MailSendServer mailSendServer = (MailSendServer) getBean("serviceMailSender");
		mailSendServer.sendEmail(sendTo, title, templatePath, params);
	}

}
