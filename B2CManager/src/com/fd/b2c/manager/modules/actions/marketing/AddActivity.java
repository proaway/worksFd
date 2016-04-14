/**
 * CreateCoupon.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.marketing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.bean.GiftsBean;
import com.fd.fashion.product.bean.GroupsBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate:  2013-4-24 下午4:23:54 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class AddActivity extends SecureAction {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		ActivityBean activity = new ActivityBean();
		ParametersUtil.initParameters(data, activity);
		
		String[] countrys = data.getParameters().getStrings("countryIds");
		if(countrys != null){
			String country = "";
			for(int i=0;i<countrys.length;i++){
				country += countrys[i] + ",";
			}
			activity.setCountry(country);
		}
		String activityId = String.valueOf(System.nanoTime());
		
		activity.setActivityId(activityId);
		Date date = new Date();
		activity.setCreateTime(date);
		if(date.getTime() - activity.getStartTime().getTime()>=0){
			activity.setStatus(1);
		}else{
			activity.setStatus(0);
		}
		if(date.getTime() - activity.getEndTime().getTime() > 0){
			activity.setStatus(2);
		}
		IProductManager productManager = (IProductManager) getBean("productManager");
		//1：赠品  0：组合优惠
		if(1 == activity.getType()){
			
			long[] zpProductIds = data.getParameters().getLongs("zpProductId");
			List<GiftsBean> pList = new ArrayList<GiftsBean> ();
			for(int i=0;i<zpProductIds.length;i++){
				GiftsBean gb = new GiftsBean();
				long productId = zpProductIds[i];
				int ntype = data.getParameters().getInt("ntype_"+productId);
				gb.setProductId(productId);
				gb.setNumType(ntype);
				if(1==ntype){
					int num = data.getParameters().getInt("num_"+productId);
					gb.setNum(num);
				}
				pList.add(gb);
			}
			
			productManager.addGiftsActivity(activity, pList);
		}else if(0 == activity.getType()){
			long[] zpProductIds = data.getParameters().getLongs("groupProductId");
			GroupsBean gb = new GroupsBean();
			if(zpProductIds.length >= 1){
				gb.setGroupProductId1(zpProductIds[0]);
				Double groupDiscount1 = data.getParameters().getDouble("groupDiscount1");
				gb.setGroupDiscount1(groupDiscount1);
			}
			if(zpProductIds.length >= 2){
				gb.setGroupProductId2(zpProductIds[1]);
				Double groupDiscount2 = data.getParameters().getDouble("groupDiscount2");
				gb.setGroupDiscount2(groupDiscount2);
			}
			if(zpProductIds.length >= 3){
				gb.setGroupProductId3(zpProductIds[2]);
				Double groupDiscount3 = data.getParameters().getDouble("groupDiscount3");
				gb.setGroupDiscount3(groupDiscount3);
			}
			if(zpProductIds.length >= 4){
				gb.setGroupProductId4(zpProductIds[3]);
				Double groupDiscount4 = data.getParameters().getDouble("groupDiscount4");
				gb.setGroupDiscount4(groupDiscount4);
			}
			productManager.addGroupActivity(activity, gb);
		}
		
		context.put("contentdata", 1);
	}

}
