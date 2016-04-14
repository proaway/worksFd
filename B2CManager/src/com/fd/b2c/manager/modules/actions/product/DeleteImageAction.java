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

/**
 * @CreateDate:  2013-3-25 下午2:49:51 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class DeleteImageAction extends BaseAction {
	public void doPerform(RunData data, Context context) throws Exception {
		String imageId = data.getParameters().getString("imageId");
		String[] imageIds = imageId.split(",");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");

		PrintWriter out = data.getResponse().getWriter();
		if(imageIds.length > 0){
			if(imageIds.length > 1){
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<imageIds.length;i++){
					if(productManager.deleteImage(imageIds[i])){
						sb.append(imageIds[i]);
						sb.append(",");
					}
				}
				out.print(sb.toString());
			}else{
				if(productManager.deleteImage(imageIds[0])){
					out.print(imageId);
				}else{
					out.print("0");
				}
			}
			
		}
	}


}
