package com.fd.fashion.modules.screens.buyer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;
import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.screens.SecureScreen;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;

public class MyOrders extends SecureScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerAccountLayout.html");
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		//所有状态(查询条件引用)
		/*IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		List<OrderStatusBean> orderStatus = dictManager.getAllOrderStatus();
		context.put("orderStatuList", orderStatus);*/
		
		//查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();
		if(buyer==null){
			orderSearch.setUserId("longli");
		}else{
			orderSearch.setUserId(buyer.getUserId());
		}
		//订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		orderSearch.setOrderId(orderId);
		//订单状态
		String orderStatu = data.getParameters().getString("statusType");
		List<String> statusList = null;
		if(orderStatu!=null && !"".equals(orderStatu)){
			if(orderStatu.indexOf("OR")>=0){
				if(orderStatu.equals("OR101")){
					orderSearch.setRiskStatus(orderStatu);
				}else {
					statusList = new ArrayList<String>();
					statusList.add(orderStatu);
					orderSearch.setRefundStatus(statusList);
				}
			}else if(orderStatu.equals("OD001")){
				statusList = new ArrayList<String>();
				statusList.add("OD001");
				statusList.add("OD002");
				statusList.add("OD003");
				orderSearch.setDisputeStatus(statusList);
			}else if(orderStatu.indexOf("OF")>=0){
				orderSearch.setFreeze(orderStatu);
			}else{
				statusList = new ArrayList<String>();
				if(orderStatu.equals("OA001")){
					statusList.add("OA001");
					statusList.add("OA003");
				}else if(orderStatu.equals("OC201")){
					statusList.add("OC201");
					statusList.add("OC202");
					statusList.add("OC203");
					statusList.add("OC204");
				}else if(orderStatu.equals("OS001")){
					statusList.add("OS001");
					statusList.add("OS002");
					statusList.add("OS003");
					statusList.add("OS004");
				}else {
					statusList.add(orderStatu);
				}
				orderSearch.setStatusList(statusList);
			}
		}
		
		//订单生成时间
		String createOrderStart =data.getParameters().getString("createOrderStart");
		Date createStart =null;
		String createOrderEnd = data.getParameters().getString("createOrderEnd");
		Date createEnd = null;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		if(createOrderStart==null|| createOrderStart.equals("") ){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -7);
			createStart = format.parse(format.format(c.getTime()));
		}else{
			createStart = format.parse(format.format(format.parse(createOrderStart)));
		}
		orderSearch.setStartCreateDate(createStart);
		if(createOrderEnd==null){
			createEnd = new Date();
			format.parse(format.format(createEnd));
		}else{
			createEnd = format.parse(format.format(format.parse(createOrderEnd)));
			 
		}
		orderSearch.setEndCreateDate(createEnd);
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		/**查询结果*/
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		SearchOrderVo searchOrder = orderManager.getSearchOrder(orderSearch, pageInfo,"2");
		if(searchOrder!=null){
			if(searchOrder.getStatusList()!=null&&searchOrder.getStatusList().size()>0){
				searchOrder.setOrderStatus(searchOrder.getStatusList().get(0));
			}
			if(searchOrder.getRefundStatus()!=null&&searchOrder.getRefundStatus().size()>0){
				searchOrder.setOrderStatus(searchOrder.getRefundStatus().get(0));
			}
			if(searchOrder.getDisputeStatus()!=null&&searchOrder.getDisputeStatus().size()>0){
				searchOrder.setOrderStatus(searchOrder.getDisputeStatus().get(0));
			}
			if(searchOrder.getFreeze()!= null){
				searchOrder.setOrderStatus(searchOrder.getFreeze());
			}
			context.put("searchOrderVo", searchOrder);
		}
		
		//统计数量结果并赋值(赋在orderVo中) (6个)
		/* 待处理 */
		
		context.put("orderVo", orderSearch);
		CullNumUtil cu = new CullNumUtil();
		context.put("cnu", cu);
	}
}
