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

public class print_invoice_template extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		String id = data.getParameters().getString("orderIds");
		String picker  = data.getParameters().getString("addpickUser");
		String pickDate = data.getParameters().getString("addpickDate");
		String flag = data.getParameters().getString("flag");
		context.put("orderIds", id);
		context.put("picker", picker);
		context.put("pickDate", StringUtil.getDateString(StringUtil.getAsDate(pickDate), "yyyy-MM-dd HH:mm:ss"));
		context.put("flag", flag);
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		if(id.equals("") || id==null || picker==null || picker.equals("") || pickDate.equals("") || pickDate==null){
			return;
		}
		
		String addIds = "";
		Integer totalNumber = 0;
		Map<Long,Integer> productCount = new HashMap<Long,Integer>();
		
		String[] ids  = id.split(",");
		List<OrderProductBean> listOrderProducts = new ArrayList<OrderProductBean>();
		List<OrderProductListVo> orderList = new ArrayList<OrderProductListVo>();
		for(int i=0;i<ids.length;i++){
			double totalPrice = 0;
			Integer totalCount = 0;
			OrderProductListVo orderProductListVo = new OrderProductListVo();
			Long orderId = Long.valueOf(ids[i]);
			OrderBean order = orderManager.getOrder(orderId);
			OrderAddressBean orderAddress = orderManager.getOrderAddressInfo(order.getOrderAddressId());
			orderProductListVo.setOrderAddr(orderAddress);
			List<OrderProductBean> orderProducts = orderManager.getOrderProductInfo(orderId);
			if(orderProducts!=null && orderProducts.size()>0){
				IProductManager productManager = (IProductManager)getBean("productManager");
				for(OrderProductBean orderPro :orderProducts){
					totalCount = totalCount + orderPro.getQuantity();
					ProductVo p = productManager.getProductVo(orderPro.getProductId());
					if(p!=null){
						if(p.getProduct()!=null && p.getProduct().getProductName()!=null){
							orderPro.setProductName(p.getProduct().getProductName());
						}
						if(p.getProduct()!=null && p.getProductUrl()!=null){
							orderPro.setImageUrl(p.getProductUrl());
						}
					}
					/*ImageBean image = productManager.getFirstProdImageBean(orderPro.getProductId());
					if(image!=null && image.getImageUrl()!=null){
						orderPro.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "m"));
					}*/
					if(orderPro.getStandardId()!=null){
						HashMap<String,AttributeBean> configs = productManager.getProductConfigs(orderPro.getStandardId());
						orderPro.setConfig(configs);
					}
					totalPrice = totalPrice+orderPro.getItemTotal();
					
					if(addIds.length()>0){
						String[] st = addIds.split(",");
						int s = 0;
						for(s=0;s<st.length;s++){
							if(st[s].equals(String.valueOf(orderPro.getProductId()))){
								Integer count = productCount.get(orderPro.getProductId());
								count = count + orderPro.getQuantity();
								productCount.put(orderPro.getProductId(), count);
								break;
							}
							if(s>=st.length-1){
								addIds = addIds + String.valueOf(orderPro.getProductId())+",";
								listOrderProducts.add(orderPro);
								productCount.put(orderPro.getProductId(), orderPro.getQuantity());
							}
						}
					}else{
						addIds = addIds + String.valueOf(orderPro.getProductId())+",";
						listOrderProducts.add(orderPro);
						productCount.put(orderPro.getProductId(), orderPro.getQuantity());
					}
				}
				if(order!=null && order.getAdjustAmount()!=null){
					totalPrice = totalPrice+order.getAdjustAmount();
				}
				if(order!=null && order.getCouponAmount()!=null){
					totalPrice = totalPrice+order.getCouponAmount();
				}
				if(order!=null && order.getShippingTotal()!=null){
					totalPrice = totalPrice+order.getShippingTotal();
				}
				
			}
			order.setTotal(totalPrice);
			orderProductListVo.setTotalCount(totalCount);
			orderProductListVo.setProductList(orderProducts);
			orderProductListVo.setOrderBean(order);
			totalNumber = totalNumber+totalCount;
			orderList.add(orderProductListVo);
		}
		context.put("totalCount", listOrderProducts.size());
		context.put("totalNumber", totalNumber);
		context.put("listOrderProducts",listOrderProducts);
		context.put("orderList", orderList);
		context.put("productCount", productCount);
		
	}

}
