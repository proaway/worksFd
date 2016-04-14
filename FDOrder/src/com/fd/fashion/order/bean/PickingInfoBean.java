package com.fd.fashion.order.bean;

/** 拣货信息 */
public class PickingInfoBean {
	/** 信息ID主键 */
	private Long infoId;
	
	/** 产品SKU */
	private String sku;
	
	/** 订单ID */
	private Long orderId;
	
	/** 状态（1：已拣，2：等待拣货，3：已经登记缺货，4：错误） PICKING_STATUS */
	private Integer pickingStatus;
	
	/**产品数量*/
	private Integer productCount;
	
	/**CURRENT_COUNT当前已检产品数量*/
	private Integer currentCount;
	
	/**PRODUCT_ID产品ID*/
	private Long productId;
	
	/**PRODUCT_NAME产品名称*/
	private String productName;
	
	/**PRODUCT_STANDAR1产品规格1*/
	private String productStandar1;
	
	/**PRODUCT_STANDAR2产品规格2*/
	private String productStandar2;
	
	/**STATUS当前产品状态*/
	private Integer status;
	
	/** 信息ID主键 */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	/** 信息ID主键 */
	public Long getInfoId() {
		return this.infoId;
	}

	/** 产品SKU */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/** 产品SKU */
	public String getSku() {
		return this.sku;
	}

	/** 订单ID */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/** 订单ID */
	public Long getOrderId() {
		return this.orderId;
	}

	/** 拣货状态（1：已拣，2：等待拣货，3：已经登记缺货，4：错误） */
	public void setPickingStatus(Integer pickingStatus) {
		this.pickingStatus = pickingStatus;
	}

	/** 拣货状态（1：已拣，2：等待拣货，3：已经登记缺货，4：错误） */
	public Integer getPickingStatus() {
		return this.pickingStatus;
	}

	/**产品数量*/
	public Integer getProductCount() {
		return productCount;
	}

	/**产品数量*/
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	/**当前拣货数量*/
	public Integer getCurrentCount() {
		return currentCount;
	}

	/**当前拣货数量*/
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}

	/**产品ID*/
	public Long getProductId() {
		return productId;
	}

	/**产品ID*/
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**产品名称*/
	public String getProductName() {
		return productName;
	}

	/**产品名称*/
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**产品规格1*/
	public String getProductStandar1() {
		return productStandar1;
	}

	/**产品规格1*/
	public void setProductStandar1(String productStandar1) {
		this.productStandar1 = productStandar1;
	}

	/**产品规格2*/
	public String getProductStandar2() {
		return productStandar2;
	}

	/**产品规格2*/
	public void setProductStandar2(String productStandar2) {
		this.productStandar2 = productStandar2;
	}

	/**产品状态*/
	public Integer getStatus() {
		return status;
	}

	/**产品状态*/
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}