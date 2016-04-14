/**
 * ImageZone.java
 * @author:  ZhouRongyu
 */
package com.fd.b2c.manager.modules.screens.product;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ImageCatBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.util.AppContextUtil;
import com.fd.util.Constants;
import com.fd.util.PageInfo;

/**
 * @CreateDate:  2013-3-25 上午9:52:27 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
public class ImageZone extends SecureScreen{
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");

		ImageBean imageBean  = new ImageBean();
		HashMap<String, String> params = new HashMap<String, String>();
		//排序参数
		String orderStatus = data.getParameters().getString("orderStatus");
		if (StringUtils.isNotEmpty(orderStatus)) {
			imageBean.setOrderByStatus(orderStatus);
		}
		
		//搜索参数
		String keyword = data.getParameters().get("keyword");
		if (StringUtils.isNotEmpty(keyword)) {
			keyword = URLDecoder.decode(keyword, "utf-8");
			params.put("keyword", keyword);
		}
		
		//分组参数
		String catId = data.getParameters().getString("catId");
		if (StringUtils.isNotEmpty(catId)) {
			imageBean.setCatId(catId);
		}
		
		//筛选参数
		String datepickera = data.getParameters().getString("datepickera");
		if (StringUtils.isNotEmpty(datepickera)) {
			params.put("startDate", datepickera);
		}
		String datepickerb = data.getParameters().getString("datepickerb");
		if (StringUtils.isNotEmpty(datepickerb)) {
			params.put("endDate", datepickerb + " 23:59:59.999");
		}
		
		String operator = data.getParameters().getString("operator");
		if (StringUtils.isNotEmpty(operator) && !"0".equals(operator)) {
			imageBean.setOperator(operator);
		}
		
		imageBean.setParams(params);
		context.put("imageCondition", imageBean);
		
		//**********设置PageInfo分页信息
		int pageSize = 20;		
		String pageIndexStr = data.getParameters().get("pageIndex");
		int pageIndex = 1;
		try{
		       pageIndex = Integer.parseInt(pageIndexStr);
		}catch(Exception e){
		       pageIndex = 1;
		}	       
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setPageSize(pageSize);
		
		List<ImageBean> images = productManager.getImagesByStatus(imageBean, pageInfo);
		params.put("endDate", datepickerb);
		context.put("images", images);
		
		List<ImageCatBean> imageCatList = productManager.getImagesCatList();
		context.put("imageCatList", imageCatList);
		if (StringUtils.isNotEmpty(catId)) {
			for (ImageCatBean imgCat : imageCatList) {
				if (catId.equals(String.valueOf(imgCat.getCatId()))) {
					context.put("imgCat", imgCat);
					break;
				}
			}
		}
		if("0".equals(catId)){
			context.put("imgCat", 0);
		}
		
		context.put("pageInfo", pageInfo);
		
		long imageSize = productManager.getImageSize(0);
		
		context.put("maxSize", Constants.USER_IMAGE_SIZE);
		context.put("usedSize", imageSize);
		context.put("usedBarSize", imageSize*100/Constants.USER_IMAGE_SIZE);
	}

}
