package com.fd.fashion.modules.screens.buyer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.order.bean.OrderAddressBean;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.bean.OrderShippingBean;
import com.fd.fashion.order.bean.OrderstatusInfoBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.util.OrderUtil;
import com.fd.fashion.order.vo.PaymentInfoVo;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.util.CullNumUtil;
import com.fd.util.RewriteUtil;
import com.fd.util.StringUtil;

public class OrderDetail extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		Long orderId = data.getParameters().getLongObject("orderId");
		if(orderId<0){
			return;
		}
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		//订单信息
		OrderBean orderInfo = orderManager.getOrder(orderId);
		context.put("orderInfo", orderInfo);
		
		//订单产品列表
		List<OrderProductBean> orderProducts = orderManager.getOrderProductInfo(orderId);
		if(orderProducts!=null && orderProducts.size()>0){
			IProductManager productManager = (IProductManager)getBean("productManager");
			for(OrderProductBean orderPro :orderProducts){
				ProductVo p = productManager.getProductVo(orderPro.getProductId());
				if(p!=null){
					if(p.getProduct()!=null && p.getProduct().getProductName()!=null){
						orderPro.setProductName(p.getProduct().getProductName());
					}
					if(p.getProduct()!=null && p.getProduct().getPacking()!=null){
						orderPro.setPacking(p.getProduct().getPacking());
					}
					if(p.getProduct()!=null && p.getProductUrl()!=null){
						orderPro.setImageUrl(p.getProductUrl());
					}
				}
				ImageBean image = productManager.getFirstProdImageBean(orderPro.getProductId());
				orderPro.setImageUrl(RewriteUtil.getImageUrl(image.getImageUrl(), "m"));
				if(orderPro.getStandardId()!=null){
					HashMap<String,AttributeBean> configs = productManager.getProductConfigs(orderPro.getStandardId());
					orderPro.setConfig(configs);
				}
			}
			context.put("totalPrice", OrderUtil.calOrderTotal(orderInfo));
			context.put("orderProductList", orderProducts);
		}
		
		//物流信息
		List<OrderShippingBean> orderShippingInfos = orderManager.getOrderShippingInfo(orderId);
		context.put("orderShipInfos", orderShippingInfos);
		
		//物流信息
		if(orderInfo!=null && orderInfo.getOrderAddressId()!=null && orderInfo.getOrderAddressId()>0){
			OrderAddressBean orderAddress = orderManager.getOrderAddressInfo(orderInfo.getOrderAddressId());
			if(StringUtil.isEmpty(orderAddress.getAddressline1())){
				String addr="";
				if(!StringUtil.isEmpty(orderAddress.getCity())){
					addr+= orderAddress.getCity();
				}
				if(!StringUtil.isEmpty(orderAddress.getCountry())){
					if(addr.equals("")){
						addr+= orderAddress.getCountry();
					}else{
						addr+= ","+orderAddress.getCountry();
					}
				}
				orderAddress.setAddressline1(addr);
			}
			context.put("orderAddressInfo", orderAddress);
		}
		//支付信息1:PayPal； 2:WesternUnion；3: BANK TRANSFER）
		PaymentInfoVo paymentInfo = orderManager.getPaymentinfoVo(orderId);
		context.put("paymentInfo", paymentInfo);
		
		//备注信息
		List<OrderstatusInfoBean> listOrderStatus = orderManager.getOrderStatusInfo(orderId,"2");
		if(listOrderStatus!=null && listOrderStatus.size()>0){
			context.put("orderStatuList", listOrderStatus);
			context.put("currentStatus", listOrderStatus.get(0));
			Map<String,String> map = new HashMap<String,String>();
			String sendStatus = "";
			String thirdStatus="";
			String fourStatus = "";
			Date secondDate = null;
			Date thirdDate = null;
			Date fourDate = null;
			for(OrderstatusInfoBean orderSta:listOrderStatus){
				if(orderSta.getOrderStatus().startsWith("OS")){
					if(orderSta.getOrderStatus().equals("OS005")){
						thirdStatus = orderSta.getOrderStatus();
						thirdDate = orderSta.getCreateTime();
					}else {
						sendStatus = orderSta.getOrderStatus();
						secondDate = orderSta.getCreateTime();
					}
				}
				if(orderSta.getOrderStatus().startsWith("OC")){
					fourStatus = orderSta.getOrderStatus();
					fourDate = orderSta.getCreateTime();
				}
				
			}
			/**用于流程图的显示*/
			
			if(secondDate!=null){
				map.put("sendStatus", sendStatus);
				map.put("sendDate", StringUtil.getDateString(secondDate, "yyyy-MM-dd HH:mm:ss"));
			}
			if(thirdDate!=null){
				map.put("thirdStatus", thirdStatus);
				map.put("thirdDate", StringUtil.getDateString(thirdDate, "yyyy-MM-dd HH:mm:ss"));
			}
			if(fourDate!=null){
				map.put("fourStatus", fourStatus);
				map.put("fourDate", StringUtil.getDateString(fourDate, "yyyy-MM-dd HH:mm:ss"));
			}
			context.put("goMap", map);
			context.put("ostatus", orderInfo.getOrderStatus());

		}
		
		//数值处理
		CullNumUtil cu = new CullNumUtil();
		context.put("cnu", cu);
	}
}
