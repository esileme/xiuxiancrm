package com.yl.crm.dao;

import java.util.List;

import com.yl.crm.model.Role;

public interface RoleDao {

	List<Role> getAllRoles();

	boolean addRole(Role role);

	Role getRoleById(Integer id);

	boolean deleteRole(Role role);

	boolean updateRole(Role role);

}
