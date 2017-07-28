package com.yl.crm.service.Impl;

import java.util.List;

import com.yl.crm.dao.RoleDao;
import com.yl.crm.dao.Impl.RoleDaoImp;
import com.yl.crm.model.Role;
import com.yl.crm.service.RoleService;

public class RoleServiceImp implements RoleService {
	private RoleDao roleDao = new RoleDaoImp();

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

}
