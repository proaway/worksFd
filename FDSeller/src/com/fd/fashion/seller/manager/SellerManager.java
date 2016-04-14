package com.fd.fashion.seller.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fd.fashion.seller.bean.CustomCategoryBean;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.RolePrivsBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.service.ISellerService;
import com.fd.fashion.seller.vo.UserSearchVo;
import com.fd.util.DESadeUtil;
import com.fd.util.PageInfo;

/**
 * @CreateDate: 2013-3-26 下午02:16:05
 * @author Longli
 * @Description: TODO
 * 
 */
@Transactional(rollbackFor=Exception.class)
@Component
public class SellerManager implements ISellerManager {
	@Autowired
	@Qualifier("sellerService")
	ISellerService sellerService;
	
	/**
	 * 获取自定义分类的下级分类
	 * 
	 * @param catId
	 * @return
	 */
	public List<CustomCategoryBean> getCustomChildrenCats(String catId) throws Exception {
		CustomCategoryBean cat = new CustomCategoryBean();
		if (StringUtils.isEmpty(catId)) {
			cat.setCatLevel(1);
		} else {
			cat.setParentId(catId);
		}
		List<CustomCategoryBean> categories = sellerService.getCustomCategoryBeans(cat);
		return categories;
	}
	
	/**
	 * 获取自定义分类的完整分类路径
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getParentsCats(String catId) throws Exception {
		if (StringUtils.isEmpty(catId)) {
			return new ArrayList<CustomCategoryBean>();
		}
		List<CustomCategoryBean> categories = new ArrayList<CustomCategoryBean>();
		int len = 3;
		while(catId.length() >= len) {
			CustomCategoryBean cat = new CustomCategoryBean();
			cat.setCatId(catId.substring(0, len));
			cat = sellerService.getCustomCategoryBean(cat);
			if (cat != null) {
				categories.add(cat);
			}
			len += 3;
		}
		return categories;
	}
	
	/**
	 * 获取自定义分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean getCustomCategory(String catId) throws Exception {
		CustomCategoryBean cat = new CustomCategoryBean();
		cat.setCatId(catId);
		cat = sellerService.getCustomCategoryBean(cat);
		return cat;
	}
	
	/**
	 * 获取
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CustomCategoryBean> getAllCustomCategories() throws Exception {
		return sellerService.getCustomCategoryBeans(new CustomCategoryBean());
	}
	
	/**
	 * 导入系统分类到自定义分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean importSysCategories(String operator) throws Exception {
		return sellerService.importSysCategories(operator);
	}
	
	/**
	 * 添加分类
	 * 
	 * @param cat
	 * @return
	 * @throws Exception
	 */
	public CustomCategoryBean addCustomCategory(CustomCategoryBean cat) throws Exception {
		String catId = cat.getCatId();
		cat.setCatLevel(catId.length()/3);
		cat.setIsleaf(1);
		
		if (cat.getCatLevel() > 1) {
			// 更新父分类的isleaf状态
			String parentId = catId.substring(0, catId.length()-3);
			CustomCategoryBean parent = new CustomCategoryBean();
			parent.setCatId(parentId);
			parent.setIsleaf(0);
			sellerService.updateCustomCategoryBean(parent);
			cat.setParentId(parentId);
		}
		return sellerService.insertCustomCategoryBean(cat);
	}
	
	/**
	 * 修改分类
	 * 
	 * @param cat
	 * @return
	 * @throws Exception
	 */
	public int updateCustomCategory(CustomCategoryBean cat) throws Exception {
		return sellerService.updateCustomCategoryBean(cat);
	}
	
	/**
	 * 删除分类
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int deleteCustomCategory(String catId) throws Exception {
		CustomCategoryBean cat = new CustomCategoryBean();
		cat.setCatId(catId);
		int i = sellerService.deleteCustomCategoryBean(cat);
		if (catId.length()>3) { // 判断是否最后一个子目录，是则将上级目录设置为叶节点
			String parentId = catId.substring(0, catId.length()-3);
			CustomCategoryBean c = new CustomCategoryBean();
			c.setParentId(parentId);
			List<CustomCategoryBean> children = sellerService.getCustomCategoryBeans(c);
			if (children == null || children.size() == 0) {
				c.setCatId(parentId);
				c.setParentId(null);
				c.setIsleaf(1);
				sellerService.updateCustomCategoryBean(c);
			}
		}
		return i;
	}
	
	/**
	 * 获取分类数量
	 * 
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	public int getAllCustomCategoryCount() throws Exception {
		return sellerService.getCustomCategoryBeansCount(new CustomCategoryBean());
	}
	/**
	 * 登录方法
	 * 
	 * @param loginName
	 * @param psw
	 * @return 
	 * @throws Exception
	 */
	public UsersBean login(String loginName, String psw) throws Exception {
		UsersBean user = new UsersBean();
		user.setLoginName(loginName);
		user.setPsw(DESadeUtil.encryptMode(psw));
		List<UsersBean> list = sellerService.getUsersBeans(user);
		if (list!=null && list.size()>0 ){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param loginName
	 * @param psw
	 * @param oldpsw
	 * @return
	 * @throws Exception
	 */
	public boolean repassword(String loginName, String psw, String oldpsw) throws Exception {
		UsersBean user = login(loginName, oldpsw);
		if (user == null) {
			return false;
		}
		user.setPsw(DESadeUtil.encryptMode(psw));
		int i = updateUser(user);
		return i>0;
	}
	
	/**
	 * 更新用户
	 * 
	 * @param user
	 * @throws Exception
	 */
	public int updateUser(UsersBean user) throws Exception {
		if (StringUtils.isNotEmpty(user.getPsw())) {
			user.setPsw(DESadeUtil.encryptMode(user.getPsw()));
		}
		return sellerService.updateUsersBean(user);
	}
	
	/**
	 * 获取用户
	 * 
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public UsersBean getUser(String loginName) throws Exception {
		if (StringUtils.isEmpty(loginName)) {
			return null;
		}
		UsersBean user = new UsersBean();
		user.setLoginName(loginName);
		List<UsersBean> list = sellerService.getUsersBeans(user);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UsersBean getUser(long userId) throws Exception {
		if (userId <= 0) {
			return null;
		}
		UsersBean user = new UsersBean();
		user.setUserId(userId);
		List<UsersBean> list = sellerService.getUsersBeans(user);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsers(UsersBean user) throws Exception {
		List<UsersBean> list = sellerService.getUsersBeans(user);
		return list;
	}
	
	/**
	 * 获取用户列表
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<UsersBean> getUsers(UserSearchVo user,PageInfo pageInfo) throws Exception {
		List<UsersBean> list = sellerService.getUsers(user, pageInfo);
		return list;
	}
	
	/**
	 * 获取所有子菜单，当modulesId=-1，得到一级菜单
	 * 
	 * @param modulesId
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getSubModules(long modulesId) throws Exception {
		ModulesBean modules = new ModulesBean();
		if (modulesId >= 0) {
			modules.setParentId(modulesId);
		}
		return sellerService.getModulesBeans(modules);
	}
	
	/**
	 * 获取所有菜单
	 * 
	 * @param modulesId
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getAllModules() throws Exception {
		return sellerService.getAllModulesBeans();
	}
	
	/**
	 * 获取角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRoles(RolesBean role) throws Exception {
		return sellerService.getRolesBeans(role);
	}
	
	/**
	 * 获取角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<RolesBean> getRoles(RolesBean role, PageInfo pageInfo) throws Exception {
		return sellerService.getRolesBeans(role, pageInfo);
	}
	
	/**
	 * 获取角色
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public RolesBean getRoles(long roleId) throws Exception {
		RolesBean role = new RolesBean();
		role.setRoleId(roleId);
		List<RolesBean> list = sellerService.getRolesBeans(role);
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取角色权限设置
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RolePrivsBean> getRolePrivs(long roleId) throws Exception {
		RolePrivsBean rolePrivs = new RolePrivsBean();
		rolePrivs.setRoleId(roleId);
		return sellerService.getRolePrivsBeans(rolePrivs);
	}
	
	/**
	 * 获取角色权限设置
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<ModulesBean> getRoleModules(long roleId) throws Exception {
		RolePrivsBean rolePrivs = new RolePrivsBean();
		rolePrivs.setRoleId(roleId);
		List<RolePrivsBean> prives = sellerService.getRolePrivsBeans(rolePrivs);
		if (prives != null && prives.size()>0) {
			List<ModulesBean> moduleses = new ArrayList<ModulesBean>();
			for (RolePrivsBean prive : prives) {
				ModulesBean m = new ModulesBean();
				m.setModulesId(prive.getModulesId());
				m = sellerService.getModulesBean(m);
				moduleses.add(m);
			}
			return moduleses;
		}
		return null;
	}
	
	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public RolesBean addRoles(RolesBean role) throws Exception {
		RolesBean r = sellerService.insertRolesBean(role);
		if (r != null) {
			Long[] modules = role.getModulesIds();
			if (modules != null && modules.length>0) {
				RolePrivsBean rp = new RolePrivsBean();
				rp.setRoleId(r.getRoleId());
				for (Long modulesId : modules) {
					rp.setModulesId(modulesId);
					sellerService.insertRolePrivsBean(rp);
				}
			}
		}
		return r;
	}
	
	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int updateRoles(RolesBean role) throws Exception {
		int i = sellerService.updateRolesBean(role);
		if (i>0) {
			Long[] modules = role.getModulesIds();
			RolePrivsBean rp = new RolePrivsBean();
			rp.setRoleId(role.getRoleId());
			sellerService.deleteRolePrivsBean(rp);
			if (modules != null && modules.length>0) {
				for (Long modulesId : modules) {
					rp.setModulesId(modulesId);
					sellerService.insertRolePrivsBean(rp);
				}
			}
		}
		return i;
	}
	
	/**
	 * 启用、禁用角色
	 * 
	 * @param roleIds
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int vaildRoles(long[] roleIds, int status) throws Exception {
		int i = 0;
		for (long roleId : roleIds) {
			RolesBean role = new RolesBean();
			role.setRoleId(roleId);
			role.setStatus(status);
			i += sellerService.updateRolesBean(role);
		}
		return i;
	}
	
	/**
	 * 获取部门列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DepartmentBean> getDepartments() throws Exception {
		return sellerService.getDepartmentBeans(null);
	}
	
	/**
	 * 增加用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public UsersBean addUser(UsersBean user) throws Exception {
		if (StringUtils.isNotEmpty(user.getPsw())) {
			user.setPsw(DESadeUtil.encryptMode(user.getPsw()));
		}
		return sellerService.insertUsersBean(user);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public Map<Long, DepartmentBean> getDepartmentsMap() throws Exception {
		List<DepartmentBean> list = sellerService.getDepartmentBeans(null);
		Map<Long, DepartmentBean> map = new HashMap<Long, DepartmentBean>();
		for (DepartmentBean d : list) {
			map.put(d.getDepartmentId(), d);
		}
		return map;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public Map<Long, RolesBean> getRolesMap() throws Exception {
		List<RolesBean> list = sellerService.getRolesBeans(null);
		Map<Long, RolesBean> map = new HashMap<Long, RolesBean>();
		for (RolesBean r : list) {
			map.put(r.getRoleId(), r);
		}
		return map;
	}
	
	/**
	 * 增加部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public DepartmentBean addDepartment(DepartmentBean department) throws Exception {
		return sellerService.insertDepartmentBean(department);
	}
	
	/**禁用用户*/
	private int updateUserByRole(UsersBean user) throws Exception{
		return sellerService.updateUserByRole(user);
	}
	
	/**通过禁用角色禁用所有为此角色的用户*/
	public int updateUsers(List<UsersBean> users) throws Exception{
		if(users==null || users.size()<1){
			return 0;
		}else{
			int count = 0;
			for (UsersBean user:users) {
				count += updateUserByRole(user);
			}
			return count;
		}
	}
}
