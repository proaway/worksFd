package com.fd.fashion.seller.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fd.dao.IBaseDao;
import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.RolePrivsBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.ShippingDetailBean;
import com.fd.fashion.seller.bean.ShippingInfoBean;
import com.fd.fashion.seller.bean.ShippingOptionBean;
import com.fd.fashion.seller.bean.SizeTemplateBean;
import com.fd.fashion.seller.bean.SizeTemplateDetailBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.vo.ShippingTemplateDetailVo;
import com.fd.fashion.seller.vo.ShippingTemplateOptionVo;
import com.fd.fashion.seller.vo.UserSearchVo;
import com.fd.util.PageInfo;
import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;


/**
 * 卖家Service接口实现类
 * 
 * @author Longli
 *
 */
@Component
public class SellerService implements ISellerService {

	@Autowired
	@Qualifier("dao")
	private IBaseDao dao;

	/**
	 * 增加自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean insertCustomCategoryBean(
			CustomCategoryBean tcCustomCategory) throws Exception {
		dao.insertObj("insertCustomCategoryBean", tcCustomCategory);
		return tcCustomCategory;
	}

	/**
	 * 修改自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
    @UpdateSingleCache(namespace = "getCustomCategoryBean", expiration = 3600)
	public int updateCustomCategoryBean(@ParameterDataUpdateContent @ParameterValueKeyProvider CustomCategoryBean tcCustomCategory)
			throws Exception {
		return dao.updateObj("updateCustomCategoryBean", tcCustomCategory);
	}

	/**
	 * 获取自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	@ReadThroughSingleCache(namespace = "getCustomCategoryBean", expiration = 3600)
	public CustomCategoryBean getCustomCategoryBean(@ParameterValueKeyProvider
			CustomCategoryBean tcCustomCategory) throws Exception {
		return (CustomCategoryBean)dao.getAsObject("getCustomCategoryBean", tcCustomCategory);
	}

	/**
	 * 获取自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getCustomCategoryBeans(
			CustomCategoryBean tcCustomCategory) throws Exception {
		return dao.getAsList("getCustomCategoryBean", tcCustomCategory);
	}

	/**
	 * 获取自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getCustomCategoryBeans(
			CustomCategoryBean tcCustomCategory, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getCustomCategoryBeanCount",
				tcCustomCategory);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getCustomCategoryBean", tcCustomCategory,
					pageInfo);
		}
		return null;
	}
	
	/**
	 * 获取自定义分类数量
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public int getCustomCategoryBeansCount(CustomCategoryBean tcCustomCategory)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getCustomCategoryBeanCount",
				tcCustomCategory);
		return count == null ? 0 : count;
	}

	/**
	 * 删除自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomCategoryBean(CustomCategoryBean tcCustomCategory)
			throws Exception {
		return dao.deleteObj("deleteCustomCategoryBean", tcCustomCategory);
	}

	/**
	 * 增加卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public ShippingDetailBean insertShippingDetailBean(
			ShippingDetailBean shippingDetail) throws Exception {
		dao.insertObj("insertShippingDetailBean", shippingDetail);
		return shippingDetail;
	}

	/**
	 * 修改卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public int updateShippingDetailBean(ShippingDetailBean shippingDetail)
			throws Exception {
		return dao.updateObj("updateShippingDetailBean", shippingDetail);
	}

	/**
	 * 获取卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public List<ShippingDetailBean> getShippingDetailBeans(
			ShippingDetailBean shippingDetail) throws Exception {
		return dao.getAsList("getShippingDetailBean", shippingDetail);
	}
	
	
	/**
	 * 获取模板明细
	 * 
	 * @param shippingTemplateDetailVo
	 *            设置模板明细的shippingTemplateId属性或shippingTemplateDetailId作为查询参数<br>
	 *            当shippingTemplateDetailId有值时，取shippingTemplateDetailId为条件，<br>
	 *            否则取shippingTemplateId为条件
	 * @return 返回模板明细列表
	 * @throws Exception
	 */
	public List<ShippingTemplateDetailVo> getShippingTemplateDetails(
			ShippingDetailBean shippingDetail) throws Exception {
		if (shippingDetail == null) {
			return null;
		}
	//	ShippingDetailBean templateDetailBean = new ShippingDetailBean();
	//	PropertyUtils.copyProperties(templateDetailBean,shippingTemplateDetailVo);

		List<ShippingDetailBean> templateDetails = getShippingDetailBeans(shippingDetail);
		if (templateDetails != null) {
			List<ShippingTemplateDetailVo> templateDetailVos = new ArrayList<ShippingTemplateDetailVo>();
			for (ShippingDetailBean templateDetail : templateDetails) {
				ShippingTemplateDetailVo templateDetailVo = new ShippingTemplateDetailVo();
				PropertyUtils.copyProperties(templateDetailVo, templateDetail);
				templateDetailVos.add(templateDetailVo);
			}
			return templateDetailVos;
		}

		return null;
	}
	
	
	
	/**
	 * 获取自定义模板明细
	 * 
	 * @param shippingTemplateOptionVo
	 *            设置自定义模板明细的shippingTemplateDetailId属性或shippingTemplateOptionId作为查询参数<br>
	 *            当shippingTemplateOptionId有值时，取shippingTemplateOptionId为条件，<br>
	 *            否则取shippingTemplateDetailId为条件
	 * @return 返回自定义模板明细列表
	 * @throws Exception
	 */
	public List<ShippingTemplateOptionVo> getShippingTemplateOptions(
			ShippingTemplateOptionVo shippingTemplateOptionVo) throws Exception {
		if (shippingTemplateOptionVo == null) {
			return null;
		}

		ShippingOptionBean templateOptionBean = new ShippingOptionBean();
		PropertyUtils.copyProperties(templateOptionBean,
				shippingTemplateOptionVo);

		ShippingOptionBean shippingOption = new ShippingOptionBean();
		shippingOption.setDetailId(shippingTemplateOptionVo.getDetailId());
		List<ShippingOptionBean> templateOptions = getShippingOptionBeans(shippingOption);
		if (templateOptions != null) {
			List<ShippingTemplateOptionVo> templateOptionVos = new ArrayList<ShippingTemplateOptionVo>();
			for (ShippingOptionBean templateOption : templateOptions) {
				ShippingTemplateOptionVo templateOptionVo = new ShippingTemplateOptionVo();
				PropertyUtils.copyProperties(templateOptionVo, templateOption);
				templateOptionVos.add(templateOptionVo);
			}
			return templateOptionVos;
		}

		return null;
	}
	

	/**
	 * 获取卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public List<ShippingDetailBean> getShippingDetailBeans(
			ShippingDetailBean shippingDetail, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getShippingDetailBeanCount",
				shippingDetail);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getShippingDetailBean", shippingDetail,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingDetailBean(ShippingDetailBean shippingDetail)
			throws Exception {
		return dao.deleteObj("deleteShippingDetailBean", shippingDetail);
	}

	/**
	 * 增加卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean insertShippingInfoBean(ShippingInfoBean shippingInfo)
			throws Exception {
		dao.insertObj("insertShippingInfoBean", shippingInfo);
		return shippingInfo;
	}

	/**
	 * 修改卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public int updateShippingInfoBean(ShippingInfoBean shippingInfo)
			throws Exception {
		return dao.updateObj("updateShippingInfoBean", shippingInfo);
	}

	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShippingInfoBean> getShippingInfoBeans(
			ShippingInfoBean shippingInfo) throws Exception {
		return dao.getAsList("getShippingInfoBean", shippingInfo);
	}

	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean getShippingInfoBean(
			ShippingInfoBean shippingInfo) throws Exception {
		return (ShippingInfoBean)dao.getAsObject("getShippingInfoBean", shippingInfo);
	}

	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShippingInfoBean> getShippingInfoBeans(
			ShippingInfoBean shippingInfo, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getShippingInfoBeanCount",
				shippingInfo);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getShippingInfoBean", shippingInfo, pageInfo);
		}
		return null;
	}

	/**
	 * 删除卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingInfoBean(ShippingInfoBean shippingInfo)
			throws Exception {
		return dao.deleteObj("deleteShippingInfoBean", shippingInfo);
	}

	/**
	 * 增加卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public ShippingOptionBean insertShippingOptionBean(
			ShippingOptionBean shippingOption) throws Exception {
		dao.insertObj("insertShippingOptionBean", shippingOption);
		return shippingOption;
	}

	/**
	 * 修改卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public int updateShippingOptionBean(ShippingOptionBean shippingOption)
			throws Exception {
		return dao.updateObj("updateShippingOptionBean", shippingOption);
	}

	/**
	 * 获取卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public List<ShippingOptionBean> getShippingOptionBeans(
			ShippingOptionBean shippingOption) throws Exception {
		return dao.getAsList("getShippingOptionBean", shippingOption);
	}

	/**
	 * 获取卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public List<ShippingOptionBean> getShippingOptionBeans(
			ShippingOptionBean shippingOption, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject("getShippingOptionBeanCount",
				shippingOption);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getShippingOptionBean", shippingOption,
					pageInfo);
		}
		return null;
	}

	/**
	 * 删除卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingOptionBean(ShippingOptionBean shippingOption)
			throws Exception {
		return dao.deleteObj("deleteShippingOptionBean", shippingOption);
	}

	/**
	 * 增加尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public SizeTemplateDetailBean insertSizeTemplateDetailBean(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception {
		dao.insertObj("insertSizeTemplateDetailBean", sizeTemplateDetail);
		return sizeTemplateDetail;
	}

	/**
	 * 修改尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public int updateSizeTemplateDetailBean(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception {
		return dao
				.updateObj("updateSizeTemplateDetailBean", sizeTemplateDetail);
	}

	/**
	 * 获取尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateDetailBean> getSizeTemplateDetailBeans(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception {
		return dao.getAsList("getSizeTemplateDetailBean", sizeTemplateDetail);
	}

	/**
	 * 获取尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateDetailBean> getSizeTemplateDetailBeans(
			SizeTemplateDetailBean sizeTemplateDetail, PageInfo pageInfo)
			throws Exception {
		Integer count = (Integer) dao.getAsObject(
				"getSizeTemplateDetailBeanCount", sizeTemplateDetail);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSizeTemplateDetailBean",
					sizeTemplateDetail, pageInfo);
		}
		return null;
	}

	/**
	 * 删除尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeTemplateDetailBean(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception {
		return dao
				.deleteObj("deleteSizeTemplateDetailBean", sizeTemplateDetail);
	}

	/**
	 * 增加尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public SizeTemplateBean insertSizeTemplateBean(SizeTemplateBean sizeTemplate)
			throws Exception {
		dao.insertObj("insertSizeTemplateBean", sizeTemplate);
		return sizeTemplate;
	}

	/**
	 * 修改尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public int updateSizeTemplateBean(SizeTemplateBean sizeTemplate)
			throws Exception {
		return dao.updateObj("updateSizeTemplateBean", sizeTemplate);
	}

	/**
	 * 获取尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateBean> getSizeTemplateBeans(
			SizeTemplateBean sizeTemplate) throws Exception {
		return dao.getAsList("getSizeTemplateBean", sizeTemplate);
	}

	/**
	 * 获取尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateBean> getSizeTemplateBeans(
			SizeTemplateBean sizeTemplate, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getSizeTemplateBeanCount",
				sizeTemplate);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count > 0) {
			return dao.getAsList("getSizeTemplateBean", sizeTemplate, pageInfo);
		}
		return null;
	}

	/**
	 * 删除尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeTemplateBean(SizeTemplateBean sizeTemplate)
			throws Exception {
		return dao.deleteObj("deleteSizeTemplateBean", sizeTemplate);
	}
	
	/**
	 * 导入系统分类到自定义分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean importSysCategories(String operator) throws Exception {
		dao.deleteObj("deleteAllCustomCats", null);
		dao.insertObj("importSysCategories", operator);
		return true;
	}
	/**
	 * 增加部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public DepartmentBean insertDepartmentBean(DepartmentBean department) throws Exception {
		dao.insertObj("insertDepartmentBean", department);
		return department;
	}
	
	/**
	 * 修改部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int updateDepartmentBean(DepartmentBean department) throws Exception {
		return dao.updateObj("updateDepartmentBean", department);
	}
	
	/**
	 * 获取部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public DepartmentBean getDepartmentBean(DepartmentBean department) throws Exception {
		return (DepartmentBean)dao.getAsObject("getDepartmentBean", department);
	}
	
	/**
	 * 获取部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentBean> getDepartmentBeans(DepartmentBean department) throws Exception {
		return dao.getAsList("getDepartmentBean", department);
	}
	
	/**
	 * 获取部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentBean> getDepartmentBeans(DepartmentBean department, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getDepartmentBeanCount", department);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getDepartmentBean", department, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int deleteDepartmentBean(DepartmentBean department) throws Exception {
		return dao.deleteObj("deleteDepartmentBean", department);
	}
	/**
	 * 增加菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public ModulesBean insertModulesBean(ModulesBean modules) throws Exception {
		dao.insertObj("insertModulesBean", modules);
		return modules;
	}
	
	/**
	 * 修改菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public int updateModulesBean(ModulesBean modules) throws Exception {
		return dao.updateObj("updateModulesBean", modules);
	}
	
	/**
	 * 获取菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public ModulesBean getModulesBean(ModulesBean modules) throws Exception {
		return (ModulesBean)dao.getAsObject("getModulesBean", modules);
	}
	
	/**
	 * 获取菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getModulesBeans(ModulesBean modules) throws Exception {
		return dao.getAsList("getModulesBean", modules);
	}
	
	/**
	 * 获取菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getAllModulesBeans() throws Exception {
		return dao.getAsList("getAllModulesBean");
	}
	
	/**
	 * 获取菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getModulesBeans(ModulesBean modules, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getModulesBeanCount", modules);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getModulesBean", modules, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public int deleteModulesBean(ModulesBean modules) throws Exception {
		return dao.deleteObj("deleteModulesBean", modules);
	}
	/**
	 * 增加角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public RolePrivsBean insertRolePrivsBean(RolePrivsBean rolePrivs) throws Exception {
		dao.insertObj("insertRolePrivsBean", rolePrivs);
		return rolePrivs;
	}
	
	/**
	 * 修改角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public int updateRolePrivsBean(RolePrivsBean rolePrivs) throws Exception {
		return dao.updateObj("updateRolePrivsBean", rolePrivs);
	}
	
	/**
	 * 获取角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public RolePrivsBean getRolePrivsBean(RolePrivsBean rolePrivs) throws Exception {
		return (RolePrivsBean)dao.getAsObject("getRolePrivsBean", rolePrivs);
	}
	
	/**
	 * 获取角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public List<RolePrivsBean> getRolePrivsBeans(RolePrivsBean rolePrivs) throws Exception {
		return dao.getAsList("getRolePrivsBean", rolePrivs);
	}
	
	/**
	 * 获取角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public List<RolePrivsBean> getRolePrivsBeans(RolePrivsBean rolePrivs, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getRolePrivsBeanCount", rolePrivs);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getRolePrivsBean", rolePrivs, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public int deleteRolePrivsBean(RolePrivsBean rolePrivs) throws Exception {
		return dao.deleteObj("deleteRolePrivsBean", rolePrivs);
	}
	/**
	 * 增加角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public RolesBean insertRolesBean(RolesBean roles) throws Exception {
		dao.insertObj("insertRolesBean", roles);
		return roles;
	}
	
	/**
	 * 修改角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public int updateRolesBean(RolesBean roles) throws Exception {
		return dao.updateObj("updateRolesBean", roles);
	}
	
	/**
	 * 获取角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public RolesBean getRolesBean(RolesBean roles) throws Exception {
		return (RolesBean)dao.getAsObject("getRolesBean", roles);
	}
	
	/**
	 * 获取角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRolesBeans(RolesBean roles) throws Exception {
		return dao.getAsList("getRolesBean", roles);
	}
	
	/**
	 * 获取角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRolesBeans(RolesBean roles, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getRolesBeanCount", roles);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getRolesBean", roles, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public int deleteRolesBean(RolesBean roles) throws Exception {
		return dao.deleteObj("deleteRolesBean", roles);
	}
	/**
	 * 增加系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public UsersBean insertUsersBean(UsersBean users) throws Exception {
		dao.insertObj("insertUsersBean", users);
		return users;
	}
	
	/**
	 * 修改系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public int updateUsersBean(UsersBean users) throws Exception {
		return dao.updateObj("updateUsersBean", users);
	}
	
	/**
	 * 获取系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public UsersBean getUsersBean(UsersBean users) throws Exception {
		return (UsersBean)dao.getAsObject("getUsersBean", users);
	}
	
	/**
	 * 获取系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsersBeans(UsersBean users) throws Exception {
		return dao.getAsList("getUsersBean", users);
	}
	
	/**
	 * 获取用户列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsers(UserSearchVo user,PageInfo pageInfo) throws Exception{
		Integer count = (Integer)dao.getAsObject("getUserListCount",user);
		if(count>0){
			if(pageInfo!=null){
				pageInfo.setRecordCount(count);
			}
			return dao.getAsList("getUserList",user);
		}
		return null;
	}
	
	/**
	 * 获取系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsersBeans(UsersBean users, PageInfo pageInfo) throws Exception {
		Integer count = (Integer) dao.getAsObject("getUsersBeanCount", users);
		pageInfo.setRecordCount(count == null ? 0 : count);
		if (count != null && count>0) {
			return dao.getAsList("getUsersBean", users, pageInfo);
		}
		return null;
	}
	
	/**
	 * 删除系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public int deleteUsersBean(UsersBean users) throws Exception {
		return dao.deleteObj("deleteUsersBean", users);
	}
	
	
	/**通过禁用角色禁用所有为此角色的用户*/
	public int updateUserByRole(UsersBean user) throws Exception{
		return dao.updateObj("updateUserByRole", user);
	}
}