/**
 * DeleteImageAction.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.actions.product;

import java.io.PrintWriter;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.actions.BaseAction;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;
import com.fd.util.Constants;

/**判断图片空间大小
 * @CreateDate:  2013-3-25 下午2:49:51 
 * @author:  zhangling
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class SellerImageSizeAction extends BaseAction {
	public void doPerform(RunData data, Context context) throws Exception {
		//sellerid,直接从登录的用户对象中取值
		long sellerId = 1;
		
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");

		PrintWriter out = data.getResponse().getWriter();
		
		long imageSize = productManager.getImageSize(sellerId);
		if(imageSize == 0){
			out.print("1");
		}else{
			if(imageSize>Constants.USER_IMAGE_SIZE){
				out.print("0");
			}else{
				out.print("1");	
			}
		}
		out.flush();
		out.close();
	}

}
