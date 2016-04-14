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

/**
 * @CreateDate: 2013-3-26 上午09:58:46
 * @author Longli
 * @Description: 获取赠品产品信息
 * 
 */
public class AjaxAddMoveMission extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String storeUrl = data.getParameters().getString("storeUrl");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeUrl", storeUrl);
		
		JSONObject obj = SpiderApi.readDate("GetStoreCode", params);
		int status = obj.getInt("status");
		if (status == 0) {
			context.put("contentdata", obj.toString());
			return;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		ProdMoveMissionBean mission;
		try {
			JSONObject resData = obj.getJSONObject("data");
			String website = resData.getString("webName");
			String storeCode = resData.getString("storeCode");
			IProductManager productManager = (IProductManager) getBean("productManager");
			mission = productManager.addMoveMission(website, storeUrl,
					storeCode);
			map.put("status", 1);
			map.put("mission", mission);
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", "增加任务失败");
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
