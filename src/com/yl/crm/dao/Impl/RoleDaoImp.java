package com.yl.crm.dao.Impl;

import java.util.List;

import com.yl.crm.dao.RoleDao;
import com.yl.crm.model.Role;
import com.yl.crm.util.DBUtil;

public class RoleDaoImp implements RoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {

		String sql = "select * from role";
		Object[] paramters = {};
		List<Role> roles = (List<Role>) DBUtil.queryForList(sql, paramters, Role.class);

		return roles;
	}
	
	public static void main(String[] args) {
		RoleDaoImp imp = new RoleDaoImp();
		List<Role> roles = imp.getAllRoles();
		System.out.println(roles.get(0));
		System.out.println(roles.get(1));
	}

}
