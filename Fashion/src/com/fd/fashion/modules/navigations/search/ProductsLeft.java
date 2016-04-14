package com.fd.fashion.modules.navigations.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatAttrVo;
import com.fd.fashion.dict.vo.CatConfNode;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.SearchAttrVo;
import com.fd.fashion.product.vo.SearchProductVo;
import com.fd.util.AppContextUtil;

/** 2013-04-09
 * @author zhangling
 *
 */
public class ProductsLeft extends VelocityNavigation{
	
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
//		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
//		String catId = (String) context.get("catId");
//		if (StringUtils.isNotEmpty(catId)) {
////			List<CategoryBean> parentsCategories = dictManager.getParentsCategories(catId);
////			context.put("parentsCategories", parentsCategories);
//			
//			List<CatAttrNode> listCatAttr = dictManager.getCatAttributeNodes(catId);
//			List<CatConfNode> listCatConfig = dictManager.getCatConfigNodes(catId);
//			if(listCatConfig!=null && listCatConfig.size()>0){
//				String configIds[] = data.getParameters().getStrings("config_id");
//				if (configIds != null) {
//					List<String> list = new ArrayList<String>();
//					for (String str : configIds) {
//						list.add(str);
//					}
//					for (CatConfNode conf : listCatConfig) {
//						if (conf.getNodes() != null && conf.getNodes().size()>0) {
//							for (CatConfNode c : conf.getNodes()) {
//								if (list.contains((String.valueOf(c.getConfig().getId())))) {
//									c.setChecked(true);
//								}
//							}
//						}
//					}
//				}
//				context.put("listCatConfigs", listCatConfig);
//			}
//			if(listCatAttr!=null && listCatAttr.size()>0){
//				String attrIds[] = data.getParameters().getStrings("attr_id");
//				if (attrIds != null) {
//					List<String> list = new ArrayList<String>();
//					for (String str : attrIds) {
//						list.add(str);
//					}
//					for (CatAttrNode attr : listCatAttr) {
//						if (attr.getNodes() != null && attr.getNodes().size()>0) {
//							for (CatAttrNode a : attr.getNodes()) {
//								if (list.contains((String.valueOf(a.getAttr().getId())))) {
//									a.setChecked(true);
//								}
//							}
//						}
//					}
//				}
//				context.put("listCatAttrs", listCatAttr);
//			}
//		}
		IProductManager productManager = (IProductManager) AppContextUtil.getBean("productManager");
		SearchProductVo searchProduct = (SearchProductVo) context.get("searchProductVo");

		List<SearchAttrVo> searchConfs = productManager.searchProductConfs(searchProduct);
		if (searchConfs != null && searchConfs.size()>0) {
			String configIds[] = data.getParameters().getStrings("config_id");
			List<String> list = new ArrayList<String>();
			if (configIds != null) {
				for (String str : configIds) {
					list.add(str);
				}
			}
			for (SearchAttrVo attr : searchConfs) {
				if (attr.getNodes() != null && attr.getNodes().size() > 0) {
					for (SearchAttrVo a : attr.getNodes()) {
						if (list.contains((String.valueOf(a.getAttrId())))) {
							a.setChecked(true);
						}
					}
				}
			}
			context.put("searchConfs", searchConfs);
		}
		List<SearchAttrVo> searchAttrs = productManager.searchProductAttrs(searchProduct);
		if (searchAttrs != null && searchAttrs.size()>0) {
			String attrIds[] = data.getParameters().getStrings("attr_id");
			List<String> list = new ArrayList<String>();
			if (attrIds != null) {
				for (String str : attrIds) {
					list.add(str);
				}
			}
			for (SearchAttrVo attr : searchAttrs) {
				if (attr.getNodes() != null && attr.getNodes().size() > 0) {
					for (SearchAttrVo a : attr.getNodes()) {
						if (list.contains((String.valueOf(a.getAttrId())))) {
							a.setChecked(true);
						}
					}
				}
			}
			context.put("searchAttrs", searchAttrs);
		}
	}
}
