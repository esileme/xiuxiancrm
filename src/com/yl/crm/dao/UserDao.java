package com.yl.crm.dao;

import java.util.List;

import com.yl.crm.model.Department;
import com.yl.crm.model.Role;
import com.yl.crm.model.User;
import com.yl.crm.util.Pager;

public interface UserDao {

	User getUserByName(String username);

	User getUserById(int userId);

	int getTotalCount(String keyword, String field);

	List<User> getUserByKeywordFieldPager(String keyword, String field, Pager pager);

	List<Role> getAllRole();

	Boolean addUser(User user);

	Boolean updateUser(User user);

	Boolean deleteUser(User user);

	@Deprecated
	List<User> getAllUsers();

}
