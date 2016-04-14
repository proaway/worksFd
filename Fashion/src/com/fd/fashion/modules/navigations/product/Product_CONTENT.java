package com.fd.fashion.modules.navigations.product;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.turbine.modules.navigations.VelocityNavigation;
import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.constants.SessionConstants;
import com.fd.fashion.buyer.bean.BuyerBean;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.bean.SizeAttrBean;
import com.fd.fashion.dict.bean.SizeColattrBean;
import com.fd.fashion.dict.bean.SizeRowattrBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.product.bean.ImageBean;
import com.fd.fashion.product.bean.ProductBean;
import com.fd.fashion.product.manager.IProductManager;
import com.fd.fashion.product.vo.ProductVo;
import com.fd.fashion.seller.bean.SizeTemplateBean;
import com.fd.fashion.seller.bean.SizeTemplateDetailBean;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.AppContextUtil;
import com.fd.util.FileUtil;
import com.fd.util.RewriteUtil;

/**
 * @CreateDate:  2013-3-13 下午08:17:27 
 * @author:  ZhouRongyu
 * @Description:  产品详细页详细描述模块
 * 
 * @version:  V1.0
 */
public class Product_CONTENT extends VelocityNavigation{
	
	@SuppressWarnings("unchecked")
	protected void doBuildTemplate(RunData data, Context context) throws Exception {
		data.setCharSet("UTF-8");
		
		ProductVo productVo = (ProductVo) context.get("productVo");
		String specificationsContent = "";;// 产品详细内容
		try {
			//产品详细描述
			if(productVo == null) {
				return;
			}
			String specificationsUrl = productVo.getProduct().getSpecifications();
			
			try {
				if (new File(specificationsUrl).exists())
					specificationsContent = FileUtil.readFile(
							specificationsUrl, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
	//		specificationsContent = WordsFilterUtil.wordsFilter(
	//				specificationsContent, WordsFilterUtil.FILTER_TYPE_ALL,
	//				true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.put("specificationsContent", specificationsContent);
		
		
		IProductManager productManager = (IProductManager)AppContextUtil.getBean("productManager");
		
		//获取产品Template信息
		
		Long templateId = productVo.getProduct().getSizeTemplateId();
		
		SizeTemplateBean sizeTemplateBean = productManager.getSizeTemplateBean(templateId);
		if(sizeTemplateBean != null){
			
			if(sizeTemplateBean.getImageId() != null){
				//获取size规格图片
				ImageBean imageBean = new ImageBean();
				imageBean.setImageId(sizeTemplateBean.getImageId());
				imageBean = productManager.getImageBean(imageBean);
				String imageUrl = RewriteUtil.getImageUrl(imageBean.getImageUrl());
				context.put("imageUrl", imageUrl);
			}
			
			//获取产品Template 分类信息
			//SizeCatBean sizeCatBean = new SizeCatBean();
			//sizeCatBean = productManager.getSizeCatBean(sizeTemplateBean.getSizecatId());
			
			//获取产品Template列信息
			List<SizeColattrBean> sizeColattrBeanList = productManager.getSizeColattrBean(sizeTemplateBean.getSizecatId());
			
			
			// 按index排序
			@SuppressWarnings("rawtypes")
			Comparator comp = new Comparator() {
				public int compare(Object o1, Object o2) {
					SizeColattrBean sizeColattrBean1 = (SizeColattrBean) o1;
					SizeColattrBean sizeColattrBean2 = (SizeColattrBean) o2;
					return (sizeColattrBean1.getIndexId() < sizeColattrBean2.getIndexId() ? 1
							: (sizeColattrBean1.getIndexId() > sizeColattrBean2.getIndexId() ? -1
									: 0));
				}
			};
			Arrays.sort(sizeColattrBeanList.toArray(), comp);
			
			//封装产品SizeCat列属性名称List:sizeAttrColList
			List<String> sizeAttrColList = new ArrayList<String>();
			for(int i=0;i<sizeColattrBeanList.size();i++){
				Long attrId = sizeColattrBeanList.get(i).getAttrId();
				SizeAttrBean sizeAttrBean = new SizeAttrBean();
				sizeAttrBean = productManager.getSizeAttrBean(attrId);
				sizeAttrColList.add(sizeAttrBean.getName());
			}
			
			
			//获取产品SizeCat行信息
			List<SizeRowattrBean> sizeRowattrBeanList = productManager.getSizeRowattrBean(sizeTemplateBean.getSizecatId());
			
			// 按index排序
			@SuppressWarnings("rawtypes")
			Comparator comp2 = new Comparator() {
				public int compare(Object o1, Object o2) {
					SizeRowattrBean sizeRowattrBean1 = (SizeRowattrBean) o1;
					SizeRowattrBean sizeRowattrBean2 = (SizeRowattrBean) o2;
					return (sizeRowattrBean1.getIndexId() < sizeRowattrBean2.getIndexId() ? 1
							: (sizeRowattrBean1.getIndexId() > sizeRowattrBean2.getIndexId() ? -1
									: 0));
				}
			};
			Arrays.sort(sizeRowattrBeanList.toArray(), comp2);
			
			//封装产品SizeCat行属性名称List：sizeAttrRowList
			List<String> sizeAttrRowList = new ArrayList<String>();
			for(int i=0;i<sizeRowattrBeanList.size();i++){
				Long attrId = sizeRowattrBeanList.get(i).getAttrId();
				SizeAttrBean sizeAttrBean = new SizeAttrBean();
				sizeAttrBean = productManager.getSizeAttrBean(attrId);
				sizeAttrRowList.add(sizeAttrBean.getName());
			}
			
			
			
			//获取产品SizeCat详细数据并封装
			List<SizeTemplateDetailBean> stdbList = productManager.getSizeTemplateBeanDetailList(templateId);
			
			List<List<String>> sizeAttrList = new ArrayList<List<String>>();
			sizeAttrList.add(sizeAttrColList);
			for(int n=0;n<sizeRowattrBeanList.size();n++){
				SizeRowattrBean srb = sizeRowattrBeanList.get(n);
				Long rowId = srb.getRowId();
				List<String> attrList = new ArrayList<String>();
				attrList.add(sizeAttrRowList.get(n));
				for(int m=0;m<stdbList.size();m++){
					SizeTemplateDetailBean stdb = stdbList.get(m); 
					if(stdb.getRowId().longValue() == rowId.longValue()){
						for(int z=2;z<sizeAttrColList.size()+2;z++){
							if(stdb.getColId().intValue() == z){
								attrList.add(stdb.getAttrValue());
							}
						}
					}
				}
				sizeAttrList.add(attrList);
			}
			
			//System.out.println(sizeAttrList.toString());
			context.put("sizeAttrList", sizeAttrList);
		
		}
		
		IDictManager dictManager = (IDictManager)AppContextUtil.getBean("dictManager");
		List<RegionBean> countries = dictManager.getCountries();
		context.put("countries", countries);

		FdSession session = FdSessionFactory.getFdSession(data);
		BuyerBean buyer = (BuyerBean) session.getAttribute(SessionConstants.KEY_LOGIN_BUYER);
		if(buyer != null){
			context.put("buyerCountry",buyer.getCountry());
		}
		
	}

}
