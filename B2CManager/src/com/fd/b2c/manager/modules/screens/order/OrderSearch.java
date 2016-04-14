package com.fd.b2c.manager.modules.screens.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.OrderProductBean;
import com.fd.fashion.order.bean.ShippingCompanyBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.OrderVo;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.payment.manager.IPaymentManager;
import com.fd.util.AppContextUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;

/**2013-04-12
 * @author zhangling
 *
 */
public class OrderSearch extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/OrderSearchLayout.html");
	
		//所有状态(查询条件引用)
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		List<OrderStatusBean> orderStatus = dictManager.getAllOrderStatus();
		context.put("orderStatuList", orderStatus);
		
		IOrderManager orderManager = (IOrderManager)getBean("orderManager");
		List<ShippingCompanyBean> shippingCompanys = orderManager.getShippingCompanys();
		context.put("shippingCompanys", shippingCompanys);
		
		//查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();
		
		//基本查询条件
		//订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		//订单状态
		String orderStatu = data.getParameters().getString("statusType");
		List<String> statusList = null;
		if(orderStatu!=null && !"".equals(orderStatu)){
			if(orderStatu.indexOf("OR")>=0){
				if(orderStatu.equals("OR101")){
					orderSearch.setRiskStatus(orderStatu);
				}else{
					statusList = new ArrayList<String>();
					statusList.add(orderStatu);
					orderSearch.setRefundStatus(statusList);
				}
			}else if(orderStatu.indexOf("OD")>=0){
				statusList = new ArrayList<String>();
				statusList.add(orderStatu);
				orderSearch.setDisputeStatus(statusList);
			}else if(orderStatu.indexOf("OF")>=0){
				orderSearch.setFreeze(orderStatu);
			}else if(orderStatu.equals("OA001")){
				statusList = new ArrayList<String>();
				statusList.add("OA001");
				statusList.add("OA003");
				orderSearch.setStatusList(statusList);
			}else if(orderStatu.equals("OC101")){
				statusList = new ArrayList<String>();
				statusList.add("OC101");
				statusList.add("OC202");
				statusList.add("OC203");
				statusList.add("OC204");
				orderSearch.setStatusList(statusList);
			}else{
				statusList = new ArrayList<String>();
				statusList.add(orderStatu);
				orderSearch.setStatusList(statusList);
			}
		}
		//订单生成时间
		Date createOrderStart =data.getParameters().getDate("createOrderStart");
		Date createOrderEnd = data.getParameters().getDate("createOrderEnd");
		orderSearch.setOrderId(orderId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(createOrderStart==null){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, -7);
			createOrderStart = format.parse(format.format(c.getTime()));
		}else{
			createOrderStart = format.parse(format.format(createOrderStart));
		}
		orderSearch.setStartCreateDate(createOrderStart);
		if(createOrderEnd==null){
			createOrderEnd = format.parse(format.format(new Date()));
		}else{
			createOrderEnd = format.parse(format.format(createOrderEnd));
		}
		orderSearch.setEndCreateDate(createOrderEnd);
		
		//判断是否有高级查询条件 0是无，1是有
		/*String flag = data.getParameters().getString("isHignGreat","0");
		if(flag.equals("1")){*/
			//高级查询条件
			Date paymmentDateStart = data.getParameters().getDate("paymentDateStart");
			Date paymmentDateEnd = data.getParameters().getDate("paymentDateEnd");
			Date deliveryDateStart = data.getParameters().getDate("deliveryDateStart");
			Date deliveryDateEnd = data.getParameters().getDate("deliveryDateEnd");
			orderSearch.setEndPaymentDate(paymmentDateEnd);
			orderSearch.setStartPaymentDate(paymmentDateStart);
			orderSearch.setStartShippingDate(deliveryDateStart);
			orderSearch.setEndShippingDate(deliveryDateEnd);
			
			String shippingType = data.getParameters().getString("shippingType");
			if(shippingType==null || "".equals(shippingType)){
				orderSearch.setShippingType(null);
			}else{
				orderSearch.setShippingType(shippingType);
			}
			
		/*}*/
			
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		/**查询结果*/
		SearchOrderVo searchOrder = orderManager.getSearchOrder(orderSearch, pageInfo,"1");
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
	
	
	public static void main(String[] args){
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		SearchOrderVo searchOrder = new SearchOrderVo();
		PageInfo pageInfo = new PageInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		searchOrder.setEndCreateDate(new Date());
		searchOrder.setPageInfo(pageInfo);
	/*	int i = 0;
		while(i < 10){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(d);
		String dateTime = String.valueOf(System.nanoTime());
		int start = dateTime.length() - 5;
		int end = dateTime.length();
		dateTime.startsWith(String.valueOf(start),end);
		
		System.out.println(str +dateTime.substring(start, end));
		}*/
		try {
			SearchOrderVo searchOrderVo = orderManager.getSearchOrder(searchOrder, pageInfo,"1");
			System.out.println(searchOrderVo.getOrders().size());
			System.out.println(searchOrderVo.getWaitDeliveryCount());
			System.out.println(searchOrderVo.getWaitPaymentcCount());
			System.out.println(searchOrderVo.getIssueCount());
			System.out.println(searchOrderVo.getIsStockCount());
			
			orderManager.getOrder(searchOrder.getOrderId());
			IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
			List<OrderProductBean> products = orderManager.getOrderProductInfo(searchOrder.getOrderId());
			
			/*for(int i=0;i<products.size();i++){
				productManager.getProductConfigs(products.get(i).getStandardId());
			}*/
			List<OrderVo> ors = orderManager.getBuyerOrderList(searchOrder.getOrderId(),5);
			System.out.println(ors.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
