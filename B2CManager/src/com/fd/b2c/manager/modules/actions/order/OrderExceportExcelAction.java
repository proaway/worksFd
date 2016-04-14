package com.fd.b2c.manager.modules.actions.order;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.fashion.order.vo.SearchOrderVo;
import com.fd.util.StringUtil;

public class OrderExceportExcelAction extends SecureAction {

	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");

		// 查询注入对象
		SearchOrderVo orderSearch = new SearchOrderVo();

		String flag = data.getParameters().getString("atcionFlag");
		// 订单Id
		Long orderId = data.getParameters().getLongObject("orderId");
		orderSearch.setOrderId(orderId);

		// 订单生成时间
		Date createOrderStart = data.getParameters()
				.getDate("createOrderStart");
		Date createOrderEnd = data.getParameters().getDate("createOrderEnd");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (createOrderStart == null) {
		} else {
			createOrderStart = format.parse(format.format(createOrderStart));
		}
		orderSearch.setStartCreateDate(createOrderStart);
		if (createOrderEnd == null) {
		} else {
			createOrderEnd = format.parse(format.format(createOrderEnd));
		}
		orderSearch.setEndCreateDate(createOrderEnd);

		String userId = data.getParameters().getString("userId", "").trim();
		if (userId.equals("")) {
			orderSearch.setUserId(null);
		} else {
			orderSearch.setUserId(userId);
		}

		if (flag.equals("OrderList")) {
			// 基本查询条件

			// 订单状态
			String orderStatu = data.getParameters().getString("statusType");
			List<String> statusList = null;
			if (orderStatu.indexOf("OR") >= 0) {
				if (orderStatu.equals("OR101")) {
					orderSearch.setRiskStatus(orderStatu);
				} else {
					statusList = new ArrayList<String>();
					statusList.add(orderStatu);
					orderSearch.setRefundStatus(statusList);
				}
			} else if (orderStatu.indexOf("OD") >= 0) {
				statusList = new ArrayList<String>();
				statusList.add(orderStatu);
				orderSearch.setDisputeStatus(statusList);
			} else if (orderStatu.indexOf("OF") >= 0) {
				orderSearch.setFreeze(orderStatu);
			} else {
				if (orderStatu != null && !"".equals(orderStatu)) {
					statusList = new ArrayList<String>();
					statusList.add(orderStatu);
				}
				orderSearch.setStatusList(statusList);
			}
			Date paymmentDateStart = data.getParameters().getDate(
					"paymentDateStart");
			Date paymmentDateEnd = data.getParameters().getDate(
					"paymentDateEnd");
			Date deliveryDateStart = data.getParameters().getDate(
					"deliveryDateStart");
			Date deliveryDateEnd = data.getParameters().getDate(
					"deliveryDateEnd");
			orderSearch.setEndPaymentDate(paymmentDateEnd);
			orderSearch.setStartPaymentDate(paymmentDateStart);
			orderSearch.setStartShippingDate(deliveryDateStart);
			orderSearch.setEndShippingDate(deliveryDateEnd);

			String shippingType = data.getParameters()
					.getString("shippingType");
			if (shippingType == null || "".equals(shippingType)) {
				orderSearch.setShippingType(null);
			} else {
				orderSearch.setShippingType(shippingType);
			}

		} else if (flag.equals("WaitingForPayment")) {
			// 待付款状态CODE=‘OA001,OA004……哪些状态包含在待付款状态中’
			List<String> statusList = new ArrayList<String>();
			statusList.add("OA001");
			statusList.add("OA003");
			orderSearch.setStatusList(statusList);
		} else if (flag.equals("WaitingForSend")) {
			// 订单Id
			// 货运国家
			String regionId = data.getParameters().getString("regionId");
			// 订单付款时间
			Date paymmentDateStart = data.getParameters().getDate(
					"paymentDateStart");
			Date paymmentDateEnd = data.getParameters().getDate(
					"paymentDateEnd");
			// 备货时间
			Integer maxStockDays = data.getParameters().getIntObject(
					"maxStockDays");
			String maxday = data.getParameters().getString("stockDay");
			if (maxday == null || "".equals(maxday)) {
				orderSearch.setMaxStockDays(maxStockDays);
			} else {
				orderSearch.setMaxStockDays(Integer.parseInt(maxday));
			}
			if (regionId == null || "".equals(regionId)) {
				orderSearch.setShippingCountry(null);
			} else {
				orderSearch.setShippingCountry(regionId);
			}
			orderSearch.setStartPaymentDate(paymmentDateStart);
			orderSearch.setEndPaymentDate(paymmentDateEnd);
			// 待发货状态CODE=‘OS001 …哪些状态包含在待发货状态’
			List<String> statusList = new ArrayList<String>();
			statusList.add("OS001");
			orderSearch.setStatusList(statusList);
		} else if (flag.equals("DisputeOrder")) {
			// 纠纷状态CODE=‘OD001,OD002,OD003 …哪些状态包含在付款确认状态’
			List<String> statusList = new ArrayList<String>();
			statusList.add("OD001");
			statusList.add("OD002");
			statusList.add("OD003");
			orderSearch.setDisputeStatus(statusList);
		} else if (flag.equals("RefundConfirmation")) {
			// 退款确认CODE=‘OR201 …哪些状态包含在退款确认状态’
			List<String> statusList = new ArrayList<String>();
			statusList.add("OR201");
			orderSearch.setRefundStatus(statusList);
		} else if (flag.equals("PaymentConfirmation")) {
			// 订单付款时间
			Date paymentDateStart = data.getParameters().getDate(
					"paymentDateStart");
			Date paymentDateEnd = data.getParameters()
					.getDate("paymentDateEnd");
			if (paymentDateStart == null) {
			} else {
				paymentDateStart = format
						.parse(format.format(paymentDateStart));
			}
			orderSearch.setStartCreateDate(paymentDateStart);
			if (paymentDateEnd == null) {
			} else {
				paymentDateEnd = format.parse(format.format(paymentDateEnd));
			}
			orderSearch.setEndCreateDate(paymentDateEnd);

			// 支付方式
			String paymentType = data.getParameters().get("paymentType");
			if (userId == null || "".equals(userId)) {
				orderSearch.setPaymentType(null);
			} else {
				orderSearch.setPaymentType(paymentType);
			}
			// 付款确认CODE=‘OA002,OA003 …哪些状态包含在付款确认状态’
			List<String> statusList = new ArrayList<String>();
			statusList.add("OA002");
			orderSearch.setStatusList(statusList);
		}

		// 获取模板文件
		HttpServletResponse response = data.getResponse();
		HttpServletRequest request = data.getRequest();
		ServletOutputStream out = response.getOutputStream();

		IOrderManager orderManager = (IOrderManager) getBean("orderManager");
		SearchOrderVo searchOrder = orderManager.getSearchOrderList(orderSearch);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("orders", searchOrder.getOrders());
		
		params.put("StringUtil", new StringUtil());

		// 提供下载
		response.reset();
		response.setContentType("application/x-download;charset=utf-8;");
		response.setHeader("Content-Disposition",
				"attachment;filename=report.xls");
		String path = request.getSession().getServletContext().getRealPath("/");
		String fileUrl = path + "templates" + File.separator
				+ "reportOrder.xls";
		InputStream in = new FileInputStream(fileUrl);

		ExcelUtils.export(in, params, out);
		out.flush();
		out.close();
	}

}
