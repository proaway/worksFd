package com.fd.b2c.manager.modules.screens.user;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.dict.bean.CategoryBean;
import com.fd.fashion.dict.manager.IDictManager;
import com.fd.fashion.seller.bean.DepartmentBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.bean.UsersBean;
import com.fd.fashion.seller.manager.ISellerManager;
import com.fd.fashion.seller.vo.UserSearchVo;
import com.fd.session.FdSession;
import com.fd.session.FdSessionFactory;
import com.fd.util.PageInfo;

/**
 * @author zhangling
 *
 */
public class Users extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/Layout.html");
		
		FdSession session = FdSessionFactory.getFdSession(data);
		UsersBean user = (UsersBean)session.getAttribute("KEY_LOGIN_USER");
		context.put("userBean", user);
		
		UserSearchVo cond = new UserSearchVo();
		//ParametersUtil.initParameters(data, cond);
		String userNameFlag = data.getParameters().getString("userNameFlag","");
		String departmentId = data.getParameters().getString("departmentId","");
		String roleId = data.getParameters().getString("roleId","");
		String vaild = data.getParameters().getString("vaild","");
		String adsc = data.getParameters().getString("adsc","0");
		
		/**注入查询条件*/
		cond.setUserNameFlag(userNameFlag);
		cond.setAdsc(adsc);
		
		if(userNameFlag.equals("")){
		}else{
			String userValue = data.getParameters().getString("userValue","");
			if(userValue.equals("")){
			}else{
				userValue = URLDecoder.decode(userValue, "utf-8");
				if(userNameFlag.equals("1")){ //1.表示用户名，2.表示姓名，3.表示email ，4.表示电话
					cond.setLoginName(userValue);
				}else if(userNameFlag.equals("2")){
					cond.setUserName(userValue);
				}else if(userNameFlag.equals("3")){
					cond.setEmail(userValue);
				}else{
					cond.setPhone(userValue);
				}
			}
		}
		
		if(departmentId.equals("")){
			cond.setDepartmentId(null);
		}else{
			cond.setDepartmentId(Long.parseLong(departmentId));
		}
		if(roleId.equals("")){
			cond.setRoleId(null);
		}else{
			cond.setRoleId(Long.parseLong(roleId));
		}
		if(vaild.equals("")){
			cond.setVaild(null);
		}else{
			cond.setVaild(Integer.parseInt(vaild));
		}
		
		/**分页*/
		PageInfo pageInfo = new PageInfo();
		String pageSize = data.getParameters().getString("pageSize","10");
		String pageIndex = data.getParameters().getString("pageIndex","1");
		pageInfo.setPageIndex(Integer.parseInt(pageIndex));
		pageInfo.setPageSize(Integer.parseInt(pageSize));
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		//List<UsersBean> users = sellerManager.getUsers(cond);
		List<UsersBean> users  =sellerManager.getUsers(cond,pageInfo);
		Map<Long, DepartmentBean> departments = sellerManager.getDepartmentsMap();
		Map<Long, RolesBean> roles = sellerManager.getRolesMap();
		
		IDictManager dictManager = (IDictManager) getBean("dictManager");
		Map<String, CategoryBean> industry = dictManager.getChildrenCategoriesMap(null);
		context.put("cond", cond);
		context.put("departments", departments);
		context.put("roles", roles);
		context.put("industry", industry);
		context.put("users", users);
		context.put("pageInfo", pageInfo);

	}

}
