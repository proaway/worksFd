package com.fd.fashion.seller.service;

import java.util.List;

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


/**
 * 卖家Service接口
 * 
 * @author Longli
 *
 */
public interface ISellerService {

	/**
	 * 增加自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean insertCustomCategoryBean(
			CustomCategoryBean tcCustomCategory) throws Exception;

	/**
	 * 修改自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public int updateCustomCategoryBean(CustomCategoryBean tcCustomCategory)
			throws Exception;

	/**
	 * 获取自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean getCustomCategoryBean(
			CustomCategoryBean tcCustomCategory) throws Exception;

	/**
	 * 获取自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getCustomCategoryBeans(
			CustomCategoryBean tcCustomCategory) throws Exception;
	
	/**
	 * 获取自定义分类数量
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public int getCustomCategoryBeansCount(CustomCategoryBean tcCustomCategory)
			throws Exception;

	/**
	 * 获取自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getCustomCategoryBeans(
			CustomCategoryBean tcCustomCategory, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除自定义分类
	 * 
	 * @param tcCustomCategory
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomCategoryBean(CustomCategoryBean tcCustomCategory)
			throws Exception;

	/**
	 * 增加卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public ShippingDetailBean insertShippingDetailBean(
			ShippingDetailBean shippingDetail) throws Exception;

	/**
	 * 修改卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public int updateShippingDetailBean(ShippingDetailBean shippingDetail)
			throws Exception;

	/**
	 * 获取卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public List<ShippingDetailBean> getShippingDetailBeans(
			ShippingDetailBean shippingDetail) throws Exception;

	/**
	 * 获取卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public List<ShippingDetailBean> getShippingDetailBeans(
			ShippingDetailBean shippingDetail, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除卖家运费信息模板细节
	 * 
	 * @param shippingDetail
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingDetailBean(ShippingDetailBean shippingDetail)
			throws Exception;

	/**
	 * 增加卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean insertShippingInfoBean(ShippingInfoBean shippingInfo)
			throws Exception;

	/**
	 * 修改卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public int updateShippingInfoBean(ShippingInfoBean shippingInfo)
			throws Exception;

	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShippingInfoBean> getShippingInfoBeans(
			ShippingInfoBean shippingInfo) throws Exception;

	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public ShippingInfoBean getShippingInfoBean(
			ShippingInfoBean shippingInfo) throws Exception;

	/**
	 * 获取卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public List<ShippingInfoBean> getShippingInfoBeans(
			ShippingInfoBean shippingInfo, PageInfo pageInfo) throws Exception;

	/**
	 * 删除卖家运费信息模板（主）
	 * 
	 * @param shippingInfo
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingInfoBean(ShippingInfoBean shippingInfo)
			throws Exception;

	/**
	 * 增加卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public ShippingOptionBean insertShippingOptionBean(
			ShippingOptionBean shippingOption) throws Exception;

	/**
	 * 修改卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public int updateShippingOptionBean(ShippingOptionBean shippingOption)
			throws Exception;

	/**
	 * 获取卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public List<ShippingOptionBean> getShippingOptionBeans(
			ShippingOptionBean shippingOption) throws Exception;

	/**
	 * 获取卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public List<ShippingOptionBean> getShippingOptionBeans(
			ShippingOptionBean shippingOption, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除卖家运费信息模板细节自定义
	 * 
	 * @param shippingOption
	 * @return
	 * @throws Exception
	 */
	public int deleteShippingOptionBean(ShippingOptionBean shippingOption)
			throws Exception;

	/**
	 * 增加尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public SizeTemplateDetailBean insertSizeTemplateDetailBean(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception;

	/**
	 * 修改尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public int updateSizeTemplateDetailBean(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception;

	/**
	 * 获取尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateDetailBean> getSizeTemplateDetailBeans(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception;

	/**
	 * 获取尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateDetailBean> getSizeTemplateDetailBeans(
			SizeTemplateDetailBean sizeTemplateDetail, PageInfo pageInfo)
			throws Exception;

	/**
	 * 删除尺码模版明细
	 * 
	 * @param sizeTemplateDetail
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeTemplateDetailBean(
			SizeTemplateDetailBean sizeTemplateDetail) throws Exception;

	/**
	 * 增加尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public SizeTemplateBean insertSizeTemplateBean(SizeTemplateBean sizeTemplate)
			throws Exception;

	/**
	 * 修改尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public int updateSizeTemplateBean(SizeTemplateBean sizeTemplate)
			throws Exception;

	/**
	 * 获取尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateBean> getSizeTemplateBeans(
			SizeTemplateBean sizeTemplate) throws Exception;

	/**
	 * 获取尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public List<SizeTemplateBean> getSizeTemplateBeans(
			SizeTemplateBean sizeTemplate, PageInfo pageInfo) throws Exception;

	/**
	 * 删除尺码模版
	 * 
	 * @param sizeTemplate
	 * @return
	 * @throws Exception
	 */
	public int deleteSizeTemplateBean(SizeTemplateBean sizeTemplate)
			throws Exception;
	
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
			ShippingDetailBean shippingDetail) throws Exception;
	
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
			ShippingTemplateOptionVo shippingTemplateOptionVo) throws Exception;

	
	/**
	 * 导入系统分类到自定义分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean importSysCategories(String operator) throws Exception;
	/**
	 * 增加部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public DepartmentBean insertDepartmentBean(DepartmentBean department) throws Exception;
	
	/**
	 * 修改部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int updateDepartmentBean(DepartmentBean department) throws Exception;
	
	
	/**
	 * 获取部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public DepartmentBean getDepartmentBean(DepartmentBean department) throws Exception;
	/**
	 * 获取部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentBean> getDepartmentBeans(DepartmentBean department) throws Exception;
	
	/**
	 * 获取部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentBean> getDepartmentBeans(DepartmentBean department, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public int deleteDepartmentBean(DepartmentBean department) throws Exception;
	/**
	 * 增加菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public ModulesBean insertModulesBean(ModulesBean modules) throws Exception;
	
	/**
	 * 修改菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public int updateModulesBean(ModulesBean modules) throws Exception;
	
	
	/**
	 * 获取菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public ModulesBean getModulesBean(ModulesBean modules) throws Exception;
	
	/**
	 * 获取菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getModulesBeans(ModulesBean modules) throws Exception;
	
	/**
	 * 获取菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getAllModulesBeans() throws Exception;
	
	/**
	 * 获取菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getModulesBeans(ModulesBean modules, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除菜单
	 * 
	 * @param modules
	 * @return
	 * @throws Exception
	 */
	public int deleteModulesBean(ModulesBean modules) throws Exception;
	/**
	 * 增加角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public RolePrivsBean insertRolePrivsBean(RolePrivsBean rolePrivs) throws Exception;
	
	/**
	 * 修改角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public int updateRolePrivsBean(RolePrivsBean rolePrivs) throws Exception;
	
	
	/**
	 * 获取角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public RolePrivsBean getRolePrivsBean(RolePrivsBean rolePrivs) throws Exception;
	/**
	 * 获取角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public List<RolePrivsBean> getRolePrivsBeans(RolePrivsBean rolePrivs) throws Exception;
	
	/**
	 * 获取角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public List<RolePrivsBean> getRolePrivsBeans(RolePrivsBean rolePrivs, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除角色权限
	 * 
	 * @param rolePrivs
	 * @return
	 * @throws Exception
	 */
	public int deleteRolePrivsBean(RolePrivsBean rolePrivs) throws Exception;
	/**
	 * 增加角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public RolesBean insertRolesBean(RolesBean roles) throws Exception;
	
	/**
	 * 修改角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public int updateRolesBean(RolesBean roles) throws Exception;
	
	
	/**
	 * 获取角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public RolesBean getRolesBean(RolesBean roles) throws Exception;
	/**
	 * 获取角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRolesBeans(RolesBean roles) throws Exception;
	
	/**
	 * 获取角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRolesBeans(RolesBean roles, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除角色
	 * 
	 * @param roles
	 * @return
	 * @throws Exception
	 */
	public int deleteRolesBean(RolesBean roles) throws Exception;
	/**
	 * 增加系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public UsersBean insertUsersBean(UsersBean users) throws Exception;
	
	/**
	 * 修改系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public int updateUsersBean(UsersBean users) throws Exception;
	
	
	/**
	 * 获取系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public UsersBean getUsersBean(UsersBean users) throws Exception;
	/**
	 * 获取系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsersBeans(UsersBean users) throws Exception;
	
	/**
	 * 获取用户列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsers(UserSearchVo user,PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsersBeans(UsersBean users, PageInfo pageInfo) throws Exception;
	
	/**
	 * 删除系统用户
	 * 
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public int deleteUsersBean(UsersBean users) throws Exception;
	
	/**通过禁用角色禁用所有为此角色的用户*/
	public int updateUserByRole(UsersBean user) throws Exception;
}