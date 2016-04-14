package com.fd.b2c.manager.modules.actions.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.manager.IOrderStatusManager;
import com.fd.fashion.order.vo.OrderProductListVo;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.util.CullNumUtil;

/**
 * @author zhangling
 * 
 *         打包查询
 * 
 */
public class AjaxBaleOrderAction extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);

		String id = data.getParameters().getString("orderId");
		String pickUser = data.getParameters().getString("pickUser");
		String flag = data.getParameters().getString("flag");
		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		IProductManager productManager = (IProductManager) getBean("productManager");
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		if (flag == null || flag.equals("")) {
			if (id == null || id.equals("")) {
			}else{
				Long orderId = Long.valueOf(id);
				OrderBean order = orderManager.getOrder(orderId);
				if (order!=null && order.getOrderStatus().equals("OS003")) {
					context.put("contentdata", 1);
				}
			}
		
		} else if (flag.equals("checkUser")) {
			UsersBean user = new UsersBean();
			user = sellerManager.getUser(pickUser);
			if (user != null && user.getUserId() != null) {
				context.put("contentdata", 1);
			}
		} else if (flag.equals("print")) {
			if (id == null || id.equals("")) {
				
			}else {
				Long orderId = Long.valueOf(id);
				UsersBean user = new UsersBean();
				user = sellerManager.getUser(pickUser);
				if (user == null || user.getUserId()==null ) {
					context.put("contentdata", 2);
				}else{
					OrderBean orderBean = orderManager.getOrder(orderId);
					if (orderBean==null || !(orderBean.getOrderStatus().equals("OS003"))) {
						context.put("contentdata", 3);
					}else{
						IOrderStatusManager orderStatusManager = (IOrderStatusManager) getBean("orderStatusManager");
						OrderBean order = new OrderBean();
						order.setOrderId(orderId);
						boolean f = orderStatusManager.os003ToOs004(order, pickUser);
						if (f) {
							context.put("contentdata", 1);
						}
					}
				}
			}
		}else if(flag.equals("getInfoFlag")){
			Long orderId = Long.valueOf(id);
			OrderProductListVo olv = new OrderProductListVo();
			OrderBean order = orderManager.getOrder(orderId);
			OrderAddressBean orderAddress = orderManager.getOrderAddressInfo(order.getOrderAddressId());
			olv.setOrderAddr(orderAddress);
			olv.setOrderBean(order);
			List<OrderProductBean> orderProducts = orderManager.getOrderProductInfo(orderId);
			Integer totalCount = 0;
			double totalWeight = 0;
			for (OrderProductBean orderPro : orderProducts) {
				totalCount = totalCount + orderPro.getQuantity();
				ProductVo pv = productManager.getProductVo(orderPro.getProductId());
				if (pv != null) {
					if (pv.getProduct() != null
							&& pv.getProduct().getProductName() != null) {
						orderPro.setProductName(pv.getProduct().getProductName());
					}
					if (pv.getProduct() != null
							&& pv.getProductUrl() != null) {
						orderPro.setImageUrl(pv.getProductUrl());
					}
					totalWeight = totalWeight + orderPro.getQuantity()* pv.getProduct().getWeight();
				}
				if (orderPro.getStandardId() != null) {
					HashMap<String, AttributeBean> configs = productManager.getProductConfigs(orderPro.getStandardId());
					orderPro.setConfig(configs);
				}
			}
			olv.setTotalCount(totalCount);
			olv.setTotalWeight(totalWeight);
			olv.setProductList(orderProducts);
			CullNumUtil cnu = new CullNumUtil();
			context.put("cullNumber", cnu);
			context.put("orderProductVo", olv);
			setTemplate(data, "order,OrderInfo.html");
		}
	}

}
