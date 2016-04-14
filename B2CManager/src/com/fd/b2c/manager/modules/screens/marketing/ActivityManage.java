/**
 * ActivityManage.java
 * @author:  Zhou Rongyu
 */
package com.fd.b2c.manager.modules.screens.marketing;

import java.util.HashMap;
import java.util.List;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.bean.ActivityBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.PageInfo;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate:  2013-5-22 上午11:00:26 
 * @author:  Zhou Rongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ActivityManage  extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		ActivityBean activity = new ActivityBean();
		ParametersUtil.initParameters(data, activity);
		
		context.put("activityName", activity.getActivityName());
		context.put("searchDateType", activity.getSearchDateType());
		context.put("searchStartDate", activity.getSearchStartDate());
		context.put("searchEndDate", activity.getSearchEndDate());
		context.put("type", activity.getType());
		context.put("status", activity.getStatus());
		
		
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		//**********设置PageInfo分页信息
		int pageSize = 4;
		int pageIndex = data.getParameters().getInt("pageIndex", 1);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);

		List<ActivityBean> activityList = productManager.getActivityList(activity,pageInfo);
		context.put("activityList", activityList);
		context.put("pageInfo", pageInfo);
		
		IDictManager dictManager = (IDictManager)this.getBean("dictManager");
		HashMap<Integer,List<RegionBean>> regionMap = new HashMap<Integer,List<RegionBean>>();
		int i = 1;
		while(i<7){
			List<RegionBean> list = dictManager.getRegionByContinentId(i);
			regionMap.put(i, list);
			i++;
		}
		context.put("regionMap", regionMap);
		
	}

}
