package com.fd.fashion.finance.bean;

import java.util.Date;

/** 财务收支 */
public class FinancialBean {
	/** 流水号 */
	private Long financialId;
	/** 创建记录时间 */
	private Date createTime;
	/** 金额 */
	private Double amount;
	/** 连接ID */
	private Long linkeId;
	/** 收支类型 */
	private String amountType;
	/** 备注 */
	private String memo;
	/** 支付类型 */
	private String paymentType;
	/** 交易状态 */
	private String financialStatus;
	/** 账户盈余 */
	private Double balance;

	/** 流水号 */
	public void setFinancialId(Long financialId) {
		this.financialId = financialId;
	}

	/** 流水号 */
	public Long getFinancialId() {
		return this.financialId;
	}

	/** 创建记录时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 创建记录时间 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/** 金额 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/** 金额 */
	public Double getAmount() {
		return this.amount;
	}

	/** 连接ID */
	public void setLinkeId(Long linkeId) {
		this.linkeId = linkeId;
	}

	/** 连接ID */
	public Long getLinkeId() {
		return this.linkeId;
	}

	/** 收支类型 */
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	/** 收支类型 */
	public String getAmountType() {
		return this.amountType;
	}

	/** 备注 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/** 备注 */
	public String getMemo() {
		return this.memo;
	}

	/** 支付类型 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	/** 支付类型 */
	public String getPaymentType() {
		return this.paymentType;
	}

	/** 交易状态 */
	public void setFinancialStatus(String financialStatus) {
		this.financialStatus = financialStatus;
	}

	/** 交易状态 */
	public String getFinancialStatus() {
		return this.financialStatus;
	}

	/** 账户盈余 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	/** 账户盈余 */
	public Double getBalance() {
		return this.balance;
	}

}