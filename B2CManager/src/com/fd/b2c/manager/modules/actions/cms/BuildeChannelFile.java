package com.fd.b2c.manager.modules.actions.cms;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.cms.util.AnalyseChannel;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-22 下午05:23:42
 * @author Longli
 * @Description: 保存频道明细
 * 
 */
public class BuildeChannelFile extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		ICmsManager cmsManager = (ICmsManager) getBean("cmsManager");
		String channelId = data.getParameters().getString("channelId");
		
		boolean succeed = cmsManager.publishChannel(channelId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (succeed) {
			map.put("status", 1);
			map.put("channelId", channelId);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
}
