package com.fd.fashion.modules.screens.search;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.modules.screens.BaseScreen;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.fashion.vo.BreadCrumb;
import com.fd.util.CullNumUtil;
import com.fd.util.PageInfo;
import com.fd.util.RewriteUtil;


/** 2013-04-09
 * @author zhangling
 *
 */
public class ProductsList  extends BaseScreen {
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/ProductsListLayout.html");
		
		IProductManager productManager = (IProductManager)getBean("productManager");
		//关键词搜索
		String keywords = data.getParameters().getString("keywords");
		String catId = data.getParameters().getString("catId");
		context.put("catId", catId);
		//获取目录导航
		//String productCategories = productManager.getProductCategories(catId);
		List<BreadCrumb> breadCrumbs = new ArrayList<BreadCrumb>();
		if (StringUtils.isNotEmpty(catId)) {
			ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
			List<CustomCategoryBean> cats = sellerManager.getParentsCats(catId);
			for (CustomCategoryBean cat : cats) {
				BreadCrumb bc = new BreadCrumb();
				bc.setName(cat.getCatName());
				bc.setUrl(RewriteUtil.getCategoryUrl(cat.getCatName(), cat.getCatId()));
				breadCrumbs.add(bc);
			}
			CustomCategoryBean cat = cats.get(cats.size()-1);
			context.put("tag", cat.getCatName());
		}
		if(StringUtils.isNotEmpty(keywords)) {
			keywords = URLDecoder.decode(keywords, "utf-8");
			context.put("keywords", keywords);
			context.put("tag", keywords);
			BreadCrumb bc = new BreadCrumb();
			bc.setName("\"" + keywords + "\"");
			bc.setUrl(RewriteUtil.getKeywordUrl(keywords));
			breadCrumbs.add(bc);
		}
		
		if(StringUtils.isEmpty(keywords) && StringUtils.isEmpty(catId)) {
			BreadCrumb bc = new BreadCrumb();
			bc.setName("Search");
			bc.setUrl("");
			breadCrumbs.add(bc);
			context.put("tag", "Search");
		}
		context.put("breadCrumbs", breadCrumbs);
		//grid or list显示方式
		String isList = data.getParameters().getString("isList","0");
		context.put("isList", isList);
		
		String[] attrIds = data.getParameters().getStrings("attr_id");
		String[] configIds = data.getParameters().getStrings("config_id");
		
		double priceStart = data.getParameters().getFloat("priceStart");
		double priceEnd = data.getParameters().getFloat("priceEnd");
		
		//排序方式
		String orderType = data.getParameters().getString("orderType","");
		context.put("orderType", orderType);
		//每页显示的数量
		int pageSize = data.getParameters().getInt("pageSize",20);
		//当前页码
		int pageIndex = data.getParameters().getInt("pageIndex",1);
		PageInfo pageInfo = new PageInfo(pageIndex,pageSize);
		context.put("pageInfo", pageInfo);
		
		SearchProductVo searchProduct = new SearchProductVo();
		searchProduct.setProductStatus(1);
		searchProduct.setCatId(catId);
		searchProduct.setKey(keywords);
		searchProduct.setOrderType(orderType);
		if(priceStart>0){
			searchProduct.setPriceStart(priceStart);
		}else{
			searchProduct.setPriceStart(0d);
		}
		if(priceEnd>0){
			searchProduct.setPriceEnd(priceEnd);
		}
		List<Long> attrList = new ArrayList<Long>();
		List<Long> configList = new ArrayList<Long>();
		if(attrIds!=null && attrIds.length>0){
			//List<String> attrs = new ArrayList<String>();
			for (int i = 0; i < attrIds.length; i++) {
//				String[] str = attrIds[i].split("_");
				attrList.add(Long.valueOf(attrIds[i]));
//				attrs.add(attrIds[i]);
			}
//			context.put("attrIds", JSONUtil.list2JSON(attrs));
			searchProduct.setAttrList(attrList);
		}
		if(configIds!=null && configIds.length>0){
			//List<String> configs = new ArrayList<String>();
			for (int i = 0; i < configIds.length; i++) {
//				String[] str = configIds[i].split("_");
				configList.add(Long.valueOf(configIds[i]));
//				configs.add(configIds[i]);
			}
//			context.put("configIds", JSONUtil.list2JSON(configs));
			searchProduct.setConfigsList(configList);
		}
		
		
		searchProduct = productManager.getProductByKey(searchProduct, pageInfo);
		if(null != searchProduct){
			int count = searchProduct.getProductList().size();
			int count1 = count%4;
			if(count1==0){
				context.put("productLineLen", count/4);
			}else{
				context.put("productLineLen", (count/4)+1);
			}
			if(priceStart>0){
				context.put("priceStart", priceStart);
			}
			if(priceEnd>0){
				context.put("priceEnd", priceEnd);
			}
		}
		for(int i=0;i<searchProduct.getProductList().size();i++){
			ProductVo pv = searchProduct.getProductList().get(i);
			int position = i+((pageIndex-1)*pageSize) + 1;
			pv.setPosition(position);
		}
		context.put("searchProductVo", searchProduct);
		
		int count=0;
		for (CustomCategoryBean c : searchProduct.getCategoryList()) {
			count += c.getProductCount();
		}
		context.put("searchCount", count);
		
		CullNumUtil cu = new CullNumUtil();
		context.put("cu", cu);
	}
}
