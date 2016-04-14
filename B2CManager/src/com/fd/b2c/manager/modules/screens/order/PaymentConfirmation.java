package com.fd.b2c.manager.modules.screens.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

public class PaymentConfirmation extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderSearchLayout.html");

		//查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();
		
		//订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		orderSearch.setOrderId(orderId);
		//订单付款时间
		Date paymentDateStart =data.getParameters().getDate("paymentDateStart");
		Date paymentDateEnd = data.getParameters().getDate("paymentDateEnd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(paymentDateStart==null){
		}else{
			paymentDateStart = format.parse(format.format(paymentDateStart));
		}
		orderSearch.setStartCreateDate(paymentDateStart);
		if(paymentDateEnd==null){
		}else{
			paymentDateEnd = format.parse(format.format(paymentDateEnd));
		}
		orderSearch.setEndCreateDate(paymentDateEnd);
		
		//用户名称
		String userId = data.getParameters().getString("userId");
		if(userId==null ||"".equals(userId)){
			orderSearch.setUserId(null);
		}else{
			orderSearch.setUserId(userId);
		}
		
		//支付方式
		String paymentType = data.getParameters().get("paymentType");
		if(paymentType==null ||"".equals(paymentType)){
			orderSearch.setPaymentType(null);
		}else{
			orderSearch.setPaymentType(paymentType);
		}
		
		//付款确认CODE=‘OA002,OA003 …哪些状态包含在付款确认状态’
		List<String> statusList = new ArrayList<String>();
		statusList.add("OA002");
		orderSearch.setStatusList(statusList);
		
		//查询结果
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		SearchOrderVo searhcOrder = orderManager.getSearchOrder(orderSearch, pageInfo,"1");
		if(searhcOrder!=null){
			context.put("searchOrderVo", searhcOrder);
		}
		
		context.put("orderVo", orderSearch);
		
		CullNumUtil cu = new CullNumUtil();
		context.put("cnu", cu);
	}

}
