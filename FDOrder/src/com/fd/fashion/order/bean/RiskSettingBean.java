package com.fd.fashion.order.bean;

import java.util.Date;

/** 风控设置 */
public class RiskSettingBean {
	/** 主键 */
	private Long id;
	/** 提醒名称 */
	private String title;
	/** 购买情况（0：买家第一次购买；1：买家有纠纷记录；2：无成功购买记录） */
	private Integer buyCase;
	/** 是否启动购买情况 */
	private Integer isBuyCase;
	/** 买家IP起始 */
	private String ipFrom;
	/** 买家IP结束 */
	private String ipTo;
	/** 是否启动买家IP */
	private Integer isIp;
	/** 买家ID */
	private String buyId;
	/** 是否启动买家ID */
	private Integer isBuyId;
	/** 注册国家 */
	private String registerCountry;
	/** 是否启动注册国家 */
	private Integer isRegisterCountry;
	/** 运输国家 */
	private String shippingCountry;
	/** 是否启动运输国家 */
	private Integer isShippingCountry;
	/** 订单金额范围，0-小于，1-等于，2-大于 */
	private Integer amountRange;
	/** 订单金额 */
	private Integer amount;
	/** 是否启动订单金额 */
	private Integer isAmount;
	/** 订单分类 */
	private String orderCat;
	/** 是否启动订单分类 */
	private Integer isOrderCat;
	/** 城市，多个城市用“;”间隔 */
	private String riskBuyer;
	/** 是否启动城市 */
	private Integer isRiskBuyer;
	/** 是否启用单帐号多卡支付 */
	private Integer isMultiCard;
	/** 是否启用单卡多帐号支付 */
	private Integer isMultiBuyer;
	/** 支付方式 */
	private String payment;
	/** 是否启用支付方式 */
	private Integer isPayment;
	/** 是否启用地址账单地址与货运地址比对 */
	private Integer isAddress;
	/** 货运地址相似度 */
	private Integer shipAddSimilar;
	/** 是否启用货运地址相似度比对 */
	private Integer isShipAddSimilar;
	/** 账单地址相似度 */
	private Integer billAddSimilar;
	/** 是否启用账单地址相似度比对 */
	private Integer isBillAddSimilar;
	/** 支付帐号历史风险订单比对 */
	private String payAccount;
	/** 是否启用支付帐号历史风险订单比对 */
	private Integer isPayAccount;
	/** 提醒条件，0-满足任意条件，1-满足所有条件 */
	private Integer remindCondition;
	/** 创建日期 */
	private Date createDate;
	/** 创建人 */
	private String creator;
	/** 是否被删除(0：已删除；1：未删除) */
	private Integer state;

	/** 主键 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 主键 */
	public Long getId() {
		return this.id;
	}

	/** 提醒名称 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 提醒名称 */
	public String getTitle() {
		return this.title;
	}

	/** 购买情况（0：买家第一次购买；1：买家有纠纷记录；2：无成功购买记录） */
	public void setBuyCase(Integer buyCase) {
		this.buyCase = buyCase;
	}

	/** 购买情况（0：买家第一次购买；1：买家有纠纷记录；2：无成功购买记录） */
	public Integer getBuyCase() {
		return this.buyCase;
	}

	/** 是否启动购买情况 */
	public void setIsBuyCase(Integer isBuyCase) {
		this.isBuyCase = isBuyCase;
	}

	/** 是否启动购买情况 */
	public Integer getIsBuyCase() {
		return this.isBuyCase;
	}

	/** 买家IP起始 */
	public void setIpFrom(String ipFrom) {
		this.ipFrom = ipFrom;
	}

	/** 买家IP起始 */
	public String getIpFrom() {
		return this.ipFrom;
	}

	/** 买家IP结束 */
	public void setIpTo(String ipTo) {
		this.ipTo = ipTo;
	}

	/** 买家IP结束 */
	public String getIpTo() {
		return this.ipTo;
	}

	/** 是否启动买家IP */
	public void setIsIp(Integer isIp) {
		this.isIp = isIp;
	}

	/** 是否启动买家IP */
	public Integer getIsIp() {
		return this.isIp;
	}

	/** 买家ID */
	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	/** 买家ID */
	public String getBuyId() {
		return this.buyId;
	}

	/** 是否启动买家ID */
	public void setIsBuyId(Integer isBuyId) {
		this.isBuyId = isBuyId;
	}

	/** 是否启动买家ID */
	public Integer getIsBuyId() {
		return this.isBuyId;
	}

	/** 注册国家 */
	public void setRegisterCountry(String registerCountry) {
		this.registerCountry = registerCountry;
	}

	/** 注册国家 */
	public String getRegisterCountry() {
		return this.registerCountry;
	}

	/** 是否启动注册国家 */
	public void setIsRegisterCountry(Integer isRegisterCountry) {
		this.isRegisterCountry = isRegisterCountry;
	}

	/** 是否启动注册国家 */
	public Integer getIsRegisterCountry() {
		return this.isRegisterCountry;
	}

	/** 运输国家 */
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	/** 运输国家 */
	public String getShippingCountry() {
		return this.shippingCountry;
	}

	/** 是否启动运输国家 */
	public void setIsShippingCountry(Integer isShippingCountry) {
		this.isShippingCountry = isShippingCountry;
	}

	/** 是否启动运输国家 */
	public Integer getIsShippingCountry() {
		return this.isShippingCountry;
	}

	/** 订单金额范围，0-小于，1-等于，2-大于 */
	public void setAmountRange(Integer amountRange) {
		this.amountRange = amountRange;
	}

	/** 订单金额范围，0-小于，1-等于，2-大于 */
	public Integer getAmountRange() {
		return this.amountRange;
	}

	/** 订单金额 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/** 订单金额 */
	public Integer getAmount() {
		return this.amount;
	}

	/** 是否启动订单金额 */
	public void setIsAmount(Integer isAmount) {
		this.isAmount = isAmount;
	}

	/** 是否启动订单金额 */
	public Integer getIsAmount() {
		return this.isAmount;
	}

	/** 订单分类 */
	public void setOrderCat(String orderCat) {
		this.orderCat = orderCat;
	}

	/** 订单分类 */
	public String getOrderCat() {
		return this.orderCat;
	}

	/** 是否启动订单分类 */
	public void setIsOrderCat(Integer isOrderCat) {
		this.isOrderCat = isOrderCat;
	}

	/** 是否启动订单分类 */
	public Integer getIsOrderCat() {
		return this.isOrderCat;
	}

	/** 城市，多个城市用“;”间隔 */
	public void setRiskBuyer(String riskBuyer) {
		this.riskBuyer = riskBuyer;
	}

	/** 城市，多个城市用“;”间隔 */
	public String getRiskBuyer() {
		return this.riskBuyer;
	}

	/** 是否启动城市 */
	public void setIsRiskBuyer(Integer isRiskBuyer) {
		this.isRiskBuyer = isRiskBuyer;
	}

	/** 是否启动城市 */
	public Integer getIsRiskBuyer() {
		return this.isRiskBuyer;
	}

	/** 是否启用单帐号多卡支付 */
	public void setIsMultiCard(Integer isMultiCard) {
		this.isMultiCard = isMultiCard;
	}

	/** 是否启用单帐号多卡支付 */
	public Integer getIsMultiCard() {
		return this.isMultiCard;
	}

	/** 是否启用单卡多帐号支付 */
	public void setIsMultiBuyer(Integer isMultiBuyer) {
		this.isMultiBuyer = isMultiBuyer;
	}

	/** 是否启用单卡多帐号支付 */
	public Integer getIsMultiBuyer() {
		return this.isMultiBuyer;
	}

	/** 支付方式 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	/** 支付方式 */
	public String getPayment() {
		return this.payment;
	}

	/** 是否启用支付方式 */
	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}

	/** 是否启用支付方式 */
	public Integer getIsPayment() {
		return this.isPayment;
	}

	/** 是否启用地址账单地址与货运地址比对 */
	public void setIsAddress(Integer isAddress) {
		this.isAddress = isAddress;
	}

	/** 是否启用地址账单地址与货运地址比对 */
	public Integer getIsAddress() {
		return this.isAddress;
	}

	/** 货运地址相似度 */
	public void setShipAddSimilar(Integer shipAddSimilar) {
		this.shipAddSimilar = shipAddSimilar;
	}

	/** 货运地址相似度 */
	public Integer getShipAddSimilar() {
		return this.shipAddSimilar;
	}

	/** 是否启用货运地址相似度比对 */
	public void setIsShipAddSimilar(Integer isShipAddSimilar) {
		this.isShipAddSimilar = isShipAddSimilar;
	}

	/** 是否启用货运地址相似度比对 */
	public Integer getIsShipAddSimilar() {
		return this.isShipAddSimilar;
	}

	/** 账单地址相似度 */
	public void setBillAddSimilar(Integer billAddSimilar) {
		this.billAddSimilar = billAddSimilar;
	}

	/** 账单地址相似度 */
	public Integer getBillAddSimilar() {
		return this.billAddSimilar;
	}

	/** 是否启用账单地址相似度比对 */
	public void setIsBillAddSimilar(Integer isBillAddSimilar) {
		this.isBillAddSimilar = isBillAddSimilar;
	}

	/** 是否启用账单地址相似度比对 */
	public Integer getIsBillAddSimilar() {
		return this.isBillAddSimilar;
	}

	/** 支付帐号历史风险订单比对 */
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	/** 支付帐号历史风险订单比对 */
	public String getPayAccount() {
		return this.payAccount;
	}

	/** 是否启用支付帐号历史风险订单比对 */
	public void setIsPayAccount(Integer isPayAccount) {
		this.isPayAccount = isPayAccount;
	}

	/** 是否启用支付帐号历史风险订单比对 */
	public Integer getIsPayAccount() {
		return this.isPayAccount;
	}

	/** 提醒条件，0-满足任意条件，1-满足所有条件 */
	public void setRemindCondition(Integer remindCondition) {
		this.remindCondition = remindCondition;
	}

	/** 提醒条件，0-满足任意条件，1-满足所有条件 */
	public Integer getRemindCondition() {
		return this.remindCondition;
	}

	/** 创建日期 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 创建日期 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 创建人 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/** 创建人 */
	public String getCreator() {
		return this.creator;
	}

	/** 是否被删除(0：已删除；1：未删除) */
	public void setState(Integer state) {
		this.state = state;
	}

	/** 是否被删除(0：已删除；1：未删除) */
	public Integer getState() {
		return this.state;
	}
}