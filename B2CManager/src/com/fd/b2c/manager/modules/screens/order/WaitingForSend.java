package com.fd.b2c.manager.modules.screens.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.StringUtil;

public class WaitingForSend  extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderSearchLayout.html");
		IDictManager dictManager = (IDictManager)getBean("dictManager");
		List<RegionBean> regions = dictManager.getCountries();
		context.put("regionLists", regions);
		//查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();
		
		//基本查询条件
		//订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		//货运国家
		String regionId = data.getParameters().getString("regionId");
		//订单生成时间
		Date createOrderStart =data.getParameters().getDate("createOrderStart");
		Date createOrderEnd = data.getParameters().getDate("createOrderEnd");
		//订单付款时间
		Date paymmentDateStart = data.getParameters().getDate("paymentDateStart");
		Date paymmentDateEnd = data.getParameters().getDate("paymentDateEnd");
		//备货时间
		Integer maxStockDays = data.getParameters().getIntObject("maxStockDays");
		String maxday = data.getParameters().getString("stockDay");
		if(maxday==null || "".equals(maxday)){
			orderSearch.setMaxStockDays(maxStockDays);
		}else{
			orderSearch.setMaxStockDays(Integer.parseInt(maxday));
		}
		
		String userId = data.getParameters().getString("userId");
		if(userId==null || "".equals(userId)){
			orderSearch.setUserId(null);
		}else{
			orderSearch.setUserId(userId);
		}
		
		if(regionId==null ||"".equals(regionId)){
			orderSearch.setShippingCountry(null);
		}else{
			orderSearch.setShippingCountry(regionId);
		}
		
		orderSearch.setOrderId(orderId);
		orderSearch.setStartPaymentDate(paymmentDateStart);
		orderSearch.setEndPaymentDate(paymmentDateEnd);
		
		
		//待发货状态CODE=‘OS001 …哪些状态包含在待发货状态’
		List<String> statusList = new ArrayList<String>();
		statusList.add("OS001");
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
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		SearchOrderVo searchOrder = orderManager.getSearchOrder(orderSearch, pageInfo,"1");
				
		

		if(searchOrder!=null){
			context.put("searchOrderVo", searchOrder);
		}
		
		//统计数量结果并赋值(赋在orderVo中) (6个)
		/* 待处理 */
		
		context.put("orderVo", orderSearch);
		
		CullNumUtil cu = new CullNumUtil();
		context.put("cnu", cu);
	}

}
