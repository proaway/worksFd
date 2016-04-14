package com.fd.b2c.manager.modules.actions.product;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.product.bean.ProdMoveMissionBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.JSONUtil;
import com.fd.util.SpiderApi;
import com.fd.util.WebPropUtil;

/**
 * @CreateDate: 2013-3-26 上午09:58:46
 * @author Longli
 * @Description: 获取赠品产品信息
 * 
 */
public class AjaxConfMission extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IProductManager productManager = (IProductManager) getBean("productManager");
		
		int productStatus = data.getParameters().getInt("productStatus", 2);
		int syncStatus = data.getParameters().getInt("syncStatus", 0);
		int syncFreq = data.getParameters().getInt("syncFreq", 30);
		long missionId = data.getParameters().getLong("missionId");
		
		ProdMoveMissionBean mission = new ProdMoveMissionBean();
		
		mission.setMissionId(missionId);
		mission.setProductStatus(productStatus);
		mission.setSyncStatus(syncStatus);
		mission.setSyncFreq(syncFreq);
		
		int i = productManager.updateMoveMission(mission);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (i > 0) {
			WebPropUtil prop = new WebPropUtil();
			mission = productManager.getMoveMission(missionId);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("storeCode", mission.getStoreCode());
			params.put("webName", mission.getWebsite());
			params.put("dataSendUrl", prop.get("product.spider.send.url"));
			params.put("syncFreq", mission.getSyncFreq());
			params.put("syncStatus", mission.getSyncStatus());
			
			JSONObject jsobj = SpiderApi.readDate("CreateSyncTask", params);
			int status = jsobj.getInt("status");
			String msg = jsobj.getString("msg");
			map.put("status", status);
			map.put("msg", msg);
		} else {
			// 设置失败
			map.put("status", 0);
			map.put("msg", "设置失败");
		}
		
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
