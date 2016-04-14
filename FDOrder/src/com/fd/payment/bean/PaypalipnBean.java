package com.fd.payment.bean;

import java.util.Date;

/** 记录每次paypalIPN信息 */
public class PaypalipnBean {
	/** 主键 */
	private Long id;
	/** 客户所在城市 */
	private String addressCity;
	/** 客户所在国家 */
	private String addressCountry;
	/** 国家代码 */
	private String addressCountryCode;
	/** 地址名称 */
	private String addressName;
	/** 地址中的省、直辖市或自治区 */
	private String addressState;
	/** 客户地址状态（Confirmed已确认，Unconfirmed未确认） */
	private String addressStatus;
	/** 地址街道 */
	private String addressStreet;
	/** 邮编 */
	private String addressZip;
	/** 商家的电子邮件或账户 */
	private String business;
	/** 字符集 */
	private String charset;
	/** 购买方式 */
	private String cmd;
	/** 订单号 */
	private String custom;
	/** 名 */
	private String firstName;
	/** 手续费 */
	private String handlingAmount;
	/** 物品描述（可自定为账单号） */
	private String itemName;
	/** 物品数量 */
	private String itemNumber;
	/** 姓 */
	private String lastName;
	/** 交易货币 */
	private String mcCurrency;
	/** 交易费用 */
	private String mcFee;
	/** 买家全款金额 */
	private String mcGross;
	/** 版本 */
	private String notifyVersion;
	/** 付款人email账户 */
	private String payerEmail;
	/** 客户ID */
	private String payerId;
	/** 客户paypal账户的状态（verified已认证，unverified未认证） */
	private String payerStatus;
	/** paypal生成的时间 */
	private String paymentDate;
	/** 与付款相关的美元交易费 */
	private String paymentFee;
	/** 客户付款美元总金额 */
	private String paymentGross;
	/** 支付状态：Completed付款成功 */
	private String paymentStatus;
	/** 支付类型：echeck电子支票支付，instant立即支付（paypal,信用卡或即时转账） */
	private String paymentType;
	/** 支付不成功的原因 */
	private String pendingReason;
	/** PROTECTION_ELIGIBILITY */
	private String protectionEligibility;
	/** 数量 */
	private String quantity;
	/** 收款email账户 */
	private String receiverEmail;
	/** 收款人账户 */
	private String receiverId;
	/** 国家或地区代码（两位ISO 3166） */
	private String residenceCountry;
	/** 运费总额 */
	private String shipping;
	/** 付款的税费金额 */
	private String tax;
	/** 测试IPN */
	private String testIpn;
	/** 交易主体 */
	private String transactionSubject;
	/** 唯一交易号 */
	private String txnId;
	/** 交易类型 */
	private String txnType;
	/** 登陆验证 */
	private String verifySign;
	/** IPN产生时间 */
	private Date ipnDate;
	/** 事件识别号 */
	private String caseId;
	/** 事件类型 */
	private String caseType;
	/** 事件创建时间 */
	private Date caseCreationDate;
	/** 事件原因 */
	private String reasonCode;
	/** 用来识别此次购物的帐单号码的转递变量(订单号) */
	private String invoice;
	/** "VERIFIED"说明是Paypal发出的IPN，"INVALID"说明不是Paypal发出的IPN，"VerifyFailed" 说明校验失败 */
	private String verifyIpn;
	/** 在退款、撤销或取消撤销的情况下，该变量包含原定交易的 txn_id */
	private String parentTxnId;

	/** 主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 主键 */
	public Long getId() {
		return this.id;
	}

	/** 客户所在城市 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	/** 客户所在城市 */
	public String getAddressCity() {
		return this.addressCity;
	}

	/** 客户所在国家 */
	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	/** 客户所在国家 */
	public String getAddressCountry() {
		return this.addressCountry;
	}

	/** 国家代码 */
	public void setAddressCountryCode(String addressCountryCode) {
		this.addressCountryCode = addressCountryCode;
	}

	/** 国家代码 */
	public String getAddressCountryCode() {
		return this.addressCountryCode;
	}

	/** 地址名称 */
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	/** 地址名称 */
	public String getAddressName() {
		return this.addressName;
	}

	/** 地址中的省、直辖市或自治区 */
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	/** 地址中的省、直辖市或自治区 */
	public String getAddressState() {
		return this.addressState;
	}

	/** 客户地址状态（Confirmed已确认，Unconfirmed未确认） */
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

	/** 客户地址状态（Confirmed已确认，Unconfirmed未确认） */
	public String getAddressStatus() {
		return this.addressStatus;
	}

	/** 地址街道 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	/** 地址街道 */
	public String getAddressStreet() {
		return this.addressStreet;
	}

	/** 邮编 */
	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	/** 邮编 */
	public String getAddressZip() {
		return this.addressZip;
	}

	/** 商家的电子邮件或账户 */
	public void setBusiness(String business) {
		this.business = business;
	}

	/** 商家的电子邮件或账户 */
	public String getBusiness() {
		return this.business;
	}

	/** 字符集 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	/** 字符集 */
	public String getCharset() {
		return this.charset;
	}

	/** 购买方式 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/** 购买方式 */
	public String getCmd() {
		return this.cmd;
	}

	/** 订单号 */
	public void setCustom(String custom) {
		this.custom = custom;
	}

	/** 订单号 */
	public String getCustom() {
		return this.custom;
	}

	/** 名 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** 名 */
	public String getFirstName() {
		return this.firstName;
	}

	/** 手续费 */
	public void setHandlingAmount(String handlingAmount) {
		this.handlingAmount = handlingAmount;
	}

	/** 手续费 */
	public String getHandlingAmount() {
		return this.handlingAmount;
	}

	/** 物品描述（可自定为账单号） */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/** 物品描述（可自定为账单号） */
	public String getItemName() {
		return this.itemName;
	}

	/** 物品数量 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	/** 物品数量 */
	public String getItemNumber() {
		return this.itemNumber;
	}

	/** 姓 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/** 姓 */
	public String getLastName() {
		return this.lastName;
	}

	/** 交易货币 */
	public void setMcCurrency(String mcCurrency) {
		this.mcCurrency = mcCurrency;
	}

	/** 交易货币 */
	public String getMcCurrency() {
		return this.mcCurrency;
	}

	/** 交易费用 */
	public void setMcFee(String mcFee) {
		this.mcFee = mcFee;
	}

	/** 交易费用 */
	public String getMcFee() {
		return this.mcFee;
	}

	/** 买家全款金额 */
	public void setMcGross(String mcGross) {
		this.mcGross = mcGross;
	}

	/** 买家全款金额 */
	public String getMcGross() {
		return this.mcGross;
	}

	/** 版本 */
	public void setNotifyVersion(String notifyVersion) {
		this.notifyVersion = notifyVersion;
	}

	/** 版本 */
	public String getNotifyVersion() {
		return this.notifyVersion;
	}

	/** 付款人email账户 */
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	/** 付款人email账户 */
	public String getPayerEmail() {
		return this.payerEmail;
	}

	/** 客户ID */
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	/** 客户ID */
	public String getPayerId() {
		return this.payerId;
	}

	/** 客户paypal账户的状态（verified已认证，unverified未认证） */
	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}

	/** 客户paypal账户的状态（verified已认证，unverified未认证） */
	public String getPayerStatus() {
		return this.payerStatus;
	}

	/** paypal生成的时间 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/** paypal生成的时间 */
	public String getPaymentDate() {
		return this.paymentDate;
	}

	/** 与付款相关的美元交易费 */
	public void setPaymentFee(String paymentFee) {
		this.paymentFee = paymentFee;
	}

	/** 与付款相关的美元交易费 */
	public String getPaymentFee() {
		return this.paymentFee;
	}

	/** 客户付款美元总金额 */
	public void setPaymentGross(String paymentGross) {
		this.paymentGross = paymentGross;
	}

	/** 客户付款美元总金额 */
	public String getPaymentGross() {
		return this.paymentGross;
	}

	/** 支付状态：Completed付款成功 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/** 支付状态：Completed付款成功 */
	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	/** 支付类型：echeck电子支票支付，instant立即支付（paypal,信用卡或即时转账） */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/** 支付类型：echeck电子支票支付，instant立即支付（paypal,信用卡或即时转账） */
	public String getPaymentType() {
		return this.paymentType;
	}

	/** 支付不成功的原因 */
	public void setPendingReason(String pendingReason) {
		this.pendingReason = pendingReason;
	}

	/** 支付不成功的原因 */
	public String getPendingReason() {
		return this.pendingReason;
	}

	/** PROTECTION_ELIGIBILITY */
	public void setProtectionEligibility(String protectionEligibility) {
		this.protectionEligibility = protectionEligibility;
	}

	/** PROTECTION_ELIGIBILITY */
	public String getProtectionEligibility() {
		return this.protectionEligibility;
	}

	/** 数量 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/** 数量 */
	public String getQuantity() {
		return this.quantity;
	}

	/** 收款email账户 */
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	/** 收款email账户 */
	public String getReceiverEmail() {
		return this.receiverEmail;
	}

	/** 收款人账户 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	/** 收款人账户 */
	public String getReceiverId() {
		return this.receiverId;
	}

	/** 国家或地区代码（两位ISO 3166） */
	public void setResidenceCountry(String residenceCountry) {
		this.residenceCountry = residenceCountry;
	}

	/** 国家或地区代码（两位ISO 3166） */
	public String getResidenceCountry() {
		return this.residenceCountry;
	}

	/** 运费总额 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	/** 运费总额 */
	public String getShipping() {
		return this.shipping;
	}

	/** 付款的税费金额 */
	public void setTax(String tax) {
		this.tax = tax;
	}

	/** 付款的税费金额 */
	public String getTax() {
		return this.tax;
	}

	/** 测试IPN */
	public void setTestIpn(String testIpn) {
		this.testIpn = testIpn;
	}

	/** 测试IPN */
	public String getTestIpn() {
		return this.testIpn;
	}

	/** 交易主体 */
	public void setTransactionSubject(String transactionSubject) {
		this.transactionSubject = transactionSubject;
	}

	/** 交易主体 */
	public String getTransactionSubject() {
		return this.transactionSubject;
	}

	/** 唯一交易号 */
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	/** 唯一交易号 */
	public String getTxnId() {
		return this.txnId;
	}

	/** 交易类型 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	/** 交易类型 */
	public String getTxnType() {
		return this.txnType;
	}

	/** 登陆验证 */
	public void setVerifySign(String verifySign) {
		this.verifySign = verifySign;
	}

	/** 登陆验证 */
	public String getVerifySign() {
		return this.verifySign;
	}

	/** IPN产生时间 */
	public void setIpnDate(Date ipnDate) {
		this.ipnDate = ipnDate;
	}

	/** IPN产生时间 */
	public Date getIpnDate() {
		return this.ipnDate;
	}

	/** 事件识别号 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/** 事件识别号 */
	public String getCaseId() {
		return this.caseId;
	}

	/** 事件类型 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	/** 事件类型 */
	public String getCaseType() {
		return this.caseType;
	}

	/** 事件创建时间 */
	public void setCaseCreationDate(Date caseCreationDate) {
		this.caseCreationDate = caseCreationDate;
	}

	/** 事件创建时间 */
	public Date getCaseCreationDate() {
		return this.caseCreationDate;
	}

	/** 事件原因 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/** 事件原因 */
	public String getReasonCode() {
		return this.reasonCode;
	}

	/** 用来识别此次购物的帐单号码的转递变量(订单号) */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	/** 用来识别此次购物的帐单号码的转递变量(订单号) */
	public String getInvoice() {
		return this.invoice;
	}

	/** "VERIFIED"说明是Paypal发出的IPN，"INVALID"说明不是Paypal发出的IPN，"VerifyFailed" 说明校验失败 */
	public void setVerifyIpn(String verifyIpn) {
		this.verifyIpn = verifyIpn;
	}

	/** "VERIFIED"说明是Paypal发出的IPN，"INVALID"说明不是Paypal发出的IPN，"VerifyFailed" 说明校验失败 */
	public String getVerifyIpn() {
		return this.verifyIpn;
	}

	/** 在退款、撤销或取消撤销的情况下，该变量包含原定交易的 txn_id */
	public void setParentTxnId(String parentTxnId) {
		this.parentTxnId = parentTxnId;
	}

	/** 在退款、撤销或取消撤销的情况下，该变量包含原定交易的 txn_id */
	public String getParentTxnId() {
		return this.parentTxnId;
	}
}