package com.fd.payment.bean;

import java.io.Serializable;

import com.google.code.ssm.api.CacheKeyMethod;

/** 支付方式 */
public class PaymentTypeBean implements Serializable {
	private static final long serialVersionUID = -5599734145427648457L;
	/** 支付方式编号 */
	private Integer paymentType;
	/** 支付方式名称 */
	private String name;
	/** 是否使用，0-不使用，1-使用 */
	private Integer enabled;
	/** 支付方式图标URL */
	private String logo;
	/** 支付方式描述 */
	private String description;
	/** 支付方式设置 */
	private AbstractPaymentSetting setting;

	@CacheKeyMethod
	public String cacheKey() {
		return this.getClass().getName() + "." + String.valueOf(paymentType);
	}

	/** 支付方式编号 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	/** 支付方式编号 */
	public Integer getPaymentType() {
		return this.paymentType;
	}

	/** 支付方式名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 支付方式名称 */
	public String getName() {
		return this.name;
	}

	/** 是否使用，0-不使用，1-使用 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	/** 是否使用，0-不使用，1-使用 */
	public Integer getEnabled() {
		return this.enabled;
	}

	/** 支付方式图标URL */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/** 支付方式图标URL */
	public String getLogo() {
		return this.logo;
	}

	/**
	 * @return the setting
	 */
	public AbstractPaymentSetting getSetting() {
		return setting;
	}

	/**
	 * @param setting the setting to set
	 */
	public void setSetting(AbstractPaymentSetting setting) {
		this.setting = setting;
	}

	/** 支付方式描述 */
	public String getDescription() {
		return description;
	}

	/** 支付方式描述 */
	public void setDescription(String desc) {
		this.description = desc;
	}
}