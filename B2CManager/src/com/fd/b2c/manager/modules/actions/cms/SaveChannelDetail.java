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
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-22 下午05:23:42
 * @author Longli
 * @Description: 保存频道明细
 * 
 */
public class SaveChannelDetail extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		CmsChannelBean channel = new CmsChannelBean();
		ParametersUtil.initParameters(data, channel);
		
		String channelId = channel.getChannelId();
		ICmsManager cmsManager = (ICmsManager) getBean("cmsManager");
		List<CmsBlockBean> blocks = cmsManager.getChannelBlocks(channelId);
		
		for(int i=0; i<blocks.size(); i++) {
			Long infoId = data.getParameters().getLongObject("infoId_" + i);
			String linkUrl = data.getParameters().getString("linkUrl_" + i);
			String imgUrl = data.getParameters().getString("imgUrl_" + i);
			String title = data.getParameters().getString("title_" + i);
			String summary = data.getParameters().getString("summary_" + i);
			String imgAlt = data.getParameters().getString("imgAlt_" + i);
			String sku = data.getParameters().getString("sku_" + i);
			CmsBlockBean block = blocks.get(i);
			block.setInfoId(infoId);
			block.setImgAlt(decode(imgAlt));
			block.setImgUrl(decode(imgUrl));
			block.setTitle(decode(title));
			block.setSummary(decode(summary));
			block.setSku(decode(sku));
			block.setLinkUrl(decode(linkUrl));
		}
		
		int i = cmsManager.updateCmsChannel(channel, blocks);
		Map<String, Object> map = new HashMap<String, Object>();
		if (i > 0) {
			map.put("status", 1);
			map.put("channelId", channelId);
		} else {
			map.put("status", 0);
		}
		context.put("contentdata", JSONUtil.obj2JSON(map));
	}
	
	private String decode(String str) throws Exception {
		if (str != null) {
			str = URLDecoder.decode(str, "utf-8");
		}
		return str;
	}
}
