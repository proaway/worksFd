package com.fd.b2c.manager.modules.screens.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

public class WaitingForPayment extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderSearchLayout.html");

		//查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();
		
		//基本查询条件
		//订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		String userId = data.getParameters().getString("userId","").trim();
		//订单生成时间
		Date createOrderStart =data.getParameters().getDate("createOrderStart");
		Date createOrderEnd = data.getParameters().getDate("createOrderEnd");
		
		orderSearch.setOrderId(orderId);
		if(userId.equals("")){
			orderSearch.setUserId(null);
		}else{
			orderSearch.setUserId(userId);
		}
		
		//待付款状态CODE=‘OA001,OA004……哪些状态包含在待付款状态中’
		List<String> statusList = new ArrayList<String>();
		statusList.add("OA001");
		statusList.add("OA003");
		orderSearch.setStatusList(statusList);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(createOrderStart==null){
		}else{
			createOrderStart = format.parse(format.format(createOrderStart));
		}
		orderSearch.setStartCreateDate(createOrderStart);
		if(createOrderEnd==null){
		}else{
			createOrderEnd = format.parse(format.format(createOrderEnd));
		}
		orderSearch.setEndCreateDate(createOrderEnd);
		
		PageInfo pageInfo = new PageInfo();
		String pageIndex = data.getParameters().getString("pageIndex","1");
		pageInfo.setPageIndex(Integer.parseInt(pageIndex));
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		/**查询结果*/
		
		SearchOrderVo searchOrder = orderManager.getSearchOrder(orderSearch, pageInfo,"1");
		if(searchOrder!=null){
			context.put("searchOrderVo", searchOrder);
		}
		context.put("orderVo", orderSearch);
		CullNumUtil cu = new CullNumUtil();
		context.put("cnu", cu);
	}


}
