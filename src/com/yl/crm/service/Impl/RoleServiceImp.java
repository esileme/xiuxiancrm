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

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.addRole(role);
	}

	@Override
	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.getRoleById(id);
	}

	@Override
	public boolean deleteRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.deleteRole(role);
	}

	@Override
	public boolean updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.updateRole(role);
	}

}
