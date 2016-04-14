package com.fd.b2c.manager.modules.actions.order;

import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.bean.ProblemOrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.order.vo.OrderScanVo;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.CullNumUtil;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

public class AjaxOrderInfoAction extends SecureAction {

	public void doPerform(RunData data, Context context) throws Exception {
		int flag = data.getParameters().getInt("flag");
		Long orderId = data.getParameters().getLongObject("orderNumber");
		String trackingNumber = data.getParameters().getString("trackingNumber");
		String shippingType = data.getParameters().getString("shippingType");
		if (orderId < 0) {
			return;
		}
		OrderScanVo ocv = new OrderScanVo();
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		// 订单信息
		OrderBean orderInfo = orderManager.getOrder(orderId);
		if (orderInfo != null && orderInfo.getOrderStatus().equals("OS004") && (orderInfo.getBadOrder()==null || orderInfo.getBadOrder()!=1)) {
			if (flag == 0) {
				//查询订单详细信息
				ocv.setBuyerId(orderInfo.getBuyerId());
				ocv.setBuyerName(orderInfo.getUserId());
				ocv.setOrderNo(orderId);
				ocv.setOrderStatus(orderInfo.getOrderStatus());

				OrderStatusBean status = dictManager.getOrderStatus(orderInfo
						.getOrderStatus());
				ocv.setOrderStatusName(status.getStatusCn());

				// 物流信息
				List<OrderShippingBean> orderShippingInfos = orderManager
						.getOrderShippingInfo(orderId);
				if (orderShippingInfos != null && orderShippingInfos.size() > 0) {
					OrderShippingBean orderShip = orderShippingInfos.get(0);
					ocv.setTrackNo(orderShip.getTrackingNo());
					ocv.setShippingDate(StringUtil.getDateString(orderShip.getShippingDate(), "yyyy-MM-dd HH:mm:ss"));
				}

				// 货运信息
				if (orderInfo != null && orderInfo.getOrderAddressId() != null
						&& orderInfo.getOrderAddressId() > 0) {
					OrderAddressBean orderAddress = orderManager
							.getOrderAddressInfo(orderInfo.getOrderAddressId());
					if (orderAddress != null
							&& orderAddress.getCountry() != null) {
						if (orderAddress.getAddressline1() != null) {
							ocv.setShippingAddress(orderAddress
									.getAddressline1());
						} else if (orderAddress.getAddressline2() != null) {
							ocv.setShippingAddress(orderAddress
									.getAddressline2());
						} else {
							String addr = orderAddress.getProvince() + ","
									+ orderAddress.getCity() + ","
									+ orderAddress.getCountry()
									+ orderAddress.getPhone() != null ? ","
									+ orderAddress.getPhone() : "";
							ocv.setShippingAddress(addr);
						}

					}

				}

				List<OrderProductBean> orderProducts = orderManager
						.getOrderProductInfo(orderId);
				if (orderProducts != null && orderProducts.size() > 0) {
					double totalPrice = 0;
					double totalWeight = 0;
					int totalCount = 0;
					IProductManager productManager = (IProductManager) getBean("productManager");
					for (OrderProductBean orderPro : orderProducts) {
						ProductVo p = productManager.getProductVo(orderPro
								.getProductId());
						totalCount = totalCount + orderPro.getQuantity();
						totalWeight = totalWeight + p.getProduct().getWeight()
								* orderPro.getQuantity();
						totalPrice = totalPrice + orderPro.getItemTotal();
					}
					if (orderInfo != null
							&& orderInfo.getAdjustAmount() != null) {
						totalPrice = totalPrice + orderInfo.getAdjustAmount();
					}
					if (orderInfo != null
							&& orderInfo.getCouponAmount() != null) {
						totalPrice = totalPrice + orderInfo.getCouponAmount();
					}
					if (orderInfo != null
							&& orderInfo.getShippingTotal() != null) {
						totalPrice = totalPrice + orderInfo.getShippingTotal();
					}

					ocv.setTotalCost(CullNumUtil.cullNum(totalPrice, 2));
					ocv.setTotalCount(totalCount);
					ocv.setTotalWeight(CullNumUtil.cullNum(totalWeight, 2));

				}
				context.put("contentdata", JSONUtil.obj2JSON(ocv));
			}else if(flag==5){
				//变更为问题订单
				OrderBean ord = new OrderBean();
				ord.setOrderId(orderId);
				ord.setBadOrder(1);
				int suFlag = orderManager.updateOrder(ord);
				if(suFlag>0){
					ord = orderManager.getOrder(orderId);
					context.put("contentdata", JSONUtil.obj2JSON(ord));
				}
			}else if(flag==1){
				OrderShippingBean osb = new OrderShippingBean();
				osb.setOrderId(orderId);
				osb.setShippingType(shippingType);
				osb.setTrackingNo(trackingNumber);
				osb.setShippingDate(new Date());
				IOrderStatusManager orderStatusManager = (IOrderStatusManager)this.getBean("orderStatusManager");
				orderStatusManager.shipment(osb);
				ocv.setTrackNo(trackingNumber);
				ocv.setOrderStatus("OS005");
				ocv.setOrderStatusName("已发货，等待买家确认");
				context.put("contentdata", JSONUtil.obj2JSON(ocv));
			}
		}

	}

}
