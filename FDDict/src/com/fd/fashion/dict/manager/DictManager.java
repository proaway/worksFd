package com.fd.fashion.dict.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.dict.bean.AttributeBean;
import com.fd.fashion.dict.bean.CatAttrBean;
import com.fd.fashion.dict.bean.CatConfigBean;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.bean.OrderStatusBean;
import com.fd.fashion.dict.bean.PackageUnitBean;
import com.fd.fashion.dict.bean.RegionBean;
import com.fd.fashion.dict.bean.ShippingCostBean;
import com.fd.fashion.dict.service.IDictService;
import com.fd.fashion.dict.vo.CatAttrNode;
import com.fd.fashion.dict.vo.CatAttrVo;
import com.fd.fashion.dict.vo.CatConfNode;
import com.fd.fashion.dict.vo.CatConfigVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;

/**
 * 字典数据操作实现类
 * 
 * @author Longli
 *
 */
@Transactional(rollbackFor=Exception.class)
@Component
public class DictManager implements IDictManager {
	@Autowired
	@Qualifier("dictService")
	IDictService dictService;
	
	/**
	 * 获取国家信息
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getCountries() throws Exception{
		return dictService.getRegionBeans(new RegionBean());
	}
	
	/**
	 * 根据查询条件查询所有目录列表
	 * @return
	 * @throws Exception
	 * 所用sql为select * from tc_category t where t.CAT_NAME_CN LIKE '%上衣%'
	 */
	public CategoryBean getCategory(String catId) throws Exception{
		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setCatId(catId);
		List<CategoryBean> list = dictService.getCategoryBeans(categoryBean);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据查询条件查询所有目录列表
	 * @return
	 * @throws Exception
	 * 所用sql为select * from tc_category t where t.CAT_NAME_CN LIKE '%上衣%'
	 */
	public List<CategoryBean> searchCategories(String condition) throws Exception{
		
		return dictService.searchCategories(condition);
	}
	
	/**
	 * 获取指定分类ID的所有下级分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getChildrenCategories(String catId) throws Exception {
		CategoryBean cat = new CategoryBean();
		if (StringUtils.isEmpty(catId)) {
			cat.setCatLevel(1);
		} else {
			cat.setParentId(catId);
		}
		List<CategoryBean> categories = dictService.getCategoryBeans(cat);
		return categories;
		
	}
	
	/**将List集合转成map对象
	 * @return
	 * @throws Exception
	 */
	public Map<String,CategoryBean> getCategoryById() throws Exception{
		List<CategoryBean> list = dictService.getCategoryBeans(new CategoryBean());
		Map<String,CategoryBean> map = new HashMap<String,CategoryBean>();
		if(null == list){
			return null;
		}
		for(CategoryBean category :list){
			map.put(category.getCatId(), category);
		}
		return map;
	}
	
	/**将所查到的产品所在CAT_ID所属父类连成字符串
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> changeCategoryNameString(List<CategoryBean> list) throws Exception{
		if(null == list){
			return null;
		}
		for(CategoryBean category :list){
			List<CategoryBean> cats = getParentsCategories(category.getCatId());
			String str = "";
			for (CategoryBean cat : cats) {
				str += cat.getCatNameCn()+"("+cat.getCatName()+") >> ";
			}
			if (str.endsWith(">>")) {
				str = str.substring(0, str.length()-2);
			}
			category.setCatNameCn(str);
		}
		return list;
	}

	/**
	 * 获取分类全部普通属性节点
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CatAttrNode> getCatAttributeNodes(String catId) throws Exception {
		CatAttrBean cond = new CatAttrBean();
		cond.setCatId(catId);
		List<CatAttrBean> catAttrs = dictService.getCatAttrBeans(cond);
		if (catAttrs == null || catAttrs.size()==0) {
			return null;
		}
		List<CatAttrNode> queue = new ArrayList<CatAttrNode>();
		List<CatAttrNode> nodes = new ArrayList<CatAttrNode>();
		
		Map<Long, List<CatAttrNode>> nodeMap = new HashMap<Long, List<CatAttrNode>>();
		
		// 属性树root
		for (CatAttrBean catAttr : catAttrs) {
			CatAttrNode node = new CatAttrNode();
			CatAttrVo catAttrVo = new CatAttrVo();
			long attrId = catAttr.getAttrId();
			AttributeBean attr = new AttributeBean();
			attr.setAttrId(attrId);
			List<AttributeBean> attrs = dictService.getAttributeBeans(attr);
			if (attrs != null && attrs.size()>0) {
				attr = attrs.get(0);
			}
			catAttrVo.setId(attr.getAttrId());
			catAttrVo.setNoteType(attr.getNoteType());
			catAttrVo.setValue(attr.getValue());
			catAttrVo.setValueCn(attr.getValueCn());
			catAttrVo.setShowStyle(catAttr.getShowStyle());
			catAttrVo.setShowType(catAttr.getShowType());
			node.setAttr(catAttrVo);
			if (attr.getParentId() == null) { // 根节点
				nodes.add(node);
				queue.add(node);
			} else {
				List<CatAttrNode> list = nodeMap.get(attr.getParentId());
				if (list == null) {
					list = new ArrayList<CatAttrNode>();
					nodeMap.put(attr.getParentId(), list);
				}
				list.add(node);
			}
		}
		
		// 属性树节点
		while (queue.size()>0) {
			CatAttrNode node = queue.remove(0);
			Long parentId = node.getAttr().getId();
			List<CatAttrNode> list = nodeMap.get(parentId);
			if (list != null) {
				node.setNodes(list);
				queue.addAll(list);
			}
		}
		
		return nodes;
	}
	
	/**
	 * 获取分类全部配置属性节点
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CatConfNode> getCatConfigNodes(String catId) throws Exception {
		CatConfigBean cond = new CatConfigBean();
		cond.setCatId(catId);
		List<CatConfigBean> catConfs = dictService.getCatConfigBeans(cond);
		if (catConfs == null || catConfs.size()==0) {
			return null;
		}
		List<CatConfNode> queue = new ArrayList<CatConfNode>();
		List<CatConfNode> nodes = new ArrayList<CatConfNode>();
		
		Map<Long, List<CatConfNode>> nodeMap = new HashMap<Long, List<CatConfNode>>();
		
		// 属性树root
		for (CatConfigBean catConf : catConfs) {
			CatConfNode node = new CatConfNode();
			CatConfigVo catConfVo = new CatConfigVo();
			long attrId = catConf.getAttrId();
			AttributeBean attr = new AttributeBean();
			attr.setAttrId(attrId);
			List<AttributeBean> attrs = dictService.getAttributeBeans(attr);
			if (attrs != null && attrs.size()>0) {
				attr = attrs.get(0);
			}
			catConfVo.setId(attr.getAttrId());
			catConfVo.setNoteType(attr.getNoteType());
			catConfVo.setValue(attr.getValue());
			catConfVo.setValueCn(attr.getValueCn());
			catConfVo.setAllowCustom(catConf.getAllowCustom());
			catConfVo.setShowStyle(catConf.getShowStyle());
			catConfVo.setShowType(catConf.getShowType());
			node.setConfig(catConfVo);
			if (attr.getParentId() == null) { // 根节点
				nodes.add(node);
				queue.add(node);
			} else {
				List<CatConfNode> list = nodeMap.get(attr.getParentId());
				if (list == null) {
					list = new ArrayList<CatConfNode>();
					nodeMap.put(attr.getParentId(), list);
				}
				list.add(node);
			}
		}
		
		// 属性树节点
		while (queue.size()>0) {
			CatConfNode node = queue.remove(0);
			Long parentId = node.getConfig().getId();
			List<CatConfNode> list = nodeMap.get(parentId);
			if (list != null) {
				node.setNodes(list);
				queue.addAll(list);
			}
		}
		// 主配置属性放到第一位
		if (nodes.size()>1) {
			CatConfNode first = null;
			for (CatConfNode node : nodes) {
				if (node.getConfig().getAllowCustom()) {
					first = node;
					break;
				}
			}
			if (first != null) {
				nodes.remove(first);
				nodes.add(0, first);
			}
		}
		
		return nodes;
	}
	
	/**
	 * 获取包装单位
	 * 
	 * @param tcPackageUnit
	 * @return
	 * @throws Exception
	 */
	public List<PackageUnitBean> getPackageUnitBeans(PackageUnitBean tcPackageUnit) throws Exception {
		return dictService.getPackageUnitBeans(tcPackageUnit);
	}
	
	/**
	 * 增加属性
	 * 
	 * @param attribute
	 * @throws Exception
	 */
	public void addAttribute(AttributeBean attribute) throws Exception {
		AttributeBean attr = new AttributeBean();
		attr.setAttrId(attribute.getAttrId());
		List<AttributeBean> list = dictService.getAttributeBeans(attr);
		if (list == null || list.size()==0) {
			dictService.insertAttributeBean(attribute);
		}
	}
	
	/**
	 * 增加分类配置属性
	 * 
	 * @param catConfig
	 * @throws Exception
	 */
	public void addCatConfig(CatConfigBean catConfig) throws Exception {
		CatConfigBean conf = new CatConfigBean();
		conf.setAttrId(catConfig.getAttrId());
		conf.setCatId(catConfig.getCatId());
		List<CatConfigBean> list = dictService.getCatConfigBeans(conf);
		if (list == null || list.size()==0) {
			dictService.insertCatConfigBean(catConfig);
		}
	}
	
	/**
	 * 增加分类普通属性
	 * 
	 * @param catAttr
	 * @throws Exception
	 */
	public void addCatAttr(CatAttrBean catAttr) throws Exception {
		CatAttrBean catatt = new CatAttrBean();
		catatt.setAttrId(catAttr.getAttrId());
		catatt.setCatId(catAttr.getCatId());
		List<CatAttrBean> list = dictService.getCatAttrBeans(catatt);
		if (list == null || list.size()==0) {
			dictService.insertCatAttrBean(catAttr);
		}
	}
	
	/**
	 * 根据来源名称获取分类ID
	 * 
	 * @param srcId
	 * @return
	 * @throws Exception
	 */
	public String getCatIdbySrcId(String srcId) throws Exception {
		return dictService.getCatIdbySrcId(srcId);
	}
	
	/**
	 * 获取指定物流的运费设置
	 * 
	 * @param shippingCompany
	 * @return
	 * @throws Exception
	 */
	public List<ShippingCostBean> getShippingCosts(String shippingCompany) throws Exception {
		ShippingCostBean shippingCost = new ShippingCostBean();
		shippingCost.setShippingCompany(shippingCompany);
		List<ShippingCostBean> shippings = dictService.getShippingCostBeans(shippingCost);
		return shippings;
	}
	
	/**
	 * 根据国家缩写获取国家
	 * 
	 * @param shortName
	 * @return
	 * @throws Exception
	 */
	public RegionBean getCountry(String shortName) throws Exception {
		RegionBean region = new RegionBean();
		region.setShortName(shortName);
		List<RegionBean> list = dictService.getRegionBeans(region);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取订单状态字典对象
	 * 
	 * @param statusCode
	 * @return
	 * @throws Exception
	 */
	public OrderStatusBean getOrderStatus(String statusCode) throws Exception {
		return dictService.getOrderStatusBean(statusCode);
	}

	/**
	 * 获取所有状态字典 S
	 */
	public List<OrderStatusBean> getAllOrderStatus() throws Exception {
		return dictService.getOrderStatusBeans(null);
	}
	
	/**
	 * 获取父级分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CategoryBean> getParentsCategories(String catId) throws Exception {
		List<CategoryBean> categories = new ArrayList<CategoryBean>();
		int len = 3;
		while(catId.length() >= len) {
			CategoryBean cat = new CategoryBean();
			cat.setCatId(catId.substring(0, len));
			cat = dictService.getCategoryBean(cat);
			if (cat != null) {
				categories.add(cat);
			}
			len += 3;
		}
		return categories;
	}
	
	/**
	 * 根据国家id获取国家
	 * 
	 * @param shortName
	 * @return
	 * @throws Exception
	 */
	public RegionBean getRegionByRegionId(String regionId) throws Exception {
		if (StringUtils.isEmpty(regionId)) {
			return null;
		}
		RegionBean region = new RegionBean();
		region.setRegionId(regionId);
		return dictService.getRegionBean(region);
	}
	
	/**
	 * 获取指定州内的国家
	 * 
	 * @param shortName
	 * @return
	 * @throws Exception
	 */
	public List<RegionBean> getRegionByContinentId(int continentId) throws Exception{
		RegionBean rg = new RegionBean();
		rg.setContinent(continentId);
		return dictService.getRegionBeans(rg);
	}
	
	/**
	 * 获取下级分类，返回map
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public Map<String, CategoryBean> getChildrenCategoriesMap(String catId) throws Exception {
		List<CategoryBean> children = getChildrenCategories(catId);
		if (children == null || children.size() == 0) {
			return null;
		}
		Map<String, CategoryBean> map = new HashMap<String, CategoryBean>();
		for (CategoryBean c : children) {
			map.put(c.getCatId(), c);
		}
		return map;
	}
}
