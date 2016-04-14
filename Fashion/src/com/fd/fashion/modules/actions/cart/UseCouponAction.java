package com.fd.fashion.modules.actions.cart;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.modules.actions.BaseAction;
import com.fd.fashion.order.bean.CouponBean;
import com.fd.fashion.order.manager.ICouponManager;
import com.fd.fashion.product.vo.CartProductVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.CullNumUtil;
import com.fd.util.JSONUtil;
import com.fd.util.StringUtil;

public class UseCouponAction extends BaseAction{
	@SuppressWarnings("unchecked")
	public void doPerform(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean)session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		
//		String catIds = data.getParameters().getString("catIds");
//		String sellerId = data.getParameters().getString("sellerId");
		String couponCode = data.getParameters().getString("couponCode");
		if (StringUtils.isNotEmpty(couponCode)) {
			couponCode = couponCode.trim();
		}
		double total = data.getParameters().getDouble("totalPrice");
		HashMap<String, Object> map = new HashMap<String, Object>();
		//Date now = new Date();
		ICouponManager couponManager = (ICouponManager) this.getBean("couponManager");
		CouponBean coupon = couponManager.getCoupon(couponCode);
		
		List<CartProductVo> cartProducts = (List<CartProductVo>)session.getAttribute(SessionConstants.KEY_CART_PRODUCT);
		List<CartProductVo> couponProducts = new ArrayList<CartProductVo>();
		
		boolean flag = false;
		if (coupon != null) {
			if(coupon.getStatus() == 1){
				//判断是否绑定userId
				if(coupon.getUserId() != null){
					if(coupon.getUserId().equals(buyer.getUserId())){
						flag = true;
					}else{
						//coupon 指定的buyerid不是当前buyer
						map.put("status", 2);
					}
				}else{
					flag = true;
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date =coupon.getEndDate();
				if(dateCompare(sdf.format(date).toString()) == 1){
					flag = true;
				}else{
					flag = false;
					//过期
					map.put("status", 3);
				}
				
				String numType = coupon.getNumberType();
				if(numType.equals("0") && coupon.getNormalState() == 2){
					flag = false;
					// coupon号已被使用
					map.put("status", 6); 
				}else{
					flag = true;
				}
			}else{
				//coupon被禁用
				map.put("status", 7); 
			}
		}else{
			// 错误coupon号
			map.put("status", 1); 
		}
		
		
		if(flag){
			for(int j=0;j<cartProducts.size();j++){
				CartProductVo cpv = cartProducts.get(j);
				//先判断限定产品
				if(coupon.getLimitProduct() != null){
					String[] skus = coupon.getLimitProduct().split(",");
					for(int i=0;i<skus.length;i++){
						String sku = skus[i];
						if(cpv.getSku().equals(sku)){
							couponProducts.add(cpv);
						}
					}
				}else if(coupon.getLimitCategory() != null){
					//再判断限定目录
					String[] catIds = coupon.getLimitCategory().split(",");
					for(int i=0;i<catIds.length;i++){
						String catId = catIds[i];
						if(cpv.getCatId().startsWith(catId)){
							couponProducts.add(cpv);
						}
					}
				} else {
					couponProducts.add(cpv);
				}
			}
			
			if(couponProducts.size()>0){
				Double pTotal = 0.00;
				String couponProduct = "";
				for(int m=0;m<couponProducts.size();m++){
					CartProductVo cpv = cartProducts.get(m);
					pTotal += cpv.getPrice()*cpv.getQuantity();
					couponProduct += cpv.getProductId();
					if(m != couponProducts.size()-1){
						couponProduct += ",";
					}
				}
				Double totalPrice = 0.00;
				//如果最小消费为空，则初始化为0;
				if(coupon.getMinaMount()==null || coupon.getMinaMount()<1){
					coupon.setMinaMount(0.);
				}
				if(pTotal >= coupon.getMinaMount()){
					if(coupon.getCouponType() == 0){
						//返回现金优惠价格
						map.put("couponAmount", coupon.getCouponAmount());
						totalPrice = total - coupon.getCouponAmount();
					}else if(coupon.getCouponType() == 1){
						//计算折扣优惠价格
						pTotal = pTotal * coupon.getCouponDiscount();
						map.put("couponAmount", CullNumUtil.cullNumTwo(pTotal));
						totalPrice = total - pTotal;
					}
					map.put("couponEndDate", StringUtil.getDateString(coupon.getEndDate()));
					map.put("couponProduct", couponProduct);
					map.put("total", CullNumUtil.cullNumTwo(totalPrice));
					map.put("status", 0);
				}else{
					//不满足最小金
					map.put("minAmount",coupon.getMinaMount());
					map.put("status", 5);
				}

			}else{
				//没有匹配产品
				map.put("status", 4);
			}
			
		}
		
/*		if (coupon == null) {
			map.put("status", 1); // 错误coupon号
		} else if (!StringUtil.isEmpty(coupon.getUserId().toString()) && String.valueOf(buyerId) != coupon.getUserId()) {
			// coupon 指定的buyerid不是当前buyer
			map.put("status", 2);
		} else if (!matchSeller(sellerId, coupon.getCreateId().toString())) {
			// 不是这个Seller的coupon
			map.put("status", 3);
		} else if(now.after(coupon.getEndDate())) {
			// 判断过期
			map.put("status", 4);
		} else if (!matchCategory(catIds, coupon.getLimitCategory())) {
			// 分类不匹配
			map.put("status", 5);
		} else if (!matchMinAmount(total, coupon.getMinaMount())) {
			// 没到最小金额
			map.put("status", 6);
			map.put("minAmount", coupon.getMinaMount());
		} else {
			map.put("status", 0);
			map.put("coupon", coupon);
			double couponPrice = 0;
			if (coupon.getCouponType().equals("0")) {
				couponPrice = coupon.getCouponAmount();
			} else {
				double discount = coupon.getCouponDiscount();
				couponPrice = total * discount / 100.0;
			}
			map.put("couponPrice", CullNumUtil.cullNumTwo(couponPrice));
		}*/
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}

	public static int dateCompare(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime().compareTo(new Date());
    }
	
	public static void main(String[] args){
		System.out.println(dateCompare("2013-04-26"));
	}
}
