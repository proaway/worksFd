package com.fd.fashion.order.vo;

import java.util.ArrayList;
import java.util.List;

import com.fd.fashion.order.bean.OrderBean;

public class OrderGroup {
	public String invoice = "";
	public List<OrderBean> orders = new ArrayList<OrderBean>();
	
	public void addOrder(OrderBean order) {
		if (!orders.contains(order)) {
			if (orders.size() == 0) {
				invoice = order.getOrderId()+"";
			} else {
				invoice += "," + order.getOrderId();
			}
			orders.add(order);
		}
	}
	
	public int size() {
		return orders.size();
	}
}
