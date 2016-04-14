/**
 * ShippingManager.java
 * @author:  ZhouRongyu
 */
package com.fd.fashion.seller.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.dict.bean.ShippingCostBean;
import com.fd.fashion.dict.service.IDictService;
import com.fd.fashion.seller.bean.ShippingDetailBean;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.bean.ShippingOptionBean;
import com.fd.fashion.seller.service.ISellerService;
import com.fd.fashion.seller.vo.ResultRateVo;
import com.fd.fashion.seller.vo.ShippingDetailVo;
import com.fd.fashion.seller.vo.ShippingRateVo;
import com.fd.fashion.seller.vo.ShippingTemplateDetailVo;
import com.fd.fashion.seller.vo.ShippingTemplateVo;


/**
 * @CreateDate:  2013-3-15 下午05:29:36 
 * @author:  ZhouRongyu
 * @Description:  TODO
 * 
 * @version:  V1.0
 */
@Transactional(rollbackFor=Exception.class)
@Component
public class ShippingManager implements IShippingManager {
	
/*	@Autowired
	@Qualifier("productService")
	IProductService productService;*/
	
	@Autowired
	@Qualifier("sellerService")
	ISellerService sellerService;
	
	@Autowired
	@Qualifier("dictService")
	IDictService dictService;
	/**
	 * 得到运费计算结果
	 * 
	 * @param shippingRateVo
	 *            将运算条件封装为一个Vo作为传入参数
	 * @return
	 * @throws Exception
	 */
	public List<ResultRateVo> getShippingCalculateResult(ShippingRateVo shippingRateVo) throws Exception {
		return null;
	}

	/**
	 * 获取所有的运费模版
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShippingInfoBean> getShippingInfoBeans() throws Exception {
		return sellerService.getShippingInfoBeans(new ShippingInfoBean());
	}
	
	/**
	 * 获得所有物流方式
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ShippingTemplateVo> getShippingInfos() throws Exception {
		List<ShippingTemplateVo> shippings = new ArrayList<ShippingTemplateVo>();
		
		ShippingInfoBean shippingInfo = new ShippingInfoBean();
		List<ShippingInfoBean> shippingBeans = sellerService.getShippingInfoBeans(shippingInfo);
		if (shippingBeans != null && shippingBeans.size()>0) {
			for (ShippingInfoBean shippingBean : shippingBeans) {
				ShippingTemplateVo shippingVo = new ShippingTemplateVo();
				shippingVo.setShippingInfoBean(shippingBean);
				ShippingDetailBean shippingDetail = new ShippingDetailBean();
				shippingDetail.setShippingInfoId(shippingBean.getShippingInfoId());
				List<ShippingTemplateDetailVo> details = sellerService.getShippingTemplateDetails(shippingDetail);
				shippingVo.setTempDetails(details);
				shippings.add(shippingVo);
			}
		}
		return shippings;
	}
	
	/**
	 * 增加物流方式
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean addShippingInfo(ShippingInfoBean shippingInfo) throws Exception {
		sellerService.insertShippingInfoBean(shippingInfo);
		long infoId = shippingInfo.getShippingInfoId();
		List<ShippingDetailBean> details = shippingInfo.getDetails();
		for (ShippingDetailBean detail : details) {
			detail.setShippingInfoId(infoId);
			sellerService.insertShippingDetailBean(detail);
			long detailId = detail.getDetailId();
			detail.setDetailId(detailId);
			List<ShippingOptionBean> options = detail.getOptions();
			if (options != null) {
				for (ShippingOptionBean option : options) {
					option.setDetailId(detailId);
					sellerService.insertShippingOptionBean(option);
				}
			}
		}
		return shippingInfo;
	}
	
	/**
	 * 获取单个物流模版
	 * 
	 * @param shippingInfoId
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean getShippingInfo(long shippingInfoId) throws Exception {
		ShippingInfoBean shippingInfo = new ShippingInfoBean();
		shippingInfo.setShippingInfoId(shippingInfoId);
		shippingInfo = sellerService.getShippingInfoBean(shippingInfo);
		if (shippingInfo == null) {
			return null;
		}
		ShippingDetailBean detail = new ShippingDetailBean();
		detail.setShippingInfoId(shippingInfoId);
		List<ShippingDetailBean> details = sellerService.getShippingDetailBeans(detail);
		for (ShippingDetailBean d : details) {
			if ("custom".equals(d.getShippingType())) {
				ShippingOptionBean option = new ShippingOptionBean();
				option.setDetailId(d.getDetailId());
				List<ShippingOptionBean> options = sellerService.getShippingOptionBeans(option);
				d.setOptions(options);
			}
		}
		shippingInfo.setDetails(details);
		return shippingInfo;
	}
	
	/**
	 * 删除物流模版
	 * 
	 * @param shippingInfoIds
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingInfos(long[] shippingInfoIds) throws Exception {
		int i = 0;
		for (long infoId : shippingInfoIds) {
			ShippingDetailBean detail = new ShippingDetailBean();
			detail.setShippingInfoId(infoId);
			List<ShippingDetailBean> details = sellerService.getShippingDetailBeans(detail);
			if (details != null && details.size()>0) {
				for (ShippingDetailBean d : details) {
					ShippingOptionBean option = new ShippingOptionBean();
					option.setDetailId(d.getDetailId());
					sellerService.deleteShippingOptionBean(option);
				}
			}
			sellerService.deleteShippingDetailBean(detail);
			ShippingInfoBean info = new ShippingInfoBean();
			info.setShippingInfoId(infoId);
			sellerService.deleteShippingInfoBean(info);
			i++;
		}
		return i;
	}
	
	/**
	 * 更新物流方式
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean updateShippingInfo(ShippingInfoBean shippingInfo) throws Exception {
		sellerService.updateShippingInfoBean(shippingInfo);
		ShippingDetailBean d = new ShippingDetailBean();
		d.setShippingInfoId(shippingInfo.getShippingInfoId());
		List<ShippingDetailBean> details = sellerService.getShippingDetailBeans(d);
		for (ShippingDetailBean detail : details) {
			if ("custom".equals(detail.getShippingType())) {
				ShippingOptionBean opt = new ShippingOptionBean();
				opt.setDetailId(detail.getDetailId());
				sellerService.deleteShippingOptionBean(opt);
			}
		}
		sellerService.deleteShippingDetailBean(d);

		details = shippingInfo.getDetails();
		for (ShippingDetailBean detail : details) {
			detail.setShippingInfoId(shippingInfo.getShippingInfoId());
			sellerService.insertShippingDetailBean(detail);
			List<ShippingOptionBean> options = detail.getOptions();
			if (options != null) {
				for (ShippingOptionBean opt : options) {
					opt.setDetailId(detail.getDetailId());
					sellerService.insertShippingOptionBean(opt);
				}
			}
		}
		return shippingInfo;
	}
	
	/**
	 * 获取物流明细
	 * 
	 * @param weight 重量，单位kg
	 * @param length 长，单位cm
	 * @param width 宽，单位cm
	 * @param height 高，单位cm
	 * @return
	 * @throws Exception
	 */
	public List<ShippingDetailVo> getShippingDetails(double weight, int length, int width, int height, String country) throws Exception {
		long volume = (long)length * (long)width * (long)height;
		List<ShippingInfoBean> infos = getShippingInfoBeans();
		if (infos != null && infos.size()>0) {
			List<ShippingCostBean> shippingCosts = dictService.getShippingCostBeans(new ShippingCostBean());
			ShippingDetailBean detail = new ShippingDetailBean();
			detail.setShippingInfoId(infos.get(0).getShippingInfoId());
			List<ShippingDetailBean> details = sellerService.getShippingDetailBeans(detail);
			List<ShippingDetailVo> detailVos = new ArrayList<ShippingDetailVo>();
			for (ShippingDetailBean detailBean : details) {
				List<ShippingOptionBean> options = null;
				if ("custom".equals(detailBean.getShippingType())) {
					ShippingOptionBean opt = new ShippingOptionBean();
					opt.setDetailId(detailBean.getDetailId());
					options = sellerService.getShippingOptionBeans(opt);
				}
				try {
					ShippingDetailVo detailVo = new ShippingDetailVo(weight,
							volume, country, detailBean, options, shippingCosts);
					if (detailVo.getShippingAmount() >= 0) {
						detailVos.add(detailVo);
					}
				} catch (Exception e) {
				}
			}
			return detailVos;
		}
		return null;
	}
}
