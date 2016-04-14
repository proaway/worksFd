package com.fd.b2c.manager.modules.actions.product;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
public class AjaxVerifyMission extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		IProductManager productManager = (IProductManager) getBean("productManager");
		
		String verifyUrl = data.getParameters().getString("verifyUrl");
		long missionId = data.getParameters().getLong("missionId");
		
		ProdMoveMissionBean mission = productManager.getMoveMission(missionId);
		
		JSONObject res = SpiderApi.readVerify(verifyUrl, mission.getVerifyCode());
		int status = res.getInt("status");
		if (status == 0) {
			context.put("contentdata", res.toString());
			return;
		}
		JSONObject resData = res.getJSONObject("data");
		String website = resData.getString("webName");
		String storeCode = resData.getString("storeCode");
		String verifyCode = resData.getString("verifyCode");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!website.equals(mission.getWebsite())) {
			// 域名错误
			map.put("status", 0);
			map.put("msg", "网站域名错误");
		} else if (!storeCode.equals(mission.getStoreCode())) {
			// 店铺错误
			map.put("status", 0);
			map.put("msg", "店铺代码错误");
		} else if (!mission.getVerifyCode().equals(verifyCode)) {
			// 校验失败
			map.put("status", 0);
			map.put("msg", "校验失败");
		} else {
			// 校验成功
			map.put("status", 1);
			map.put("msg", "校验成功");
			mission.setVerifyUrl(verifyUrl);
			mission.setVerifyTime(new Date());
			mission.setVerifyStatus(1);
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("webName", website);
			params.put("storeCode", storeCode);
			JSONObject jo = SpiderApi.readDate("GetStoreProductCount", params);
			if (jo.getInt("status") == 1) {
				int productCount = jo.getJSONObject("data").getInt("productCount");
				mission.setProductCount(productCount);
			}
			productManager.updateMoveMission(mission);
			map.put("mission", mission);
		}
		
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
