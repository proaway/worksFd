package com.fd.payment.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.order.service.IOrderService;
import com.fd.payment.bean.AbstractPaymentSetting;
import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.AlipaySettingBean;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.bean.WesternSettingBean;
import com.fd.payment.service.IPaymentService;

/**
 * @CreateDate: 2013-4-9 下午01:42:32
 * @author Longli
 * @Description: 支付相关Manager
 * 
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class PaymentManager implements IPaymentManager {
	@Autowired
	@Qualifier("paymentService")
	IPaymentService paymentService;

	/**
	 * 获得所有可用支付方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getEnabledPayments() throws Exception {
		PaymentTypeBean payment = new PaymentTypeBean();
		payment.setEnabled(1);
		List<PaymentTypeBean> payments =  paymentService.getPaymentTypeBeans(payment);
		if (payments != null) {
			for (PaymentTypeBean p : payments) {
				int paymentType = p.getPaymentType();
				AbstractPaymentSetting setting = null;
				if (paymentType == 1) { // Paypal支付
					setting = paymentService.getPaypalSettingBean(new PaypalSettingBean());
				} else if(paymentType == 2) { // 西联
					setting = paymentService.getWesternSettingBean(new WesternSettingBean());
				}
				p.setSetting(setting);
			}
		}
		return payments;
	}

	
	/**
	 * 获取Paypal支付设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public PaypalSettingBean getPaypalSetting() throws Exception {
		return paymentService.getPaypalSettingBean(new PaypalSettingBean());
	}
	
	/**
	 * 获取西联支付设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public WesternSettingBean getWesternSetting() throws Exception {
		return paymentService.getWesternSettingBean(new WesternSettingBean());
	}
	
	/**
	 * 保存PaypalIPN
	 * 
	 * @param ipn
	 * @return
	 * @throws Exception
	 */
	public boolean addPaypalIPN(PaypalipnBean ipn) throws Exception {
		paymentService.insertPaypalipnBean(ipn);
		return true;
	}
	
	/**
	 * 获取全部付款方式的Hashmap
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, PaymentTypeBean> getAllPaymentMap() throws Exception {
		List<PaymentTypeBean> list = paymentService.getPaymentTypeBeans(new PaymentTypeBean());
		if (list != null && list.size()>0) {
			HashMap<Integer, PaymentTypeBean> map = new HashMap<Integer, PaymentTypeBean>();
			for (PaymentTypeBean pay : list) {
				map.put(pay.getPaymentType(), pay);
			}
			return map;
		}
		return null;
	}
	
	/**
	 * 获取Alipay设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public AlipaySettingBean getAlipaySetting() throws Exception {
		return paymentService.getAlipaySettingBean(new AlipaySettingBean());
	}
	
	/**
	 * 保存Alipay notify
	 * 
	 * @param notify
	 * @return
	 * @throws Exception
	 */
	public boolean addAlipayNotify(AlipayNotifyBean notify) throws Exception {
		paymentService.insertAlipayNotifyBean(notify);
		return true;
	}
	
	/**
	 * 获取Alipay notify
	 * 
	 * @param notifyId
	 * @return
	 * @throws Exception
	 */
	public AlipayNotifyBean getAlipayNotify(String notifyId) throws Exception {
		AlipayNotifyBean notify = new AlipayNotifyBean();
		notify.setNotifyId(notifyId);
		List<AlipayNotifyBean> list = paymentService.getAlipayNotifyBeans(notify);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
