package com.fd.b2c.manager.modules.screens.buyer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.buyer.bean.BuyerAddressBookBean;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.buyer.vo.BuyerVo;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.order.bean.OrderBean;
import com.fd.fashion.order.manager.IOrderManager;
import com.fd.payment.bean.PaymentTypeBean;
import com.fd.util.AppContextUtil;
import com.fd.util.CullNumUtil;
import com.fd.util.RegexUtil;
import com.fd.util.StringUtil;

public class BuyerDetail extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerDetailLayout.html");
		Long buyerId = data.getParameters().getLongObject("buyerId");
		if(buyerId==null || buyerId<=0){
			context.put("msg", "orderId 不能为空");
			return ;
		}
		context.put("buyerId",buyerId);
		/**买家基本信息*/
		IBuyerManager buyerManager = (IBuyerManager)AppContextUtil.getBean("buyerManager");
		BuyerBean buyerBean = new BuyerBean();
		buyerBean.setBuyerId(buyerId);
		buyerBean = buyerManager.getBuyerByBuyerId(buyerId);
		if(buyerBean!=null && buyerBean.getIpAddress()!=null){
			context.put("registIp", getIpRegion(buyerBean.getIpAddress()));
		}
		/**获取国家信息*/
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		RegionBean region = dictManager.getRegionByRegionId(buyerBean.getCountry());
		if(region!=null && region.getTimeZone()!=null){
			context.put("regionInfo", region);
		}
		/**支付订单统计*/
		BuyerVo buyerVo = new BuyerVo();
		buyerVo = buyerManager.getBuyerCountInfo(buyerId);
		buyerVo.setBuyer(buyerBean);
		context.put("buyerInfo", buyerVo);
		
		IOrderManager orderManager = (IOrderManager)AppContextUtil.getBean("orderManager");
		/**支付字典*/
		Map<String,PaymentTypeBean> paymentMap = orderManager.getPaymentTypeList();
		context.put("paymentMap", paymentMap);
		
		/**买家支付方式*/
		
		List<PaymentTypeBean> paymentTypeList = orderManager.getBuyerPaymentType(buyerId);
		if(paymentTypeList!=null && paymentTypeList.size()>0){
			context.put("paymentTypeList", paymentTypeList);
		}
		
		/**买家地址本信息*/
		List<BuyerAddressBookBean> books = buyerManager.getBuyerAddressBooks(buyerId);
		String countryId = "230";
		if (books != null && books.size()>0) {
			BuyerAddressBookBean buyerAddr = null;
			for (BuyerAddressBookBean book : books) {
				if(book.getIsDefault() == 1) {
					countryId = String.valueOf(book.getCountry());
					buyerAddr = new BuyerAddressBookBean();
					buyerAddr.setAddressId(book.getAddressId());
					buyerAddr.setAddressLine1(book.getAddressLine1());
					buyerAddr.setAddressLine2(book.getAddressLine2());
					buyerAddr.setCity(book.getCity());
					buyerAddr.setCountry(book.getCountry());
					break;
				}
			}
			if (buyerAddr==null) {
				countryId = String.valueOf(books.get(0).getCountry());
				buyerAddr = books.get(0);
			}
			context.put("defaultAddr", buyerAddr);
			context.put("countryId", countryId);
			context.put("books", books);
			context.put("bookCount", books.size());
		}
		CullNumUtil cu = new CullNumUtil();
		context.put("cu", cu);
		List<OrderBean> orders = orderManager.getOrderBeans(buyerId);
		if(orders!=null && orders.size()>0){
			Map<String,Object> buyerMap = new HashMap<String,Object>();
			OrderBean order = new OrderBean();
			order = orders.get(0);
			buyerMap.put("lastDate", StringUtil.getDateTimeString(order.getCreateDate()));
			buyerMap.put("lastIp", order.getIpAddress());
			if(orders.size()<2){
				buyerMap.put("firstDate",  StringUtil.getDateTimeString(order.getCreateDate()));
				buyerMap.put("firstIp", order.getIpAddress());
			}else{
				order = orders.get(orders.size()-1);
				buyerMap.put("firstDate",  StringUtil.getDateTimeString(order.getCreateDate()));
				buyerMap.put("firstIp", order.getIpAddress());
			}
			Calendar c = Calendar.getInstance();
			if(order.getCreateDate().before(c.getTime())){
				long interlVal = (c.getTime().getTime() - order.getCreateDate().getTime()) / (1000 * 60 * 60 * 24);
				Integer dayCount = buyerManager.getBuyerCreateOrderDayCount(buyerId);
				if(dayCount!=null && dayCount>0){
					
					buyerMap.put("recycleOrderDay", cu.getDoubleToString(cu.cullNum(interlVal/dayCount.intValue()), "1"));
				}
				
			}
			context.put("buyerMap", buyerMap);
			
		}
	}
	
	 /**通过IP地址获取参考国家与来源国家
	 * @param ipAddress
	 * @return
	 * @throws Exception
	 */
	public String getIpRegion(String ipAddress) throws Exception {
	    	ipAddress = ipAddress.trim();
	    	String region = null;
	    	try {
				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(
						"http://www.ip138.com/ips138.asp?action=2&ip=" + ipAddress);
				client.executeMethod(method);
				region = new String(method.getResponseBody(), "gb2312");
				RegexUtil regex = new RegexUtil();
				region = regex
						.getMatchedStr(
								region,
								"<td align=\"center\"><ul class=\"ul1\">([^\r\n]+)</ul></td>",
								1);
			} catch (Exception e) {
				region = "";
			}
			String ip = "<a href='http://www.ip138.com/ips.asp?action=2&ip=" + ipAddress + "' target='_blank'>" + ipAddress + "</a>";
	    	return region == null ? ip : ip + region;
	    }
	
	/*public static void main(String[] args) throws Exception{
		String ip = "192.168.36.115";
		String str = getIpRegion(ip);
		System.out.println(str);
	}*/

}
