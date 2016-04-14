package com.fd.fashion.order.vo;

import java.util.List;

import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderProductBean;

public class OrderProductListVo {

	//订单信息
	private OrderBean orderBean;
	
	//货运信息
	private OrderAddressBean orderAddr;
	
	//订单产品信息
	private List<OrderProductBean> productList;
	//订单产品总个数
	private Integer totalCount ;
	//订单产品种类数
	private Integer productCount;
	//订单总重量
	private double totalWeight;

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}

	public List<OrderProductBean> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderProductBean> productList) {
		this.productList = productList;
	}
	public OrderAddressBean getOrderAddr() {
		return orderAddr;
	}

	public void setOrderAddr(OrderAddressBean orderAddr) {
		this.orderAddr = orderAddr;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	
	
}
