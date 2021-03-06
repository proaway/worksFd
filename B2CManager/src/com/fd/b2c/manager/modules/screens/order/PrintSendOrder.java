package com.fd.b2c.manager.modules.screens.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.OrderProductListVo;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.StringUtil;

public class PrintSendOrder extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");

		String id = data.getParameters().getString("orderIds");
		context.put("orderIds", id);

		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		if (id.equals("") || id == null) {
			return;
		}

		String[] ids = id.split(",");
		List<OrderProductListVo> orderList = new ArrayList<OrderProductListVo>();
		for (int i = 0; i < ids.length; i++) {
			double totalPrice = 0;
			Integer totalCount = 0;
			OrderProductListVo orderProductListVo = new OrderProductListVo();
			Long orderId = Long.valueOf(ids[i]);
			OrderBean order = orderManager.getOrder(orderId);
			OrderAddressBean orderAddress = orderManager
					.getOrderAddressInfo(order.getOrderAddressId());
			orderProductListVo.setOrderAddr(orderAddress);
			List<OrderProductBean> orderProducts = orderManager
					.getOrderProductInfo(orderId);
			if (orderProducts != null && orderProducts.size() > 0) {
				IProductManager productManager = (IProductManager) getBean("productManager");
				for (OrderProductBean orderPro : orderProducts) {
					totalCount = totalCount + orderPro.getQuantity();
					ProductVo p = productManager.getProductVo(orderPro
							.getProductId());
					if (p != null) {
						if (p.getProduct() != null && p.getProduct().getProductName() != null) {
							orderPro.setProductName(p.getProduct().getProductName());
						}
						if (p.getProduct() != null && p.getProductUrl() != null) {
							orderPro.setImageUrl(p.getProductUrl());
						}
					}
					if (orderPro.getStandardId() != null) {
						HashMap<String, AttributeBean> configs = productManager.getProductConfigs(orderPro.getStandardId());
						orderPro.setConfig(configs);
					}
					totalPrice = totalPrice + orderPro.getItemTotal();

					if (order != null && order.getAdjustAmount() != null) {
						totalPrice = totalPrice + order.getAdjustAmount();
					}
					if (order != null && order.getCouponAmount() != null) {
						totalPrice = totalPrice + order.getCouponAmount();
					}
					if (order != null && order.getShippingTotal() != null) {
						totalPrice = totalPrice + order.getShippingTotal();
					}

				}
				order.setTotal(totalPrice);
				orderProductListVo.setTotalCount(totalCount);
				orderProductListVo.setProductList(orderProducts);
				orderProductListVo.setOrderBean(order);
				orderList.add(orderProductListVo);
			}
			context.put("orderList", orderList);

		}
	}

}
