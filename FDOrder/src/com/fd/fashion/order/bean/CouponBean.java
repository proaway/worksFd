package com.fd.fashion.order.bean;

import java.util.Date;

import com.fd.util.PageInfo;

/** coupon */
public class CouponBean {
	/** 优惠券code：主键 */
	private String couponCode;
	/** 类型：0，现金；1，折扣； */
	private Integer couponType;
	/** 折扣 */
	private Double couponDiscount;
	/** Coupon金额 */
	private Double couponAmount;
	/** 限定目录 */
	private String limitCategory;
	/** 最小额度 */
	private Double minaMount;
	/** 有效开始时间 */
	private Date startDate;
	/** 有效结束时间 */
	private Date endDate;
	/** 买家userId */
	private String userId;
	/** 订单ID */
	private Long orderId;
	/** 正常状态：0，正常；1，失效；2，已用；3，未开始。 */
	private Integer normalState;
	/** 创建人员ID */
	private Long createId;
	/** 创建时间 */
	private Date createDate;
	/** 状态(标识是否删除):0,正常状态；1，删除状态； */
	private Integer state;
	/** 状态修改操作人员ID */
	private Integer operateId;
	/** 状态修改操作时间 */
	private Date operateDate;
	
	/** Coupon状态*/
	private Integer status;
	/** 备注 */
	private String memo;
	/** 0:单次  1.不限次 */
	private String numberType;
	/** 限定产品*/
	private String limitProduct;
	/** 分页 **/
	private PageInfo pageInfo;
	/** 判断条件*/
	private Double startAmount;
	private Double endAmount;
	private Date createDateStart;
	private Date createDateEnd;
	
	private Double startMiniAmount;
	private Double endMiniAmount;

	/** 优惠券code：主键 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/** 优惠券code：主键 */
	public String getCouponCode() {
		return this.couponCode;
	}

	/** 类型：0，现金；1，折扣； */
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	/** 类型：0，现金；1，折扣； */
	public Integer getCouponType() {
		return this.couponType;
	}

	/** 折扣 */
	public void setCouponDiscount(Double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	/** 折扣 */
	public Double getCouponDiscount() {
		return this.couponDiscount;
	}

	/** Coupon金额 */
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}

	/** Coupon金额 */
	public Double getCouponAmount() {
		return this.couponAmount;
	}

	/** 限定目录 */
	public void setLimitCategory(String limitCategory) {
		this.limitCategory = limitCategory;
	}

	/** 限定目录 */
	public String getLimitCategory() {
		return this.limitCategory;
	}

	/** 最小额度 */
	public void setMinaMount(Double minaMount) {
		this.minaMount = minaMount;
	}

	/** 最小额度 */
	public Double getMinaMount() {
		return this.minaMount;
	}

	/** 有效开始时间 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** 有效开始时间 */
	public Date getStartDate() {
		return this.startDate;
	}

	/** 有效结束时间 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** 有效结束时间 */
	public Date getEndDate() {
		return this.endDate;
	}

	/** 买家NickName */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/** 买家NickName */
	public String getUserId() {
		return this.userId;
	}

	/** 订单ID */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 正常状态：0，正常；1，失效；2，已用；3，完成。 */
	public void setNormalState(Integer normalState) {
		this.normalState = normalState;
	}

	/** 正常状态：0，正常；1，失效；2，已用；3，完成。 */
	public Integer getNormalState() {
		return this.normalState;
	}

	/** 创建人员ID */
	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	/** 创建人员ID */
	public Long getCreateId() {
		return this.createId;
	}

	/** 创建时间 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 创建时间 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/** 状态(标识是否删除):0,正常状态；1，删除状态； */
	public void setState(Integer state) {
		this.state = state;
	}

	/** 状态(标识是否删除):0,正常状态；1，删除状态； */
	public Integer getState() {
		return this.state;
	}

	/** 状态修改操作人员ID */
	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	/** 状态修改操作人员ID */
	public Integer getOperateId() {
		return this.operateId;
	}

	/** 状态修改操作时间 */
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	/** 状态修改操作时间 */
	public Date getOperateDate() {
		return this.operateDate;
	}

	/**
	 * @return the pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * @param pageInfo the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the startAmount
	 */
	public Double getStartAmount() {
		return startAmount;
	}

	/**
	 * @param startAmount the startAmount to set
	 */
	public void setStartAmount(Double startAmount) {
		this.startAmount = startAmount;
	}

	/**
	 * @return the endAmount
	 */
	public Double getEndAmount() {
		return endAmount;
	}

	/**
	 * @param endAmount the endAmount to set
	 */
	public void setEndAmount(Double endAmount) {
		this.endAmount = endAmount;
	}

	/**
	 * @return the createDateStart
	 */
	public Date getCreateDateStart() {
		return createDateStart;
	}

	/**
	 * @param createDateStart the createDateStart to set
	 */
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	/**
	 * @return the createDateEnd
	 */
	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	/**
	 * @param createDateEnd the createDateEnd to set
	 */
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	/**
	 * @return the numberType
	 */
	public String getNumberType() {
		return numberType;
	}

	/**
	 * @param numberType the numberType to set
	 */
	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	/**
	 * @return the limitProduct
	 */
	public String getLimitProduct() {
		return limitProduct;
	}

	/**
	 * @param limitProduct the limitProduct to set
	 */
	public void setLimitProduct(String limitProduct) {
		this.limitProduct = limitProduct;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the startMiniAmount
	 */
	public Double getStartMiniAmount() {
		return startMiniAmount;
	}

	/**
	 * @param startMiniAmount the startMiniAmount to set
	 */
	public void setStartMiniAmount(Double startMiniAmount) {
		this.startMiniAmount = startMiniAmount;
	}

	/**
	 * @return the endMiniAmount
	 */
	public Double getEndMiniAmount() {
		return endMiniAmount;
	}

	/**
	 * @param endMiniAmount the endMiniAmount to set
	 */
	public void setEndMiniAmount(Double endMiniAmount) {
		this.endMiniAmount = endMiniAmount;
	}
}