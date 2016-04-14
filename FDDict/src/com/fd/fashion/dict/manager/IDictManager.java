package com.fd.fashion.dict.manager;

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
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatConfNode;

/**
 * 字典数据操作接口类
 * 
 * @author Longli
 *
 */
public interface IDictManager {
	/**
	 * 获取国家列表信息
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getCountries() throws Exception;
	
	/**
	 * 根据catId获取对应的目录
	 * @return
	 * @throws Exception
	 */
	public CategoryBean getCategory(String catId) throws Exception;
	
	/**
	 * 根据查询条件获取对应的目录
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> searchCategories(String condition) throws Exception;
	
	/**
	 * 获取指定分类ID的所有下级分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getChildrenCategories(String catId) throws Exception;
	
	/**将某个产品的全目录保存成字符串
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> changeCategoryNameString(List<CategoryBean> list) throws Exception;
	
	/**
	 * 获取分类全部普通属性节点
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CatAttrNode> getCatAttributeNodes(String catId) throws Exception;
	
	/**
	 * 获取分类全部配置属性节点
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CatConfNode> getCatConfigNodes(String catId) throws Exception;
	
	/**
	 * 获取包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public List<PackageUnitBean> getPackageUnitBeans(PackageUnitBean tcPackageUnit) throws Exception ;
	
	/**
	 * 增加属性
	 * 
	 * @param attribute
	 * @throws Exception
	 */
	public void addAttribute(AttributeBean attribute) throws Exception;
	
	/**
	 * 增加分类配置属性
	 * 
	 * @param catConfig
	 * @throws Exception
	 */
	public void addCatConfig(CatConfigBean catConfig) throws Exception;
	
	/**
	 * 增加分类普通属性
	 * 
	 * @param catAttr
	 * @throws Exception
	 */
	public void addCatAttr(CatAttrBean catAttr) throws Exception;
	
	/**
	 * 根据来源名称获取分类ID
	 * 
	 * @param srcId
	 * @return
	 * @throws Exception
	 */
	public String getCatIdbySrcId(String srcId) throws Exception;
	
	/**
	 * 获取指定物流的运费设置
	 * 
	 * @param shippingCompany
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCostBean> getShippingCosts(String shippingCompany) throws Exception;
	
	/**
	 * 根据国家缩写获取国家
	 * 
	 * @param shortName
	 * @return
	 * @throws Exception
	 */
	public RegionBean getCountry(String shortName) throws Exception;
	
	/**
	 * 获取订单状态字典
	 * 
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	public OrderStatusBean getOrderStatus(String statusCode) throws Exception;
	
	/**获取所有状态字典
	 * @return
	 * @throws Exception
	 */
	public List<OrderStatusBean> getAllOrderStatus() throws Exception;
	
	/**
	 * 获取父级分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getParentsCategories(String catId) throws Exception;
	
	/**
	 * 根据国家id获取国家
	 * 
	 * @param shortName
	 * @return
	 * @throws Exception
	 */
	public RegionBean getRegionByRegionId(String regionId) throws Exception;
	
	/**
	 * 获取指定州内的国家
	 * 
	 * @param shortName
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getRegionByContinentId(int continentId) throws Exception;
	
	/**
	 * 获取下级分类，返回map
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public Map<String, CategoryBean> getChildrenCategoriesMap(String catId) throws Exception;
}
