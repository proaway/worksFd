package com.fd.b2c.manager.modules.screens.order;

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

public class RefundConfirmation extends SecureScreen{
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
		
		//退款确认CODE=‘OR201 …哪些状态包含在退款确认状态’
		List<String> statusList = new ArrayList<String>();
		statusList.add("OR201");
		orderSearch.setRefundStatus(statusList);
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
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
