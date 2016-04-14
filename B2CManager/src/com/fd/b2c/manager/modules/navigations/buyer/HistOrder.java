package com.fd.b2c.manager.modules.navigations.buyer;

import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.PaymentInfoVo;
import com.fd.util.AppContextUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

public class HistOrder extends VelocityNavigation {

	protected void doBuildTemplate(RunData data, Context context)
			throws Exception {
		data.setCharSet("UTF-8");
		
		Long buyerId = (Long)context.get("buyerId");
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		
		//历史订单列表(getBuyerOrderList(buyerId,0)中0表示查询所有的历史订单)
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		List<OrderVo> orderHistList = orderManager.getBuyerOrderListory(buyerId, pageInfo);
		if(orderHistList!=null && orderHistList.size()>0){
			for(OrderVo order:orderHistList){
				//支付信息
				PaymentInfoVo paymentInfo = orderManager.getPaymentinfoVo(order.getOrderId());
				if(paymentInfo!=null){
					order.setPaymentDate(StringUtil.getAsDate(paymentInfo.getPayTime()));
					order.setPaymentInfoId(paymentInfo.getPaymentInfoBean().getPaymentInfoId());
					order.setPaymentType(paymentInfo.getPaymentType());
				}
			}
			context.put("orderHistList", orderHistList);
			context.put("histCount",pageInfo.getRecordCount());
			//数值处理
			CullNumUtil cu = new CullNumUtil();
			context.put("cnu", cu);
		}
		
		context.put("pageInfo", pageInfo);
		
	}

}
