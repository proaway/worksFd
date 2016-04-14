package com.fd.payment.bean;


/** Alipay Notify 通知 */
public class AlipayNotifyBean {
	/** ID主键 */
	private Long id;
	/** 通知时间 */
	private String notifyTime;
	/** 通知类型 */
	private String notifyType;
	/** 通知校验ID */
	private String notifyId;
	/** 签名方式 */
	private String signType;
	/** 签名 */
	private String sign;
	/** 商户网站唯一订单号 */
	private String outTradeNo;
	/** 商品名称 */
	private String subject;
	/** 支付类型 */
	private String paymentType;
	/** 支付宝交易号 */
	private String tradeNo;
	/** 交易状态 */
	private String tradeStatus;
	/** 交易创建时间 */
	private String gmtCreate;
	/** 交易付款时间 */
	private String gmtPayment;
	/** 交易关闭时间 */
	private String gmtClose;
	/** 退款状态 */
	private String refundStatus;
	/** 退款时间 */
	private String gmtRefund;
	/** 卖家支付宝账号 */
	private String sellerEmail;
	/** 买家支付宝账号 */
	private String buyerEmail;
	/** 卖家支付宝账户号 */
	private String sellerId;
	/** 买家支付宝账户号 */
	private String buyerId;
	/** 商品单价 */
	private String price;
	/** 交易金额 */
	private String totalFee;
	/** 购买数量 */
	private String quantity;
	/** 商品描述 */
	private String body;
	/** 折扣 */
	private String discount;
	/** 是否调整总价 */
	private String isTotalFeeAdjust;
	/** 是否使用红包买家 */
	private String useCoupon;
	/** 错误代码 */
	private String errorCode;
	/** 网银流水 */
	private String bankSeqNo;
	/** 公用回传参数 */
	private String extraCommonParam;
	/** 币种 */
	private String currency;
	/** 外币金额 */
	private String forexTotalFee;
	/** 是否通过校验 */
	private String verified;

	/** ID主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** ID主键 */
	public Long getId() {
		return this.id;
	}

	/** 通知时间 */
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	/** 通知时间 */
	public String getNotifyTime() {
		return this.notifyTime;
	}

	/** 通知类型 */
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	/** 通知类型 */
	public String getNotifyType() {
		return this.notifyType;
	}

	/** 通知校验ID */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	/** 通知校验ID */
	public String getNotifyId() {
		return this.notifyId;
	}

	/** 签名方式 */
	public void setSignType(String signType) {
		this.signType = signType;
	}

	/** 签名方式 */
	public String getSignType() {
		return this.signType;
	}

	/** 签名 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/** 签名 */
	public String getSign() {
		return this.sign;
	}

	/** 商户网站唯一订单号 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	/** 商户网站唯一订单号 */
	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	/** 商品名称 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** 商品名称 */
	public String getSubject() {
		return this.subject;
	}

	/** 支付类型 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/** 支付类型 */
	public String getPaymentType() {
		return this.paymentType;
	}

	/** 支付宝交易号 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/** 支付宝交易号 */
	public String getTradeNo() {
		return this.tradeNo;
	}

	/** 交易状态 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	/** 交易状态 */
	public String getTradeStatus() {
		return this.tradeStatus;
	}

	/** 交易创建时间 */
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	/** 交易创建时间 */
	public String getGmtCreate() {
		return this.gmtCreate;
	}

	/** 交易付款时间 */
	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}

	/** 交易付款时间 */
	public String getGmtPayment() {
		return this.gmtPayment;
	}

	/** 交易关闭时间 */
	public void setGmtClose(String gmtClose) {
		this.gmtClose = gmtClose;
	}

	/** 交易关闭时间 */
	public String getGmtClose() {
		return this.gmtClose;
	}

	/** 退款状态 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	/** 退款状态 */
	public String getRefundStatus() {
		return this.refundStatus;
	}

	/** 退款时间 */
	public void setGmtRefund(String gmtRefund) {
		this.gmtRefund = gmtRefund;
	}

	/** 退款时间 */
	public String getGmtRefund() {
		return this.gmtRefund;
	}

	/** 卖家支付宝账号 */
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	/** 卖家支付宝账号 */
	public String getSellerEmail() {
		return this.sellerEmail;
	}

	/** 买家支付宝账号 */
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	/** 买家支付宝账号 */
	public String getBuyerEmail() {
		return this.buyerEmail;
	}

	/** 卖家支付宝账户号 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	/** 卖家支付宝账户号 */
	public String getSellerId() {
		return this.sellerId;
	}

	/** 买家支付宝账户号 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	/** 买家支付宝账户号 */
	public String getBuyerId() {
		return this.buyerId;
	}

	/** 商品单价 */
	public void setPrice(String price) {
		this.price = price;
	}

	/** 商品单价 */
	public String getPrice() {
		return this.price;
	}

	/** 交易金额 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	/** 交易金额 */
	public String getTotalFee() {
		return this.totalFee;
	}

	/** 购买数量 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/** 购买数量 */
	public String getQuantity() {
		return this.quantity;
	}

	/** 商品描述 */
	public void setBody(String body) {
		this.body = body;
	}

	/** 商品描述 */
	public String getBody() {
		return this.body;
	}

	/** 折扣 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/** 折扣 */
	public String getDiscount() {
		return this.discount;
	}

	/** 是否调整总价 */
	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}

	/** 是否调整总价 */
	public String getIsTotalFeeAdjust() {
		return this.isTotalFeeAdjust;
	}

	/** 是否使用红包买家 */
	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}

	/** 是否使用红包买家 */
	public String getUseCoupon() {
		return this.useCoupon;
	}

	/** 错误代码 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/** 错误代码 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/** 网银流水 */
	public void setBankSeqNo(String bankSeqNo) {
		this.bankSeqNo = bankSeqNo;
	}

	/** 网银流水 */
	public String getBankSeqNo() {
		return this.bankSeqNo;
	}

	/** 公用回传参数 */
	public void setExtraCommonParam(String extraCommonParam) {
		this.extraCommonParam = extraCommonParam;
	}

	/** 公用回传参数 */
	public String getExtraCommonParam() {
		return this.extraCommonParam;
	}

	/** 币种 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/** 币种 */
	public String getCurrency() {
		return this.currency;
	}

	/** 外币金额 */
	public void setForexTotalFee(String forexTotalFee) {
		this.forexTotalFee = forexTotalFee;
	}

	/** 外币金额 */
	public String getForexTotalFee() {
		return this.forexTotalFee;
	}

	/** 是否通过校验 */
	public void setVerified(String verified) {
		this.verified = verified;
	}

	/** 是否通过校验 */
	public String getVerified() {
		return this.verified;
	}
}