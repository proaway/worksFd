package com.fd.payment.manager;

import java.util.List;
import java.util.Map;

import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.AlipaySettingBean;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.bean.WesternSettingBean;

public interface IPaymentManager {
	/**
	 * 获得所有可用支付方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getEnabledPayments() throws Exception;
	
	/**
	 * 获取Paypal支付设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public PaypalSettingBean getPaypalSetting() throws Exception;
	
	/**
	 * 获取西联支付设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public WesternSettingBean getWesternSetting() throws Exception;
	
	/**
	 * 保存PaypalIPN
	 * 
	 * @param ipn
	 * @return
	 * @throws Exception
	 */
	public boolean addPaypalIPN(PaypalipnBean ipn) throws Exception;
	
	/**
	 * 获取全部付款方式的Hashmap
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, PaymentTypeBean> getAllPaymentMap() throws Exception;
	
	/**
	 * 获取Alipay设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public AlipaySettingBean getAlipaySetting() throws Exception;
	
	/**
	 * 保存Alipay notify
	 * 
	 * @param notify
	 * @return
	 * @throws Exception
	 */
	public boolean addAlipayNotify(AlipayNotifyBean notify) throws Exception;
	
	/**
	 * 获取Alipay notify
	 * 
	 * @param notifyId
	 * @return
	 * @throws Exception
	 */
	public AlipayNotifyBean getAlipayNotify(String notifyId) throws Exception;
}
