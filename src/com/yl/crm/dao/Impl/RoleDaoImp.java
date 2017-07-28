package com.yl.crm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.yl.crm.dao.RoleDao;
import com.yl.crm.model.Role;
import com.yl.crm.util.DBUtil;

public class RoleDaoImp implements RoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {

		String sql = "select * from role where status = 2";
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

	@Override
	public boolean addRole(Role role) {

		System.out.println(role.toString());
		Connection con = DBUtil.getConnection();
		int isUpdate = 0;
		String sql = "insert into role (role_name,role_desc,status,create_time,update_time)values(?,?,?,?,?) ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role.getRoleName());
			ps.setObject(2, role.getRoleDesc());
			ps.setObject(3, role.getStatus());
			ps.setObject(4, role.getCreateTime());
			ps.setObject(5, role.getUpdateTime());
			isUpdate = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isUpdate > 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Role getRoleById(Integer id) {
		String sql = "select * from role where role_id = ?";

		Object[] paramters = { id };
		List<Role> roles = (List<Role>) DBUtil.queryForList(sql, paramters, Role.class);

		return roles.get(0);
	}

	@Override
	public boolean deleteRole(Role role) {

		String sql = "update role set status = 0 where role_id = ?";
		Connection connection = DBUtil.getConnection();
		int isUpdate = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, role.getRoleId());
			isUpdate = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isUpdate > 0;
	}

	@Override
	public boolean updateRole(Role role) {

		System.out.println(role.toString());
		Connection con = DBUtil.getConnection();
		int isUpdate = 0;
		String sql = "update role set role_name=?,role_desc=?,status=2,update_time=? where role_id = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, role.getRoleName());
			ps.setObject(2, role.getRoleDesc());
			ps.setObject(3, role.getUpdateTime());
			ps.setObject(4, role.getRoleId());
			isUpdate = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isUpdate > 0;

	}

}
