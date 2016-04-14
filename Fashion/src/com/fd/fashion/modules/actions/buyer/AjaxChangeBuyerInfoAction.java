package com.fd.fashion.modules.actions.buyer;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.modules.actions.SecureAction;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.JSONUtil;
import com.fd.util.RewriteUtil;

public class AjaxChangeBuyerInfoAction  extends SecureAction {

	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		IBuyerManager buyerManager = (IBuyerManager) getBean("buyerManager");
		String imageUrl = data.getParameters().getString("imageurl");
		String firstName = data.getParameters().getString("firstName");
		String lastName = data.getParameters().getString("lastName");
		String sex = data.getParameters().getString("sex");
		String buyerType = data.getParameters().getString("buyerType");
		String company =  data.getParameters().getString("company");
		
		if(imageUrl==null || "".equals(imageUrl)){
			
		}
		else{
			//FILEUPLOAD
			buyer.setImageUrl(imageUrl);
		}
		
		if(firstName!=null || !("".equals(firstName))){
			buyer.setFirstName(firstName);
		}
		if(lastName!=null || !("".equals(lastName))){
			buyer.setLastName(lastName);
		}
		buyer.setSex(Integer.parseInt(sex));
		
		if(buyerType!=null || !("".equals(buyerType))){
			buyer.setBuyerType(Integer.parseInt(buyerType));
		}
		if(company!=null || !("".equals(company))){
			buyer.setCompany(company);
		}
		
		Integer count = buyerManager.updateBuyer(buyer);
		if(count!=null && count>0){
			//String buyerImagePath = RewriteUtil.getImageUrl(imageUrl);
			session.setAttribute(SessionConstants.KEY_LOGIN_BUYER,buyer);
			buyer.setImageUrl(RewriteUtil.getImageUrl(buyer.getImageUrl()));
			context.put("contentdata",JSONUtil.obj2JSON(buyer));
		}
	}

}
