package com.fd.b2c.manager.modules.screens.order;

import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.PickingBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.OrderScanVo;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.manager.IShippingManager;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.util.AppContextUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

/**
 * @author zhangling
 * 
 * 打印订单
 *
 */
public class PrintOrder extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		Long orderId = data.getParameters().getLongObject("orderId");
		String buyerName = data.getParameters().getString("buyerName");
		Date timeStart = data.getParameters().getDate("timeStart");
		Date timeEnd = data.getParameters().getDate("timeEnd");
		String paymentType=data.getParameters().getString("paymentType");
		String shippingType = data.getParameters().getString("shippingType");
		String shippingCountry = data.getParameters().getString("shippingCountry");
		String orderStatus = data.getParameters().getString("orderStatus");
		
		OrderScanVo orderScan = new OrderScanVo();
		orderScan.setOrderNo(orderId==null?null:orderId);
		orderScan.setPaymentTimeStart(timeStart);
		orderScan.setPaymentTimeEnd(timeEnd);
		orderScan.setShippingCountry((shippingCountry==null ||shippingCountry.equals(""))?null:shippingCountry);
		orderScan.setOrderStatus((orderStatus==null || orderStatus.equals(""))?null:orderStatus);
		orderScan.setShippingType((shippingType==null ||shippingType.equals(""))?null:shippingType);
		orderScan.setBuyerName((buyerName==null || buyerName.equals(""))?null:buyerName);
		
		if(paymentType==null || paymentType.equals("")){
			orderScan.setPaymentType(null);
		}else{
			orderScan.setPaymentType(Integer.parseInt(paymentType));
		}
		
		/**货运国家*/
		IDictManager dictManager = (IDictManager)getBean("dictManager");
		List<RegionBean> regions = dictManager.getCountries();
		context.put("regionLists", regions);
		
		/**物流方式*/
		IShippingManager shippingManager = (IShippingManager) getBean("shippingManager");
		List<ShippingInfoBean> shippingInfos = shippingManager.getShippingInfoBeans();
		if (shippingInfos != null && shippingInfos.size()>0) {
			ShippingInfoBean shippingInfo = shippingManager.getShippingInfo(shippingInfos.get(0).getShippingInfoId());
			context.put("shippingInfo", shippingInfo);
		}
		 
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		List<PaymentTypeBean> paymentInfos = orderManager.getBuyerPaymentType(0);
		context.put("paymentInfos", paymentInfos);
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		List<OrderScanVo> orderPrints = orderManager.getPrintOrderList(orderScan, pageInfo);
		if(orderPrints!=null && orderPrints.size()>0){
			for(OrderScanVo osv :orderPrints){
				PickingBean pick = new PickingBean();
				pick.setOrderId(osv.getOrderNo());
				pick = orderManager.getPickingBean(pick);
				if(pick!=null && pick.getPickingUser()!=null){
					osv.setPickUser(pick.getPickingUser());
					osv.setPickDate(StringUtil.getDateString(pick.getPickingDate(), "yyyy-MM-dd HH:mm:ss"));
				}
			}
			context.put("orderPrints", orderPrints);
		}
		
		context.put("orderScanVo", orderScan);
		CullNumUtil cnu = new CullNumUtil();
		context.put("cullNumber", cnu);
	}

}
