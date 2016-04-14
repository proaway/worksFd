package com.fd.payment.service;

import java.util.List;

import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.AlipaySettingBean;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.bean.WesternSettingBean;
import com.fd.payment.bean.WesternUnionBean;
import com.fd.util.PageInfo;

public interface IPaymentService {
	/**
	 * 增加支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public PaymentTypeBean insertPaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception;

	/**
	 * 修改支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public int updatePaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception;

	/**
	 * 获取支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public PaymentTypeBean getPaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception;

	/**
	 * 获取支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getPaymentTypeBeans(PaymentTypeBean paymenttype)
			throws Exception;

	/**
	 * 获取支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getPaymentTypeBeans(
			PaymentTypeBean paymenttype, PageInfo pageInfo) throws Exception;

	/**
	 * 删除支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public int deletePaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception;

	/**
	 * 增加记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public PaypalipnBean insertPaypalipnBean(PaypalipnBean paypalipn)
			throws Exception;

	/**
	 * 修改记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public int updatePaypalipnBean(PaypalipnBean paypalipn) throws Exception;

	/**
	 * 获取记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public PaypalipnBean getPaypalipnBean(PaypalipnBean paypalipn)
			throws Exception;

	/**
	 * 获取记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public List<PaypalipnBean> getPaypalipnBeans(PaypalipnBean paypalipn)
			throws Exception;

	/**
	 * 获取记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public List<PaypalipnBean> getPaypalipnBeans(PaypalipnBean paypalipn,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public int deletePaypalipnBean(PaypalipnBean paypalipn) throws Exception;

	/**
	 * 增加Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public PaypalSettingBean insertPaypalSettingBean(
			PaypalSettingBean paypalSetting) throws Exception;

	/**
	 * 修改Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public int updatePaypalSettingBean(PaypalSettingBean paypalSetting)
			throws Exception;

	/**
	 * 获取Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public PaypalSettingBean getPaypalSettingBean(
			PaypalSettingBean paypalSetting) throws Exception;

	/**
	 * 获取Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public List<PaypalSettingBean> getPaypalSettingBeans(
			PaypalSettingBean paypalSetting) throws Exception;

	/**
	 * 获取Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public List<PaypalSettingBean> getPaypalSettingBeans(
			PaypalSettingBean paypalSetting, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public int deletePaypalSettingBean(PaypalSettingBean paypalSetting)
			throws Exception;

	/**
	 * 增加西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public WesternSettingBean insertWesternSettingBean(
			WesternSettingBean westernSetting) throws Exception;

	/**
	 * 修改西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public int updateWesternSettingBean(WesternSettingBean westernSetting)
			throws Exception;

	/**
	 * 获取西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public WesternSettingBean getWesternSettingBean(
			WesternSettingBean westernSetting) throws Exception;

	/**
	 * 获取西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public List<WesternSettingBean> getWesternSettingBeans(
			WesternSettingBean westernSetting) throws Exception;

	/**
	 * 获取西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public List<WesternSettingBean> getWesternSettingBeans(
			WesternSettingBean westernSetting, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public int deleteWesternSettingBean(WesternSettingBean westernSetting)
			throws Exception;
	/**
	 * 增加西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public WesternUnionBean insertWesternUnionBean(WesternUnionBean westernUnion) throws Exception;
	
	/**
	 * 修改西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public int updateWesternUnionBean(WesternUnionBean westernUnion) throws Exception;
	
	
	/**
	 * 获取西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public WesternUnionBean getWesternUnionBean(WesternUnionBean westernUnion) throws Exception;
	/**
	 * 获取西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public List<WesternUnionBean> getWesternUnionBeans(WesternUnionBean westernUnion) throws Exception;
	
	/**
	 * 获取西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public List<WesternUnionBean> getWesternUnionBeans(WesternUnionBean westernUnion, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public int deleteWesternUnionBean(WesternUnionBean westernUnion) throws Exception;
	/**
	 * 增加Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public AlipayNotifyBean insertAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception;
	
	/**
	 * 修改Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public int updateAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception;
	
	
	/**
	 * 获取Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public AlipayNotifyBean getAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception;
	/**
	 * 获取Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public List<AlipayNotifyBean> getAlipayNotifyBeans(AlipayNotifyBean aliNotify) throws Exception;
	
	/**
	 * 获取Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public List<AlipayNotifyBean> getAlipayNotifyBeans(AlipayNotifyBean aliNotify, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public int deleteAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception;
	
	/**
	 * 修改Alipay帐号设置
	 * 
	 * @param alipaySetting
	 * @return
	 * @throws Exception
	 */
	public int updateAlipaySettingBean(AlipaySettingBean alipaySetting) throws Exception;
	
	/**
	 * 获取Alipay帐号设置
	 * 
	 * @param alipaySetting
	 * @return
	 * @throws Exception
	 */
	public AlipaySettingBean getAlipaySettingBean(AlipaySettingBean alipaySetting) throws Exception;
}
