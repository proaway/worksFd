package com.fd.b2c.manager.modules.screens.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.turbine.util.RunData;
import org.apache.velocity.context.Context;

import com.fd.b2c.manager.modules.screens.SecureScreen;
import com.fd.fashion.seller.bean.ModulesBean;
import com.fd.fashion.seller.bean.RolePrivsBean;
import com.fd.fashion.seller.bean.RolesBean;
import com.fd.fashion.seller.manager.ISellerManager;

public class ViewRoleDetail extends SecureScreen {
	@Override
	public void doBuildTemplate(RunData data, Context context) throws Exception {
		super.doBuildTemplate(data, context);
		data.setLayoutTemplate("/NoLayout.html");
		
		ISellerManager sellerManager = (ISellerManager) getBean("sellerManager");
		List<ModulesBean> roots = sellerManager.getSubModules(-1);
		for (ModulesBean m : roots) {
			List<ModulesBean> subs = sellerManager.getSubModules(m.getModulesId());
			m.setSubModules(subs);
			if (subs != null && subs.size()>0) {
				for (ModulesBean modulesBean : subs) {
					List<ModulesBean> subsubs = sellerManager.getSubModules(modulesBean.getModulesId());
					modulesBean.setSubModules(subsubs);
				}
			}
		}
		List<ModulesBean> mbs = sellerManager.getSubModules(0);
		roots.addAll(mbs);
		context.put("rootmodules", roots);
		List<String> list = null;
		list = returnList(roots,null);
		long roleId = data.getParameters().getLong("roleId", 0);
		Map<Long, Long> roleMap = null;
		if (roleId > 0) {
			roleMap = new HashMap<Long, Long>();
			RolesBean role = sellerManager.getRoles(roleId);
			List<RolePrivsBean> rolePrivs = sellerManager.getRolePrivs(roleId);
			if (rolePrivs != null && rolePrivs.size()>0) {
				for (RolePrivsBean priv : rolePrivs) {
					roleMap.put(priv.getModulesId(), priv.getRoleId());
				}
				context.put("roleMap", roleMap);
				List<ModulesBean> modules = new ArrayList<ModulesBean>();
				for(ModulesBean modul :roots){
					if(roleMap.get(modul.getModulesId())!=null){
						if(modul.getSubModules()!=null && modul.getSubModules().size()>0){
							for(ModulesBean md :modul.getSubModules()){
								if(roleMap.get(md.getModulesId())!=null){
									if(md.getSubModules()!=null && md.getSubModules().size()>0){
										for(ModulesBean mu :md.getSubModules()){
											if(roleMap.get(mu.getModulesId())!=null){
												modules.add(mu);
											}
										}
									}
									modules.add(md);
								}
							}
						}
						modules.add(modul);
					}
				}
				if(roleMap!=null && roleMap.size()>0){
					list = returnList(modules,roleMap);
				}else{
					list = null;
				}
			}else{
				list = null;
			}
			context.put("role", role);
		}
		
		if(list!=null&&list.size()>0){
			context.put("rootRoleList", JSONArray.fromObject(list));
		}
		
		if(data.getParameters().getInt("edit", 0) == 1) {
			 list = returnList(roots,roleMap);
			 context.put("rootRoleList", JSONArray.fromObject(list));
			setTemplate(data, "user,EditRoleDetail.html");
		}
	}
	
	public List<String> returnList(List<ModulesBean> mbs,Map<Long, Long> roleMap){
		List<String> list = new ArrayList<String>();
		try {
			for (ModulesBean m : mbs) {
				String s = "";
				if(m.getParentId()==null || m.getParentId()<0){
					String name = URLDecoder.decode(m.getModulesName(), "big5");
					 s=  "{id:"+m.getModulesId()+",pId:0,name:'"+name+"',open:true";
				}else{
					String name = URLDecoder.decode(m.getModulesName(), "big5");
					s = "{id:"+m.getModulesId()+",pId:"+m.getParentId()+",name:'"+name+"',open:true";
				}
				int  count2 = 0;
				if(m.getSubModules()!=null && m.getSubModules().size()>0){
					List<ModulesBean> mods = m.getSubModules();
					for(ModulesBean mm :mods){
						String name2 =URLDecoder.decode(mm.getModulesName(), "big5");
						String str = "{id:"+mm.getModulesId()+",pId:"+mm.getParentId()+",name:'"+name2+"',open:true";
						int count3 = 0;
						if(mm.getSubModules()!=null && mm.getSubModules().size()>0){
							List<ModulesBean> modList = mm.getSubModules();
							for(ModulesBean mmm :modList){
								String name3 = URLDecoder.decode(mmm.getModulesName(), "big5");
								String strs = "{id:"+mmm.getModulesId()+",pId:"+mmm.getParentId()+",name:'"+name3+"',open:true";
								if(roleMap!=null && roleMap.get(mmm.getModulesId())!=null){
									strs = strs+",checked:true}";
									count3 += 1;
								}else{
									strs = strs+"}";
								}
								list.add(strs);
							}
							
						}
						if(mm.getSubModules()!=null && mm.getSubModules().size()>0){
							if(mm.getSubModules().size()==count3){
								str = str+",checked:true,isParent:true}";
								count2+=1;
							}else if(count3>0){
								str = str+",halfCheck:true, checked:true, isParent:true}";
								count2+=1;
							}else{
								str = str+",isParent:true}";
							}
						}else{
							if(roleMap!=null && roleMap.get(mm.getModulesId())!=null){
								str = str+",checked:true}";
								count2+=1;
							 }
							else{
								str = str+"}";
							}
						}
						list.add(str);
						
					}
					
				}
				if(m.getSubModules()!=null && m.getSubModules().size()>0){
					if(m.getSubModules()!=null && m.getSubModules().size()>0){
						if(m.getSubModules().size()==count2){
							s = s+",checked:true,isParent:true}";
						}else if(count2>0){
							s = s+",halfCheck:true, checked:true, isParent:true}";
						}else{
							s = s+",isParent:true}";
						}
					}
				}else{
					if(roleMap!=null && roleMap.get(m.getModulesId())!=null){
						s = s+",checked:true}";
					 }
					else{
						s = s+"}";
					}
				}
				list.add(s);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return list;
	}
}
