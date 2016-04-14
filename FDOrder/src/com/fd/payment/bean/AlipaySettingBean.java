package com.fd.payment.bean;

/** Alipay帐号设置 */
public class AlipaySettingBean {
	/** 合作者身份ID */
	private String partner;
	/** 私钥 */
	private String aliKey;
	/** 支付宝帐号 */
	private String sellerLogonId;

	/** 合作者身份ID */
	public void setPartner(String partner) {
		this.partner = partner;
	}

	/** 合作者身份ID */
	public String getPartner() {
		return this.partner;
	}

	/** 私钥 */
	public void setAliKey(String aliKey) {
		this.aliKey = aliKey;
	}

	/** 私钥 */
	public String getAliKey() {
		return this.aliKey;
	}

	/** 支付宝帐号 */
	public void setSellerLogonId(String sellerLogonId) {
		this.sellerLogonId = sellerLogonId;
	}

	/** 支付宝帐号 */
	public String getSellerLogonId() {
		return this.sellerLogonId;
	}
}