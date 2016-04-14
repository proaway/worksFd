package com.fd.fashion.order.bean;

import java.util.Date;

/** 订单金额历史 */
public class OrderAmountBean {
	/** 主键 */
	private Long amountId;
	/** 订单号 */
	private Long orderId;
	/** 金额 */
	private Double amount;
	/** 类型，1-订单付款，2-金额调整，3-退款 */
	private Integer amountType;
	/** 备注 */
	private String memo;
	/** 时间 */
	private Date createDate;
	/** 操作人 */
	private String operator;

	/** 主键 */
	public void setAmountId(Long amountId) {
		this.amountId = amountId;
	}

	/** 主键 */
	public Long getAmountId() {
		return this.amountId;
	}

	/** 订单号 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单号 */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 金额 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/** 金额 */
	public Double getAmount() {
		return this.amount;
	}

	/** 类型，1-订单付款，2-金额调整，3-退款 */
	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}

	/** 类型，1-订单付款，2-金额调整，3-退款 */
	public Integer getAmountType() {
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

	/** 时间 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 时间 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 操作人 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/** 操作人*/
	public String getOperator() {
		return this.operator;
	}
}