package com.fd.b2c.manager.modules.actions.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.bean.CmsChannelBean;
import com.fd.fashion.cms.bean.CmsTemplateBean;
import com.fd.fashion.cms.util.AnalyseChannel;
import com.fd.fashion.cms.util.CmsTagsConfig;
import com.fd.util.FileUtil;
import com.fd.util.ParametersUtil;
import com.fd.util.RegexUtil;

/**
 * @CreateDate: 2013-5-22 下午12:11:24
 * @author Longli
 * @Description: ajax动态获取每个block的代码，用模版替换
 * 
 */
public class ReplaceCmsBlock extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String templatePath = data.getParameters()
					.getString("templatePath");
			CmsBlockBean block = new CmsBlockBean();
			ParametersUtil.initParameters(data, block);
			String templateContent = FileUtil.readFile(templatePath);
			RegexUtil regex = new RegexUtil();
			List<String> blocks = regex.getAllMatched(templateContent,
					CmsTagsConfig.BLOCK, 0);
			String blockStr = blocks.get(block.getBlockLocation());
			AnalyseChannel analyse = new AnalyseChannel();
			int templateType = data.getParameters().getInt("templateType");
			CmsTemplateBean template = new CmsTemplateBean();
			template.setTemplateType(templateType);
			String channelUrl = data.getParameters().getString("channelUrl");
			CmsChannelBean channel = new CmsChannelBean();
			channel.setChannelId(block.getChannelId());
			channel.setChannelUrl(channelUrl);
			blockStr = analyse.replaceBlockInfo(block, blockStr, channel,
					template);
			blockStr = blockStr.replaceAll("^<div block>", "");
			blockStr = blockStr.replaceAll("</div>$", "");

			context.put("contentdata", blockStr);
		} catch (Exception e) {
			e.printStackTrace();
			context.put("contentdata", "");
		}
	}
}
