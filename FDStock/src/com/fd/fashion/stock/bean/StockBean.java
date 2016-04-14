package com.fd.fashion.stock.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @createTime 2013-6-18 下午4:32:14
 * @author ErWei
 * @description 库存类
 */
/**
 * @creatTime 2013-6-20 下午4:06:14
 * @author CuiErWei
 * @description
 * 
 */
public class StockBean implements Serializable {
	private static final long serialVersionUID = 5808662898977051806L;
	/** 库存ID */
	private Long stockId;
	/** 采购任务ID */
	private String taskId;
	/** 入库数量 */
	private int enterCount;
	/** 入库日期 */
	private Date enterTime;
	/** 凭证号 */
	private String proffId;
	/** 入库价格 */
	private Double enterPrice;
	/** 产品sku号，唯一编号 */
	private String sku;
	/** 产品ID */
	private Long productId;
	/** 产品名称 */
	private String productName;
	/** 货架号 */
	private String shelf;
	/** 预期入库数量 */
	private int expectEnterCount;
	/** 备注 */
	private String memo;
	/** 历史库存总数 */
	private Integer hisStockCount;
	/** 最近采购日期 */
	private Date lastPurchaseDate;
	/** 最近采购数 */
	private Integer lastPurchaseCount;
	/** 上月结转数(库存数) */
	private Integer lastMonthStockCount;
	/** 实际库存数 */
	private Integer stockCount;
	/** 缺货登记 */
	private Integer lackStockCount;
	/** 本月出库数 */
	private Integer outStockCount;
	/** 库存商品总价（￥） */
	private Double totalPrice;
	/** 库存状态 */
	private Integer status;

	public String getTaskId() {
		return taskId;
	}

	public int getExpectEnterCount() {
		return expectEnterCount;
	}

	public void setExpectEnterCount(int expectEnterCount) {
		this.expectEnterCount = expectEnterCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getEnterCount() {
		return enterCount;
	}

	public void setEnterCount(int enterCount) {
		this.enterCount = enterCount;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public Double getEnterPrice() {
		return enterPrice;
	}

	public String getProffId() {
		return proffId;
	}

	public void setProffId(String proffId) {
		this.proffId = proffId;
	}

	public void setEnterPrice(Double enterPrice) {
		this.enterPrice = enterPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public Integer getHisStockCount() {
		return hisStockCount;
	}

	public void setHisStockCount(Integer hisStockCount) {
		this.hisStockCount = hisStockCount;
	}

	public Date getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(Date lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}

	public Integer getLastPurchaseCount() {
		return lastPurchaseCount;
	}

	public void setLastPurchaseCount(Integer lastPurchaseCount) {
		this.lastPurchaseCount = lastPurchaseCount;
	}

	public Integer getLastMonthStockCount() {
		return lastMonthStockCount;
	}

	public void setLastMonthStockCount(Integer lastMonthStockCount) {
		this.lastMonthStockCount = lastMonthStockCount;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Integer getLackStockCount() {
		return lackStockCount;
	}

	public void setLackStockCount(Integer lackStockCount) {
		this.lackStockCount = lackStockCount;
	}

	public Integer getOutStockCount() {
		return outStockCount;
	}

	public void setOutStockCount(Integer outStockCount) {
		this.outStockCount = outStockCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}