package com.fd.fashion.dict.service;

import java.util.List;
import java.util.Map;

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

/**
 * 字典类Service接口
 * 
 * @author Longli
 * 
 */
public interface IDictService {

	/**
	 * 增加属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public AttributeBean insertAttributeBean(AttributeBean tcAttribute)
			throws Exception;

	/**
	 * 修改属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public int updateAttributeBean(AttributeBean tcAttribute) throws Exception;

	/**
	 * 获取属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public AttributeBean getAttributeBean(AttributeBean tcAttribute)
			throws Exception;

	/**
	 * 获取属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public List<AttributeBean> getAttributeBeans(AttributeBean tcAttribute)
			throws Exception;

	/**
	 * 获取属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public List<AttributeBean> getAttributeBeans(AttributeBean tcAttribute,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除属性
	 * 
	 * @param tcAttribute
	 * @return
	 * @throws Exception
	 */
	public int deleteAttributeBean(AttributeBean tcAttribute) throws Exception;

	/**
	 * 增加分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public CatAttrBean insertCatAttrBean(CatAttrBean tcCaattr) throws Exception;

	/**
	 * 修改分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public int updateCatAttrBean(CatAttrBean tcCaattr) throws Exception;

	/**
	 * 获取分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public List<CatAttrBean> getCatAttrBeans(CatAttrBean tcCaattr)
			throws Exception;

	/**
	 * 获取分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public List<CatAttrBean> getCatAttrBeans(CatAttrBean tcCaattr,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除分类属性
	 * 
	 * @param tcCaattr
	 * @return
	 * @throws Exception
	 */
	public int deleteCatAttrBean(CatAttrBean tcCaattr) throws Exception;

	/**
	 * 增加分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public CatConfigBean insertCatConfigBean(CatConfigBean tcCaconfig)
			throws Exception;

	/**
	 * 修改分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public int updateCatConfigBean(CatConfigBean tcCaconfig) throws Exception;

	/**
	 * 获取分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public CatConfigBean getCatConfigBean(CatConfigBean tcCaconfig)
			throws Exception;

	/**
	 * 获取分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public List<CatConfigBean> getCatConfigBeans(CatConfigBean tcCaconfig)
			throws Exception;

	/**
	 * 获取分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public List<CatConfigBean> getCatConfigBeans(CatConfigBean tcCaconfig,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除分类配置
	 * 
	 * @param tcCaconfig
	 * @return
	 * @throws Exception
	 */
	public int deleteCatConfigBean(CatConfigBean tcCaconfig) throws Exception;

	/**
	 * 增加分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public CategoryBean insertCategoryBean(CategoryBean tcCategory)
			throws Exception;

	/**
	 * 修改分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public int updateCategoryBean(CategoryBean tcCategory) throws Exception;

	/**
	 * 获取分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public CategoryBean getCategoryBean(CategoryBean tcCategory)
			throws Exception;

	/**
	 * 获取分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getCategoryBeans(CategoryBean tcCategory)
			throws Exception;

	/**
	 * 获取分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getCategoryBeans(CategoryBean tcCategory,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除分类
	 * 
	 * @param tcCategory
	 * @return
	 * @throws Exception
	 */
	public int deleteCategoryBean(CategoryBean tcCategory) throws Exception;

	/**
	 * 增加包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public PackageUnitBean insertPackageUnitBean(PackageUnitBean tcPackageUnit)
			throws Exception;

	/**
	 * 修改包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public int updatePackageUnitBean(PackageUnitBean tcPackageUnit)
			throws Exception;

	/**
	 * 获取包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public List<PackageUnitBean> getPackageUnitBeans(
			PackageUnitBean tcPackageUnit) throws Exception;

	/**
	 * 获取包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public List<PackageUnitBean> getPackageUnitBeans(
			PackageUnitBean tcPackageUnit, PageInfo pageInfo) throws Exception;

	/**
	 * 删除包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public int deletePackageUnitBean(PackageUnitBean tcPackageUnit)
			throws Exception;

	/**
	 * 增加地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public RegionBean insertRegionBean(RegionBean tcRegion) throws Exception;

	/**
	 * 修改地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public int updateRegionBean(RegionBean tcRegion) throws Exception;
	
	/**
	 * 获取地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public RegionBean getRegionBean(RegionBean tcRegion) throws Exception;

	/**
	 * 获取地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getRegionBeans(RegionBean tcRegion)
			throws Exception;

	/**
	 * 获取地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getRegionBeans(RegionBean tcRegion,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除地区
	 * 
	 * @param tcRegion
	 * @return
	 * @throws Exception
	 */
	public int deleteRegionBean(RegionBean tcRegion) throws Exception;

	/**
	 * 增加物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public ShippingCostBean insertShippingCostBean(
			ShippingCostBean tcShippingCost) throws Exception;

	/**
	 * 修改物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public int updateShippingCostBean(ShippingCostBean tcShippingCost)
			throws Exception;

	/**
	 * 获取物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCostBean> getShippingCostBeans(
			ShippingCostBean tcShippingCost) throws Exception;

	/**
	 * 获取物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCostBean> getShippingCostBeans(
			ShippingCostBean tcShippingCost, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除物流费率
	 * 
	 * @param tcShippingCost
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingCostBean(ShippingCostBean tcShippingCost)
			throws Exception;

	/**
	 * 增加尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public SizeAttrBean insertSizeAttrBean(SizeAttrBean tcSizeAttr)
			throws Exception;

	/**
	 * 修改尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public int updateSizeAttrBean(SizeAttrBean tcSizeAttr) throws Exception;

	/**
	 * 获取尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public List<SizeAttrBean> getSizeAttrBeans(SizeAttrBean tcSizeAttr)
			throws Exception;

	/**
	 * 获取尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public List<SizeAttrBean> getSizeAttrBeans(SizeAttrBean tcSizeAttr,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除尺码属性（头）
	 * 
	 * @param tcSizeAttr
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeAttrBean(SizeAttrBean tcSizeAttr) throws Exception;

	/**
	 * 增加尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public SizeCatBean insertSizeCatBean(SizeCatBean tcSizeCat)
			throws Exception;

	/**
	 * 修改尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public int updateSizeCatBean(SizeCatBean tcSizeCat) throws Exception;

	/**
	 * 获取尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public List<SizeCatBean> getSizeCatBeans(SizeCatBean tcSizeCat)
			throws Exception;

	/**
	 * 获取尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public List<SizeCatBean> getSizeCatBeans(SizeCatBean tcSizeCat,
			PageInfo pageInfo) throws Exception;

	/**
	 * 删除尺码分类
	 * 
	 * @param tcSizeCat
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeCatBean(SizeCatBean tcSizeCat) throws Exception;

	/**
	 * 增加尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public SizeColattrBean insertSizeColattrBean(SizeColattrBean tcSizeColattr)
			throws Exception;

	/**
	 * 修改尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public int updateSizeColattrBean(SizeColattrBean tcSizeColattr)
			throws Exception;

	/**
	 * 获取尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeColattrBean> getSizeColattrBeans(
			SizeColattrBean tcSizeColattr) throws Exception;

	/**
	 * 获取尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeColattrBean> getSizeColattrBeans(
			SizeColattrBean tcSizeColattr, PageInfo pageInfo) throws Exception;

	/**
	 * 删除尺码列属性（列头）
	 * 
	 * @param tcSizeColattr
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeColattrBean(SizeColattrBean tcSizeColattr)
			throws Exception;

	/**
	 * 增加尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public SizeRowattrBean insertSizeRowattrBean(SizeRowattrBean tcSizeRowattr)
			throws Exception;

	/**
	 * 修改尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public int updateSizeRowattrBean(SizeRowattrBean tcSizeRowattr)
			throws Exception;

	/**
	 * 获取尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeRowattrBean> getSizeRowattrBeans(
			SizeRowattrBean tcSizeRowattr) throws Exception;

	/**
	 * 获取尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public List<SizeRowattrBean> getSizeRowattrBeans(
			SizeRowattrBean tcSizeRowattr, PageInfo pageInfo) throws Exception;

	/**
	 * 删除尺码行属性（行头）
	 * 
	 * @param tcSizeRowattr
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeRowattrBean(SizeRowattrBean tcSizeRowattr)
			throws Exception;

	/**
	 * 获取国家
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getCountries(String[] countryIds) throws Exception;

	/**
	 * 根据来源名称获取分类ID
	 * 
	 * @param srcId
	 * @return
	 * @throws Exception
	 */
	public String getCatIdbySrcId(String srcId) throws Exception;

	/**
	 * 获取订单状态
	 * 
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	public OrderStatusBean getOrderStatusBean(String statusCode)
			throws Exception;

	/**
	 * 获取订单状态
	 * 
	 * @param tcOrderStatus
	 * @return
	 * @throws Exception
	 */
	public List<OrderStatusBean> getOrderStatusBeans(
			OrderStatusBean tcOrderStatus) throws Exception;
	
	/**
	 * 搜索分类
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> searchCategories(String condition) throws Exception;
	

	
}