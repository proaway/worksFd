package com.fd.b2c.manager.modules.screens.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;

public class DisputeOrder extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderSearchLayout.html");

		//查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();
		
		//订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		//用户名称
		String userId = data.getParameters().getString("userId");
		orderSearch.setOrderId(orderId);
		if(userId==null || "".equals(userId)){
			orderSearch.setUserId(null);
		}else{
			orderSearch.setUserId(userId);
		}
		
		//纠纷状态CODE=‘OD001,OD002,OD003 …哪些状态包含在付款确认状态’
		List<String> statusList = new ArrayList<String>();
		statusList.add("OD001");
		statusList.add("OD002");
		statusList.add("OD003");
		orderSearch.setDisputeStatus(statusList);
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		pageInfo.setPageSize(20);
		context.put("pageInfo", pageInfo);
		
		/**查询结果*/
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
