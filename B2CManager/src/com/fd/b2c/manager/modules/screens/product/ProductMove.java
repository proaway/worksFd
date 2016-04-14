/**
 * ProductUpload.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.bean.ProdMoveMissionBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.RegexUtil;

/**
 * @CreateDate:  2013-3-21 上午11:30:40 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ProductMove extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		IProductManager productManager = (IProductManager) getBean("productManager");
		
		List<ProdMoveMissionBean> moveMissions = productManager.getMoveMissions();
		context.put("moveMissions", moveMissions);
	}
}
