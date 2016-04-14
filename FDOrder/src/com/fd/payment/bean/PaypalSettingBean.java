package com.fd.payment.bean;


/** Paypal设置 */
public class PaypalSettingBean extends AbstractPaymentSetting {
	/** 主键ID */
	private Integer id;
	/** API用户名 */
	private String apiUserName;
	/** API密码 */
	private String apiPassword;
	/** API签名 */
	private String apiSignature;
	/** API环境 */
	private String apiEnvironment;
	/** 收款帐号 */
	private String account;
	/** 提交地址 */
	private String postUrl;
	/** IPN地址 */
	private String ipnUrl;

	/** 主键ID */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 主键ID */
	public Integer getId() {
		return this.id;
	}

	/** API用户名 */
	public void setApiUserName(String apiUserName) {
		this.apiUserName = apiUserName;
	}

	/** API用户名 */
	public String getApiUserName() {
		return this.apiUserName;
	}

	/** API密码 */
	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}

	/** API密码 */
	public String getApiPassword() {
		return this.apiPassword;
	}

	/** API签名 */
	public void setApiSignature(String apiSignature) {
		this.apiSignature = apiSignature;
	}

	/** API签名 */
	public String getApiSignature() {
		return this.apiSignature;
	}

	/** API环境 */
	public void setApiEnvironment(String apiEnvironment) {
		this.apiEnvironment = apiEnvironment;
	}

	/** API环境 */
	public String getApiEnvironment() {
		return this.apiEnvironment;
	}

	/** 收款帐号 */
	public void setAccount(String account) {
		this.account = account;
	}

	/** 收款帐号 */
	public String getAccount() {
		return this.account;
	}

	/** 提交地址 */
	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	/** 提交地址 */
	public String getPostUrl() {
		return this.postUrl;
	}

	/** IPN地址 */
	public void setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
	}

	/** IPN地址 */
	public String getIpnUrl() {
		return this.ipnUrl;
	}
}