package com.fd.b2c.manager.modules.actions.cms;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.SecureAction;
import com.fd.fashion.cms.bean.CmsBlockBean;
import com.fd.fashion.cms.manager.ICmsManager;

public class EditBlock extends SecureAction {
	@Override
	public void doPerform(RunData data, Context context) throws Exception {
		super.doPerform(data, context);
		
		String channelId = data.getParameters().getString("channelId");
		int location = data.getParameters().getInt("location");
		
		ICmsManager cmsManager = (ICmsManager) getBean("cmsManager");
		
		CmsBlockBean block = cmsManager.getBlock(channelId, location);
		context.put("block", block);
		if (block == null) {
			return;
		}
		if (block.getBlockType() == 1) {
			setTemplate(data, "cms,EditAdBlock.html");
			return;
		}
		setTemplate(data, "cms,EditProdBlock.html");
	}
}
