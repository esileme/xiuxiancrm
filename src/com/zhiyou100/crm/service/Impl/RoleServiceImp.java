package com.zhiyou100.crm.service.Impl;

import java.util.List;

import com.zhiyou100.crm.dao.RoleDao;
import com.zhiyou100.crm.dao.Impl.RoleDaoImp;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.service.RoleService;

public class RoleServiceImp implements RoleService {
	private RoleDao roleDao = new RoleDaoImp();

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

}
