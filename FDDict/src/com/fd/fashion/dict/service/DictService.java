package com.fd.fashion.dict.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fd.dao.IBaseDao;
import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.dict.bean.CatAttrBean;
import com.fd.fashion.dict.bean.CatConfigBean;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.dict.bean.PackageUnitBean;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.bean.ShippingCostBean;
import com.fd.fashion.dict.bean.SizeAttrBean;
import com.fd.fashion.dict.bean.SizeCatBean;
import com.fd.fashion.dict.bean.SizeColattrBean;
import com.fd.fashion.dict.bean.SizeRowattrBean;
import com.fd.util.PageInfo;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughAssignCache;
import com.google.code.ssm.api.ReadThroughSingleCache;

/**
 * 字典Service 实现类
 * 
 * @author Longli
 *
 */
@Component
@Controller
@SuppressWarnings("unchecked")
public class DictService implements IDictService {
	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public AttributeBean insertAttributeBean(AttributeBean tcAttribute)
			throws Exception {
		dao.insertObj("insertAttributeBean", tcAttribute);
		return tcAttribute;
	}

	/**
	 * 修改属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public int updateAttributeBean(AttributeBean tcAttribute) throws Exception {
		return dao.updateObj("updateAttributeBean", tcAttribute);
	}

	/**
	 * 获取属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getAttributeBean", expiration = 3600)
	public AttributeBean getAttributeBean(@ParameterValueKeyProvider AttributeBean tcAttribute)
			throws Exception {
		return (AttributeBean) dao.getAsObject("getAttributeBean", tcAttribute);
	}
	
	/**
	 * 获取属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getAttributeBeans", expiration = 3600)
	public List<AttributeBean> getAttributeBeans(@ParameterValueKeyProvider AttributeBean tcAttribute)
	throws Exception {
		return dao.getAsList("getAttributeBean", tcAttribute);
	}

	/**
	 * 获取属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public List<AttributeBean> getAttributeBeans(AttributeBean tcAttribute,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getAttributeBeanCount",
				tcAttribute);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getAttributeBean", tcAttribute, pageInfo);
		}
		return null;
	}

	/**
	 * 删除属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public int deleteAttributeBean(AttributeBean tcAttribute) throws Exception {
		return dao.deleteObj("deleteAttributeBean", tcAttribute);
	}


	/**
	 * 增加分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public CatAttrBean insertCatAttrBean(CatAttrBean tcCaattr) throws Exception {
		dao.insertObj("insertCatAttrBean", tcCaattr);
		return tcCaattr;
	}

	/**
	 * 修改分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public int updateCatAttrBean(CatAttrBean tcCaattr) throws Exception {
		return dao.updateObj("updateCatAttrBean", tcCaattr);
	}

	/**
	 * 获取分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCatAttrBeans", expiration = 3600)
	public List<CatAttrBean> getCatAttrBeans(@ParameterValueKeyProvider CatAttrBean tcCaattr)
			throws Exception {
		return dao.getAsList("getCatAttrBean", tcCaattr);
	}

	/**
	 * 获取分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public List<CatAttrBean> getCatAttrBeans(CatAttrBean tcCaattr,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getCatAttrBeanCount",
				tcCaattr);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCatAttrBean", tcCaattr, pageInfo);
		}
		return null;
	}

	/**
	 * 删除分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public int deleteCatAttrBean(CatAttrBean tcCaattr) throws Exception {
		return dao.deleteObj("deleteCatAttrBean", tcCaattr);
	}

	/**
	 * 增加分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public CatConfigBean insertCatConfigBean(CatConfigBean tcCaconfig)
			throws Exception {
		dao.insertObj("insertCatConfigBean", tcCaconfig);
		return tcCaconfig;
	}

	/**
	 * 修改分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public int updateCatConfigBean(CatConfigBean tcCaconfig) throws Exception {
		return dao.updateObj("updateCatConfigBean", tcCaconfig);
	}

	/**
	 * 获取分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCatConfigBean", expiration = 3600)
	public CatConfigBean getCatConfigBean(@ParameterValueKeyProvider CatConfigBean tcCaconfig)
			throws Exception {
		List<CatConfigBean> list = dao.getAsList("getCatConfigBean", tcCaconfig);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCatConfigBeans", expiration = 3600)
	public List<CatConfigBean> getCatConfigBeans(@ParameterValueKeyProvider CatConfigBean tcCaconfig)
			throws Exception {
		return dao.getAsList("getCatConfigBean", tcCaconfig);
	}

	/**
	 * 获取分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public List<CatConfigBean> getCatConfigBeans(CatConfigBean tcCaconfig,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getCatConfigBeanCount",
				tcCaconfig);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCatConfigBean", tcCaconfig, pageInfo);
		}
		return null;
	}

	/**
	 * 删除分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public int deleteCatConfigBean(CatConfigBean tcCaconfig) throws Exception {
		return dao.deleteObj("deleteCatConfigBean", tcCaconfig);
	}

	/**
	 * 增加分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public CategoryBean insertCategoryBean(CategoryBean tcCategory)
			throws Exception {
		dao.insertObj("insertCategoryBean", tcCategory);
		return tcCategory;
	}

	/**
	 * 修改分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public int updateCategoryBean(CategoryBean tcCategory) throws Exception {
		return dao.updateObj("updateCategoryBean", tcCategory);
	}

	/**
	 * 获取分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCategoryBean", expiration = 3600)
	public CategoryBean getCategoryBean(@ParameterValueKeyProvider CategoryBean tcCategory)
			throws Exception {
		List<CategoryBean> list = getCategoryBeans(tcCategory);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCategoryBeans", expiration = 3600)
	public List<CategoryBean> getCategoryBeans(@ParameterValueKeyProvider CategoryBean tcCategory)
			throws Exception {
		return dao.getAsList("getCategoryBean", tcCategory);
	}

	/**
	 * 获取分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCategoryBeans", expiration = 3600)
	public List<CategoryBean> getCategoryBeans(@ParameterValueKeyProvider(order=0) CategoryBean tcCategory,
			@ParameterValueKeyProvider(order=1) PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getCategoryBeanCount",
				tcCategory);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCategoryBean", tcCategory, pageInfo);
		}
		return null;
	}

	/**
	 * 删除分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public int deleteCategoryBean(CategoryBean tcCategory) throws Exception {
		return dao.deleteObj("deleteCategoryBean", tcCategory);
	}

	/**
	 * 增加包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public PackageUnitBean insertPackageUnitBean(PackageUnitBean tcPackageUnit)
			throws Exception {
		dao.insertObj("insertPackageUnitBean", tcPackageUnit);
		return tcPackageUnit;
	}

	/**
	 * 修改包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public int updatePackageUnitBean(PackageUnitBean tcPackageUnit)
			throws Exception {
		return dao.updateObj("updatePackageUnitBean", tcPackageUnit);
	}

	/**
	 * 获取包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getPackageUnitBeans", expiration = 3600)
	public List<PackageUnitBean> getPackageUnitBeans(@ParameterValueKeyProvider
			PackageUnitBean tcPackageUnit) throws Exception {
		return dao.getAsList("getPackageUnitBean", tcPackageUnit);
	}

	/**
	 * 获取包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public List<PackageUnitBean> getPackageUnitBeans(
			PackageUnitBean tcPackageUnit, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getPackageUnitBeanCount",
				tcPackageUnit);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getPackageUnitBean", tcPackageUnit, pageInfo);
		}
		return null;
	}

	/**
	 * 删除包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public int deletePackageUnitBean(PackageUnitBean tcPackageUnit)
			throws Exception {
		return dao.deleteObj("deletePackageUnitBean", tcPackageUnit);
	}

	/**
	 * 增加地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public RegionBean insertRegionBean(RegionBean tcRegion) throws Exception {
		dao.insertObj("insertRegionBean", tcRegion);
		return tcRegion;
	}

	/**
	 * 修改地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public int updateRegionBean(RegionBean tcRegion) throws Exception {
		return dao.updateObj("updateRegionBean", tcRegion);
	}

	/**
	 * 获取地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getRegionBeans", expiration = 3600)
	public List<RegionBean> getRegionBeans(@ParameterValueKeyProvider RegionBean tcRegion)
			throws Exception {
		return dao.getAsList("getRegionBean", tcRegion);
	}

	
	
	/**
	 * 获取国家
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getCountries(String[] countryIds) throws Exception {
		List<RegionBean> countryList = new ArrayList<RegionBean>();
		for (int i=0; i<countryIds.length; i++) {
			String countryId = countryIds[i];
			RegionBean vo = this.getRegion(countryId);
			countryList.add(vo);
		}
		return countryList;
	}	
	
	
	/**
	 * 获取地区，先从MemoryCache缓存取，取不到则从数据库取
	 * 
	 * @return
	 * @throws Exception
	 */
	public RegionBean getRegion(String regionId) throws Exception {
		if (regionId == null) {
			return null;
		}
		RegionBean cond = new RegionBean();
		cond.setRegionId(regionId);
		
		RegionBean bean = getRegionBean(cond);
		return bean;
	}
	
	/**
	 * 获取地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getRegionBean", expiration = 3600)
	public RegionBean getRegionBean(@ParameterValueKeyProvider RegionBean tcRegion) throws Exception {
		return (RegionBean)dao.getAsObject("getRegionBean", tcRegion);
	}
	
	/**
	 * 获取地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getRegionBeans", expiration = 3600)
	public List<RegionBean> getRegionBeans(@ParameterValueKeyProvider(order=0) RegionBean tcRegion,
			@ParameterValueKeyProvider(order=1) PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getRegionBeanCount",
				tcRegion);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getRegionBean", tcRegion, pageInfo);
		}
		return null;
	}

	/**
	 * 删除地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public int deleteRegionBean(RegionBean tcRegion) throws Exception {
		return dao.deleteObj("deleteRegionBean", tcRegion);
	}

	/**
	 * 增加物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public ShippingCostBean insertShippingCostBean(
			ShippingCostBean tcShippingCost) throws Exception {
		dao.insertObj("insertShippingCostBean", tcShippingCost);
		return tcShippingCost;
	}

	/**
	 * 修改物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public int updateShippingCostBean(ShippingCostBean tcShippingCost)
			throws Exception {
		return dao.updateObj("updateShippingCostBean", tcShippingCost);
	}

	/**
	 * 获取物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCostBean> getShippingCostBeans(
			ShippingCostBean tcShippingCost) throws Exception {
		return dao.getAsList("getShippingCostBean", tcShippingCost);
	}

	/**
	 * 获取物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCostBean> getShippingCostBeans(
			ShippingCostBean tcShippingCost, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getShippingCostBeanCount",
				tcShippingCost);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getShippingCostBean", tcShippingCost,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingCostBean(ShippingCostBean tcShippingCost)
			throws Exception {
		return dao.deleteObj("deleteShippingCostBean", tcShippingCost);
	}

	/**
	 * 增加尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public SizeAttrBean insertSizeAttrBean(SizeAttrBean tcSizeAttr)
			throws Exception {
		dao.insertObj("insertSizeAttrBean", tcSizeAttr);
		return tcSizeAttr;
	}

	/**
	 * 修改尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public int updateSizeAttrBean(SizeAttrBean tcSizeAttr) throws Exception {
		return dao.updateObj("updateSizeAttrBean", tcSizeAttr);
	}

	/**
	 * 获取尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public List<SizeAttrBean> getSizeAttrBeans(SizeAttrBean tcSizeAttr)
			throws Exception {
		return dao.getAsList("getSizeAttrBean", tcSizeAttr);
	}

	/**
	 * 获取尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public List<SizeAttrBean> getSizeAttrBeans(SizeAttrBean tcSizeAttr,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getSizeAttrBeanCount",
				tcSizeAttr);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSizeAttrBean", tcSizeAttr, pageInfo);
		}
		return null;
	}

	/**
	 * 删除尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeAttrBean(SizeAttrBean tcSizeAttr) throws Exception {
		return dao.deleteObj("deleteSizeAttrBean", tcSizeAttr);
	}

	/**
	 * 增加尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public SizeCatBean insertSizeCatBean(SizeCatBean tcSizeCat)
			throws Exception {
		dao.insertObj("insertSizeCatBean", tcSizeCat);
		return tcSizeCat;
	}

	/**
	 * 修改尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public int updateSizeCatBean(SizeCatBean tcSizeCat) throws Exception {
		return dao.updateObj("updateSizeCatBean", tcSizeCat);
	}

	/**
	 * 获取尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public List<SizeCatBean> getSizeCatBeans(SizeCatBean tcSizeCat)
			throws Exception {
		return dao.getAsList("getSizeCatBean", tcSizeCat);
	}

	/**
	 * 获取尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public List<SizeCatBean> getSizeCatBeans(SizeCatBean tcSizeCat,
			PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getSizeCatBeanCount",
				tcSizeCat);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSizeCatBean", tcSizeCat, pageInfo);
		}
		return null;
	}

	/**
	 * 删除尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeCatBean(SizeCatBean tcSizeCat) throws Exception {
		return dao.deleteObj("deleteSizeCatBean", tcSizeCat);
	}

	/**
	 * 增加尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public SizeColattrBean insertSizeColattrBean(SizeColattrBean tcSizeColattr)
			throws Exception {
		dao.insertObj("insertSizeColattrBean", tcSizeColattr);
		return tcSizeColattr;
	}

	/**
	 * 修改尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public int updateSizeColattrBean(SizeColattrBean tcSizeColattr)
			throws Exception {
		return dao.updateObj("updateSizeColattrBean", tcSizeColattr);
	}

	/**
	 * 获取尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeColattrBean> getSizeColattrBeans(
			SizeColattrBean tcSizeColattr) throws Exception {
		return dao.getAsList("getSizeColattrBean", tcSizeColattr);
	}

	/**
	 * 获取尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeColattrBean> getSizeColattrBeans(
			SizeColattrBean tcSizeColattr, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getSizeColattrBeanCount",
				tcSizeColattr);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSizeColattrBean", tcSizeColattr, pageInfo);
		}
		return null;
	}

	/**
	 * 删除尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeColattrBean(SizeColattrBean tcSizeColattr)
			throws Exception {
		return dao.deleteObj("deleteSizeColattrBean", tcSizeColattr);
	}

	/**
	 * 增加尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public SizeRowattrBean insertSizeRowattrBean(SizeRowattrBean tcSizeRowattr)
			throws Exception {
		dao.insertObj("insertSizeRowattrBean", tcSizeRowattr);
		return tcSizeRowattr;
	}

	/**
	 * 修改尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public int updateSizeRowattrBean(SizeRowattrBean tcSizeRowattr)
			throws Exception {
		return dao.updateObj("updateSizeRowattrBean", tcSizeRowattr);
	}

	/**
	 * 获取尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeRowattrBean> getSizeRowattrBeans(
			SizeRowattrBean tcSizeRowattr) throws Exception {
		return dao.getAsList("getSizeRowattrBean", tcSizeRowattr);
	}

	/**
	 * 获取尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeRowattrBean> getSizeRowattrBeans(
			SizeRowattrBean tcSizeRowattr, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getSizeRowattrBeanCount",
				tcSizeRowattr);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSizeRowattrBean", tcSizeRowattr, pageInfo);
		}
		return null;
	}

	/**
	 * 删除尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeRowattrBean(SizeRowattrBean tcSizeRowattr)
			throws Exception {
		return dao.deleteObj("deleteSizeRowattrBean", tcSizeRowattr);
	}
	
	/**
	 * 根据来源名称获取分类ID
	 * 
	 * @param srcId
	 * @return
	 * @throws Exception
	 */
	public String getCatIdbySrcId(String srcId) throws Exception {
		return (String) dao.getAsObject("getCatIdbySrcId", srcId);
	}

	/**
	 * 获取订单状态
	 * 
	 * @param tcOrderStatus
	 * @return
	 * @throws Exception
	 */
	public OrderStatusBean getOrderStatusBean(String statusCode)
			throws Exception {
		OrderStatusBean status = new OrderStatusBean();
		status.setStatusCode(statusCode);
		return (OrderStatusBean) dao.getAsObject("getOrderStatusBean",
				status);
	}

	/**
	 * 获取订单状态
	 * 
	 * @param tcOrderStatus
	 * @return
	 * @throws Exception
	 */
	public List<OrderStatusBean> getOrderStatusBeans(
			OrderStatusBean tcOrderStatus) throws Exception {
		return dao.getAsList("getOrderStatusBean", tcOrderStatus);
	}
	
	/**
	 * 搜索分类
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> searchCategories(String condition) throws Exception {
		return dao.getAsList("searchCategories", condition);
	}
}