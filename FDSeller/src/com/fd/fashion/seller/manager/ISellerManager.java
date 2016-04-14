package com.fd.fashion.seller.manager;

import java.util.List;
import java.util.Map;

import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.RolePrivsBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.vo.UserSearchVo;
import com.fd.util.PageInfo;

public interface ISellerManager {

	/**
	 * 获取自定义分类的下级分类
	 * 
	 * @param catId
	 * @return
	 */
	public List<CustomCategoryBean> getCustomChildrenCats(String catId) throws Exception;
	
	/**
	 * 获取自定义分类的完整分类路径
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getParentsCats(String catId) throws Exception;
	
	/**
	 * 获取自定义分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean getCustomCategory(String catId) throws Exception;
	
	/**
	 * 获取
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getAllCustomCategories() throws Exception;
	
	/**
	 * 导入系统分类到自定义分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean importSysCategories(String operator) throws Exception;
	
	/**
	 * 添加分类
	 * 
	 * @param cat
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean addCustomCategory(CustomCategoryBean cat) throws Exception;
	
	/**
	 * 修改分类
	 * 
	 * @param cat
	 * @return
	 * @throws Exception
	 */
	public int updateCustomCategory(CustomCategoryBean cat) throws Exception;
	
	/**
	 * 删除分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomCategory(String catId) throws Exception;
	
	/**
	 * 获取分类数量
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int getAllCustomCategoryCount() throws Exception;
	/**
	 * 登录方法
	 * 
	 * @param loginName
	 * @param psw
	 * @return 0-登录成功，1-用户不存在，2-密码错误
	 * @throws Exception
	 */
	public UsersBean login(String loginName, String psw) throws Exception;
	
	/**
	 * 修改密码
	 * 
	 * @param loginName
	 * @param psw
	 * @param oldpsw
	 * @return
	 * @throws Exception
	 */
	public boolean repassword(String loginName, String psw, String oldpsw) throws Exception;
	
	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateUser(UsersBean user) throws Exception;
	
	/**
	 * 获取用户
	 * 
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public UsersBean getUser(String loginName) throws Exception;
	
	/**
	 * 获取用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UsersBean getUser(long userId) throws Exception;
	
	/**
	 * 获取用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsers(UsersBean user) throws Exception;
	
	/**
	 * 获取用户列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsers(UserSearchVo user,PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取所有子菜单，当modulesId=-1，得到一级菜单
	 * 
	 * @param modulesId
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getSubModules(long modulesId) throws Exception;
	
	/**
	 * 获取所有菜单
	 * 
	 * @param modulesId
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getAllModules() throws Exception;
	
	/**
	 * 获取角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRoles(RolesBean role) throws Exception;
	
	/**
	 * 获取角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRoles(RolesBean role, PageInfo pageInfo) throws Exception;
	
	/**
	 * 获取角色
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public RolesBean getRoles(long roleId) throws Exception;
	
	/**
	 * 获取角色权限设置
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RolePrivsBean> getRolePrivs(long roleId) throws Exception;
	
	/**
	 * 获取角色权限设置
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getRoleModules(long roleId) throws Exception;
	
	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public RolesBean addRoles(RolesBean role) throws Exception;
	
	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int updateRoles(RolesBean role) throws Exception;
	
	/**
	 * 启用、禁用角色
	 * 
	 * @param roleIds
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int vaildRoles(long[] roleIds, int status) throws Exception;
	
	/**
	 * 获取部门列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentBean> getDepartments() throws Exception;
	
	/**
	 * 增加用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public UsersBean addUser(UsersBean user) throws Exception;
	
	/**
	 * 获取部门
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Long, DepartmentBean> getDepartmentsMap() throws Exception;
	
	/**
	 * 获取角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<Long, RolesBean> getRolesMap() throws Exception;
	
	/**
	 * 增加部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public DepartmentBean addDepartment(DepartmentBean department) throws Exception;
	
	/**通过禁用角色禁用所有为此角色的用户*/
	public int updateUsers(List<UsersBean> users) throws Exception;
}
