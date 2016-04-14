package com.fd.b2c.manager.modules.actions.cms;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.manager.ICmsManager;
import com.fd.fashion.cms.util.AnalyseChannel;
import com.fd.fashion.cms.util.CmsUtil;
import com.fd.util.BeanUtil;
import com.fd.util.JSONUtil;
import com.fd.util.ParametersUtil;

/**
 * @CreateDate: 2013-5-22 下午05:23:42
 * @author Longli
 * @Description: 保存频道明细
 * 
 */
public class ReviewChannel extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		CmsChannelBean channel = new CmsChannelBean();
		ParametersUtil.initParameters(data, channel);
		
		String channelId = channel.getChannelId();
		
		ICmsManager cmsManager = (ICmsManager) getBean("cmsManager");
		CmsChannelBean dbChannel = cmsManager.getCmsChannel(channelId);
		BeanUtil.copyProperties(dbChannel, channel);

		List<CmsBlockBean> blocks = cmsManager.getChannelBlocks(channelId);
		
		for(int i=0; i<blocks.size(); i++) {
			Long infoId = data.getParameters().getLongObject("infoId_" + i);
			String linkUrl = data.getParameters().getString("linkUrl_" + i);
			String imgUrl = data.getParameters().getString("imgUrl_" + i);
			String title = data.getParameters().getString("title_" + i);
			String summary = data.getParameters().getString("summary_" + i);
			String imgAlt = data.getParameters().getString("imgAlt_" + i);
			String sku = data.getParameters().getString("sku_" + i);
			CmsBlockBean block = new CmsBlockBean();
			block.setInfoId(infoId);
			block.setImgAlt(decode(imgAlt));
			block.setImgUrl(decode(imgUrl));
			block.setTitle(decode(title));
			block.setSummary(decode(summary));
			block.setSku(decode(sku));
			block.setLinkUrl(decode(linkUrl));
			BeanUtil.copyProperties(blocks.get(i), block);
		}
		
		CmsTemplateBean template = cmsManager.getCmsTemplate(dbChannel.getTemplateId());
		
		AnalyseChannel analyse = new AnalyseChannel();
		boolean succeed = analyse.buildChannelReviewFile(template, dbChannel, blocks);
		if (succeed) {
			String url = CmsUtil.getChannelViewUrl(dbChannel.getChannelUrl());
			data.getResponse().sendRedirect(url);
		} else {
			context.put("contentdata", "创建预览文件失败");
		}
	}
	
	private String decode(String str) throws Exception {
		if (StringUtils.isNotEmpty(str)) {
			str = URLDecoder.decode(str, "utf-8");
			return str;
		}
		return null;
	}
}
