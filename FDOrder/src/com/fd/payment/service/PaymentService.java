package com.fd.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.payment.bean.AlipayNotifyBean;
import com.fd.payment.bean.AlipaySettingBean;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.bean.PaypalSettingBean;
import com.fd.payment.bean.PaypalipnBean;
import com.fd.payment.bean.WesternSettingBean;
import com.fd.payment.bean.WesternUnionBean;
import com.fd.util.DESadeUtil;
import com.fd.util.PageInfo;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

@Component
@SuppressWarnings("unchecked")
public class PaymentService implements IPaymentService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public PaymentTypeBean insertPaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception {
		dao.insertObj("insertPaymentTypeBean", paymenttype);
		return paymenttype;
	}

	/**
	 * 修改支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public int updatePaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception {
		return dao.updateObj("updatePaymentTypeBean", paymenttype);
	}

	/**
	 * 获取支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getPaymentTypeBean", expiration = 3600)
	public PaymentTypeBean getPaymentTypeBean(@ParameterValueKeyProvider PaymentTypeBean paymenttype)
			throws Exception {
		return (PaymentTypeBean) dao.getAsObject("getPaymentTypeBean",
				paymenttype);
	}

	/**
	 * 获取支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
//	@ReadThroughSingleCache(namespace = "getPaymentTypeBeans", expiration = 3600)
//	public List<PaymentTypeBean> getPaymentTypeBeans(@ParameterValueKeyProvider PaymentTypeBean paymenttype)
	public List<PaymentTypeBean> getPaymentTypeBeans(PaymentTypeBean paymenttype)
			throws Exception {
		return dao.getAsList("getPaymentTypeBean", paymenttype);
	}

	/**
	 * 获取支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public List<PaymentTypeBean> getPaymentTypeBeans(
			PaymentTypeBean paymenttype, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getPaymentTypeBeanCount",
				paymenttype);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPaymentTypeBean", paymenttype, pageInfo);
		}
		return null;
	}

	/**
	 * 删除支付方式
	 * 
	 * @param paymenttype
	 * @return
	 * @throws Exception
	 */
	public int deletePaymentTypeBean(PaymentTypeBean paymenttype)
			throws Exception {
		return dao.deleteObj("deletePaymentTypeBean", paymenttype);
	}

	/**
	 * 增加记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public PaypalipnBean insertPaypalipnBean(PaypalipnBean paypalipn)
			throws Exception {
		dao.insertObj("insertPaypalipnBean", paypalipn);
		return paypalipn;
	}

	/**
	 * 修改记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public int updatePaypalipnBean(PaypalipnBean paypalipn) throws Exception {
		return dao.updateObj("updatePaypalipnBean", paypalipn);
	}

	/**
	 * 获取记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public PaypalipnBean getPaypalipnBean(PaypalipnBean paypalipn)
			throws Exception {
		return (PaypalipnBean) dao.getAsObject("getPaypalipnBean", paypalipn);
	}

	/**
	 * 获取记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public List<PaypalipnBean> getPaypalipnBeans(PaypalipnBean paypalipn)
			throws Exception {
		return dao.getAsList("getPaypalipnBean", paypalipn);
	}

	/**
	 * 获取记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public List<PaypalipnBean> getPaypalipnBeans(PaypalipnBean paypalipn,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getPaypalipnBeanCount",
				paypalipn);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPaypalipnBean", paypalipn, pageInfo);
		}
		return null;
	}

	/**
	 * 删除记录每次paypalIPN信息
	 * 
	 * @param paypalipn
	 * @return
	 * @throws Exception
	 */
	public int deletePaypalipnBean(PaypalipnBean paypalipn) throws Exception {
		return dao.deleteObj("deletePaypalipnBean", paypalipn);
	}

	/**
	 * 增加Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public PaypalSettingBean insertPaypalSettingBean(
			PaypalSettingBean paypalSetting) throws Exception {
		dao.insertObj("insertPaypalSettingBean", paypalSetting);
		return paypalSetting;
	}

	/**
	 * 修改Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public int updatePaypalSettingBean(PaypalSettingBean paypalSetting)
			throws Exception {
		return dao.updateObj("updatePaypalSettingBean", paypalSetting);
	}

	/**
	 * 获取Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public PaypalSettingBean getPaypalSettingBean(
			PaypalSettingBean paypalSetting) throws Exception {
		PaypalSettingBean bean = (PaypalSettingBean) dao.getAsObject("getPaypalSettingBean",
				paypalSetting);
		if (bean != null) {
			bean.setAccount(DESadeUtil.decryptMode(bean.getAccount()));
			bean.setApiPassword(DESadeUtil.decryptMode(bean.getApiPassword()));
			bean.setApiSignature(DESadeUtil.decryptMode(bean.getApiSignature()));
			bean.setApiUserName(DESadeUtil.decryptMode(bean.getApiUserName()));
		}
		return bean;
	}

	/**
	 * 获取Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public List<PaypalSettingBean> getPaypalSettingBeans(
			PaypalSettingBean paypalSetting) throws Exception {
		List<PaypalSettingBean> beans = dao.getAsList("getPaypalSettingBean", paypalSetting);
		if (beans != null) {
			for (PaypalSettingBean bean : beans) {
				bean.setAccount(DESadeUtil.decryptMode(bean.getAccount()));
				bean.setApiPassword(DESadeUtil.decryptMode(bean.getApiPassword()));
				bean.setApiSignature(DESadeUtil.decryptMode(bean.getApiSignature()));
				bean.setApiUserName(DESadeUtil.decryptMode(bean.getApiUserName()));
			}
		}
		return beans;
	}

	/**
	 * 获取Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public List<PaypalSettingBean> getPaypalSettingBeans(
			PaypalSettingBean paypalSetting, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getPaypalSettingBeanCount",
				paypalSetting);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			List<PaypalSettingBean> beans = dao.getAsList("getPaypalSettingBean", paypalSetting,
					pageInfo);
			if (beans != null) {
				for (PaypalSettingBean bean : beans) {
					bean.setAccount(DESadeUtil.decryptMode(bean.getAccount()));
					bean.setApiPassword(DESadeUtil.decryptMode(bean.getApiPassword()));
					bean.setApiSignature(DESadeUtil.decryptMode(bean.getApiSignature()));
					bean.setApiUserName(DESadeUtil.decryptMode(bean.getApiUserName()));
				}
			}
			return beans;
		}
		return null;
	}

	/**
	 * 删除Paypal设置
	 * 
	 * @param paypalSetting
	 * @return
	 * @throws Exception
	 */
	public int deletePaypalSettingBean(PaypalSettingBean paypalSetting)
			throws Exception {
		return dao.deleteObj("deletePaypalSettingBean", paypalSetting);
	}

	/**
	 * 增加西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public WesternSettingBean insertWesternSettingBean(
			WesternSettingBean westernSetting) throws Exception {
		dao.insertObj("insertWesternSettingBean", westernSetting);
		return westernSetting;
	}

	/**
	 * 修改西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public int updateWesternSettingBean(WesternSettingBean westernSetting)
			throws Exception {
		return dao.updateObj("updateWesternSettingBean", westernSetting);
	}

	/**
	 * 获取西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public WesternSettingBean getWesternSettingBean(
			WesternSettingBean westernSetting) throws Exception {
		return (WesternSettingBean) dao.getAsObject("getWesternSettingBean",
				westernSetting);
	}

	/**
	 * 获取西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public List<WesternSettingBean> getWesternSettingBeans(
			WesternSettingBean westernSetting) throws Exception {
		return dao.getAsList("getWesternSettingBean", westernSetting);
	}

	/**
	 * 获取西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public List<WesternSettingBean> getWesternSettingBeans(
			WesternSettingBean westernSetting, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getWesternSettingBeanCount",
				westernSetting);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getWesternSettingBean", westernSetting,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除西联付款设置
	 * 
	 * @param westernSetting
	 * @return
	 * @throws Exception
	 */
	public int deleteWesternSettingBean(WesternSettingBean westernSetting)
			throws Exception {
		return dao.deleteObj("deleteWesternSettingBean", westernSetting);
	}
	/**
	 * 增加西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public WesternUnionBean insertWesternUnionBean(WesternUnionBean westernUnion) throws Exception {
		dao.insertObj("insertWesternUnionBean", westernUnion);
		return westernUnion;
	}
	
	/**
	 * 修改西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public int updateWesternUnionBean(WesternUnionBean westernUnion) throws Exception {
		return dao.updateObj("updateWesternUnionBean", westernUnion);
	}
	
	/**
	 * 获取西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public WesternUnionBean getWesternUnionBean(WesternUnionBean westernUnion) throws Exception {
		return (WesternUnionBean)dao.getAsObject("getWesternUnionBean", westernUnion);
	}
	
	/**
	 * 获取西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public List<WesternUnionBean> getWesternUnionBeans(WesternUnionBean westernUnion) throws Exception {
		return dao.getAsList("getWesternUnionBean", westernUnion);
	}
	
	/**
	 * 获取西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public List<WesternUnionBean> getWesternUnionBeans(WesternUnionBean westernUnion, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getWesternUnionBeanCount", westernUnion);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getWesternUnionBean", westernUnion, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除西联付款
	 * 
	 * @param westernUnion
	 * @return
	 * @throws Exception
	 */
	public int deleteWesternUnionBean(WesternUnionBean westernUnion) throws Exception {
		return dao.deleteObj("deleteWesternUnionBean", westernUnion);
	}
	/**
	 * 增加Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public AlipayNotifyBean insertAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception {
		dao.insertObj("insertAlipayNotifyBean", aliNotify);
		return aliNotify;
	}
	
	/**
	 * 修改Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public int updateAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception {
		return dao.updateObj("updateAlipayNotifyBean", aliNotify);
	}
	
	/**
	 * 获取Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public AlipayNotifyBean getAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception {
		return (AlipayNotifyBean)dao.getAsObject("getAlipayNotifyBean", aliNotify);
	}
	
	/**
	 * 获取Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public List<AlipayNotifyBean> getAlipayNotifyBeans(AlipayNotifyBean aliNotify) throws Exception {
		return dao.getAsList("getAlipayNotifyBean", aliNotify);
	}
	
	/**
	 * 获取Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public List<AlipayNotifyBean> getAlipayNotifyBeans(AlipayNotifyBean aliNotify, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getAlipayNotifyBeanCount", aliNotify);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getAlipayNotifyBean", aliNotify, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除Alipay Notify 通知
	 * 
	 * @param aliNotify
	 * @return
	 * @throws Exception
	 */
	public int deleteAlipayNotifyBean(AlipayNotifyBean aliNotify) throws Exception {
		return dao.deleteObj("deleteAlipayNotifyBean", aliNotify);
	}
	
	/**
	 * 修改Alipay帐号设置
	 * 
	 * @param alipaySetting
	 * @return
	 * @throws Exception
	 */
	public int updateAlipaySettingBean(AlipaySettingBean alipaySetting) throws Exception {
		return dao.updateObj("updateAlipaySettingBean", alipaySetting);
	}
	
	/**
	 * 获取Alipay帐号设置
	 * 
	 * @param alipaySetting
	 * @return
	 * @throws Exception
	 */
	public AlipaySettingBean getAlipaySettingBean(AlipaySettingBean alipaySetting) throws Exception {
		return (AlipaySettingBean)dao.getAsObject("getAlipaySettingBean", alipaySetting);
	}
}
