package com.fd.b2c.manager.modules.screens.buyer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.buyer.manager.IBuyerManager;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.util.PageInfo;

public class BuyerIndex extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/BuyerManagerLayout.html");
		
		IDictManager dictManager = (IDictManager)getBean("dictManager");
		List<RegionBean> regions = dictManager.getCountries();
		if(regions!=null && regions.size()>0){
			Map<String,RegionBean> map = new HashMap<String,RegionBean>();
			for(RegionBean rb :regions){
				map.put(rb.getRegionId(), rb);
			}
			context.put("regionMap", map);
		}
		context.put("regionLists", regions);
		/*查询条件*/
		String userId = data.getParameters().getString("userId");
		String countryId = data.getParameters().get("countryId");
		Date regStartDate = data.getParameters().getDate("regStartDate");
		Date regEndDate = data.getParameters().getDate("regEndDate");
		String userLevel = data.getParameters().getString("userLevel");
		/*注入查询*/
		BuyerBean buyer = new BuyerBean();
		buyer.setUserId(userId);
		buyer.setCountry(countryId);
		if(userLevel!=null && !"".equals(userLevel)){
			buyer.setUserLevel(Integer.parseInt(userLevel));
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(regStartDate==null){
		}else{
			regStartDate = format.parse(format.format(regStartDate));
			buyer.setRegStartDate(regStartDate);
		}
		if(regEndDate==null){
		}else{
			regEndDate = format.parse(format.format(regEndDate));
			buyer.setRegEndDate(regEndDate);
		}
		
		/**分页信息*/
		PageInfo pageInfo = new PageInfo();
		int nowPage = data.getParameters().getInt("pageIndex", 1);
		pageInfo.setPageIndex(nowPage);
		pageInfo.setPageSize(10);
		context.put("pageInfo", pageInfo);
		
		IBuyerManager buyerManager = (IBuyerManager)getBean("buyerManager");
		List<BuyerBean> buyerList = buyerManager.getBuyerList(buyer, pageInfo);
		if(buyerList!=null && buyerList.size()>0){
			context.put("buyerList", buyerList);
		}
		context.put("buyer", buyer);
	}
}
