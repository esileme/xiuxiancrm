package com.zhiyou100.crm.dao;

import java.util.List;

import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.Pager;

public interface UserDao {

	User getUserByName(String username);

	int getTotalCount(String keyword, String field);

	List<User> getUserByKeywordFieldPager(String keyword, String field, Pager pager);

	List<Role> getAllRole();

	Boolean addUser(User user);

	@Deprecated
	List<User> getAllUsers();

}
