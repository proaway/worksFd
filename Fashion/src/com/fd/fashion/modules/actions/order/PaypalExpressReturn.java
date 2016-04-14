package com.fd.fashion.modules.actions.order;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.payment.util.PaypalApiUtil;
import com.fd.payment.vo.PaypalInfoVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.IPAddrUtil;
import com.fd.util.StringUtil;
import com.paypal.sdk.core.nvp.NVPDecoder;

public class PaypalExpressReturn extends BaseAction {
	
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		// session创建
		FdSession session = FdSessionFactory.getFdSession(data);
		String token = (String)session.getAttribute("TOKEN");
		String payerId = data.getParameters().getString("PayerID");
		context.put("payerId", payerId);
		session.setAttribute("PAYERID", payerId);
		
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		if (buyer != null) { // 已登录
			setTemplate(data, "order,ConfirmOrder.html");
		} else {
			IPaymentManager paymentManager = (IPaymentManager) getBean("paymentManager");
			PaypalSettingBean setting = paymentManager.getPaypalSetting();
	
			PaypalApiUtil paypalApiUtil = new PaypalApiUtil(setting.getApiUserName(), 
					setting.getApiPassword(), setting.getApiSignature(), 
					setting.getApiEnvironment(), setting.getAccount());
			NVPDecoder decoder = new NVPDecoder();
			String ack = paypalApiUtil.getExpressCheckoutDetails(token, decoder);
			PaypalInfoVo ppInfo = new PaypalInfoVo();
			if (ack.toLowerCase().startsWith("success")) {
				ppInfo.setAddressid(decoder.get("ADDRESSID"));
				ppInfo.setAddressstatus(decoder.get("ADDRESSSTATUS"));
				ppInfo.setCountrycode(decoder.get("COUNTRYCODE"));
				ppInfo.setEmail(decoder.get("EMAIL"));
				ppInfo.setFirstname(decoder.get("FIRSTNAME"));
				ppInfo.setLastname(decoder.get("LASTNAME"));
				ppInfo.setPayerid(decoder.get("PAYERID"));
				ppInfo.setPayerstatus(decoder.get("PAYERSTATUS"));
				ppInfo.setShiptocity(decoder.get("SHIPTOCITY"));
				ppInfo.setShiptocountrycode(decoder.get("SHIPTOCOUNTRYCODE"));
				ppInfo.setShiptoname(decoder.get("SHIPTONAME"));
				ppInfo.setShiptostate(decoder.get("SHIPTOSTATE"));
				ppInfo.setShiptostreet(decoder.get("SHIPTOSTREET"));
				ppInfo.setShiptozip(decoder.get("SHIPTOZIP"));
			}
			context.put("paypalInfoVo", ppInfo);
			
			IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
			buyer = buyerManager.getBuyerUseEmail(ppInfo.getEmail());
			if (buyer == null) {
				// 未注册，使用paypal信息注册买家
				buyer = new BuyerBean();
				buyer.setEmail(ppInfo.getEmail());
				buyer.setFirstName(ppInfo.getFirstname());
				buyer.setLastName(ppInfo.getLastname());
				String password = StringUtil.getRandomStr().substring(0, 8);
				buyer.setPassword(password);
				String userId = ppInfo.getEmail().substring(0, ppInfo.getEmail().indexOf("@"));
				BuyerBean b = buyerManager.getBuyerUseUserId(userId);
				int i = 1;
				String uId = userId;
				while (b != null) {
					uId = userId + i;
					i++;
					b = buyerManager.getBuyerUseUserId(uId);
				}
				buyer.setUserId(uId);
				buyer.setIpAddress(IPAddrUtil.getIpAddr(data.getRequest()));
				buyer.setCountry(ppInfo.getShiptocountrycode());
				//buyer.setSex(sex)
				//....
				
				// 使用地址生成
				BuyerAddressBookBean address = new BuyerAddressBookBean();
				address.setCity(ppInfo.getShiptocity());
				address.setAddressLine1(ppInfo.getShiptostreet());
				address.setAddressLine2(decoder.get("SHIPTOSTREET2"));
				address.setCountry(ppInfo.getShiptocountrycode());
				address.setFirstName(ppInfo.getShiptoname());
				address.setPostalCode(ppInfo.getShiptozip());
				address.setProvince(ppInfo.getShiptostate());
				
				buyerManager.registeBuyer(buyer, address);
				session.setAttribute(SessionConstants.KEY_LOGIN_BUYER, buyer);
				session.removeAttribute(SessionConstants.KEY_LOGIN_ERROR);
				
				// TODO 邮件点，发送密码邮件
				
			} else {// 已注册，自动登录
				session.setAttribute(SessionConstants.KEY_LOGIN_BUYER, buyer);
				session.removeAttribute(SessionConstants.KEY_LOGIN_ERROR);
			}
		}
		setTemplate(data, "order,ConfirmOrder.html");
	}
	

}
