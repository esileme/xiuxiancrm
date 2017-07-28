package com.yl.crm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yl.crm.dao.UserDao;
import com.yl.crm.model.Department;
import com.yl.crm.model.Role;
import com.yl.crm.model.User;
import com.yl.crm.util.BeanUtil;
import com.yl.crm.util.DBUtil;
import com.yl.crm.util.Pager;

public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByName(String username) {

		String sql = "SELECT a.*,d.department_name FROM department d RIGHT JOIN "
				+ "(SELECT r.role_name,u.* FROM role r RIGHT JOIN (SELECT * FROM user)u "
				+ "ON(u.role_id = r.role_id))a " + "ON(d.department_id = a.department_id) " + "WHERE a.username = ?";
		Object[] paramters = { username };
		List<User> users = (List<User>) DBUtil.queryForList(sql, paramters, User.class);

		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}

	}

	@Override
	public int getTotalCount(String keyword, String field) {
		int total = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) as total from user as u where status=2 and is_system=0");
		if (!"".equalsIgnoreCase(keyword)) {
			builder.append(" and u." + field + " like " + "'%" + keyword + "%'");
		}
		String sql = builder.toString();

		try {
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
				System.out.println("本次查询出:" + total + "条数据");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.release(connection, ps, rs);
		}

		return total;
	}

	@Deprecated
	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT a.*,d.department_name FROM department d RIGHT JOIN "
				+ "(SELECT r.role_name,u.* FROM role r RIGHT JOIN (SELECT * FROM user)u "
				+ "ON(u.role_id = r.role_id))a " + "ON(d.department_id = a.department_id) ";

		Object[] paramters = {};
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) DBUtil.queryForList(sql, paramters, User.class);

		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByKeywordFieldPager(String keyword, String field, Pager pager) {

		StringBuilder builder = new StringBuilder();
		builder.append(
				"select u.*,d.department_name,r.role_name from user u LEFT JOIN department d ON(u.department_id = d.department_id)LEFT JOIN role r ON(u.role_id = r.role_id)  where u.status=2 and u.is_system=0 ");
		if (!keyword.equals("")) {
			builder.append("and u." + field + " like " + "'%" + keyword + "%'");
		}
		String limit = " limit " + (pager.getPageNo() - 1) * pager.getPageSize() + "," + pager.getPageSize();
		builder.append(limit);
		String sql = builder.toString();

		Object[] paras = {};
		List<User> users = (List<User>) DBUtil.queryForList(sql, paras, User.class);

		return users;
	}

	@Override
	public List<Role> getAllRole() {

		return null;
	}

	public static void main(String[] args) {
		UserDaoImpl impl = new UserDaoImpl();
		List<User> users = impl.getAllUsers();
		System.out.println(users.size());

	}

	@Override
	public Boolean addUser(User user) {
		int i = 0;
		Connection con = DBUtil.getConnection();
		String sql = "INSERT INTO user (`username`, `password`, `department_id`, `role_id`, `is_male`, `mobile`, "
				+ "`address`, `age`, `tel`, `id_num`, `email`, `qq`, "
				+ "`hobby`, `education`, `card_num`, `nation`, `marry`, `status`, "
				+ "`remark`, `create_time`, `creater`,`is_system`" + ") VALUES (?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?, " + "?, ?, ?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, user.getUsername());
			ps.setObject(2, user.getPassword());
			ps.setObject(3, user.getDepartmentId());
			ps.setObject(4, user.getRoleId());
			ps.setObject(5, user.getIsMale());
			ps.setObject(6, user.getMobile());
			ps.setObject(7, user.getAddress());
			ps.setObject(8, user.getAge());
			ps.setObject(9, user.getTel());
			ps.setObject(10, user.getIdNum());
			ps.setObject(11, user.getEmail());
			ps.setObject(12, user.getQq());
			ps.setObject(13, user.getHobby());
			ps.setObject(14, user.getEducation());
			ps.setObject(15, user.getCardNum());
			ps.setObject(16, user.getNation());
			ps.setObject(17, user.getMarry());
			ps.setObject(18, 2);
			ps.setObject(19, user.getRemark());
			ps.setObject(20, user.getCreateTime());
			ps.setObject(21, user.getCreater());
			ps.setObject(22, user.getIsSystem());

			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i > 0;
	}

}
