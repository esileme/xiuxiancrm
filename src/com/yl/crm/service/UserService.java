package com.yl.crm.service;

import java.util.List;

import com.yl.crm.model.User;
import com.yl.crm.util.Pager;

public interface UserService {
	
	User getUserByName(String username);
	
	int getTotalCount(String keyword, String field);
	
	@Deprecated
	List<User> getAllUsers();
	
	List<User> getUserByKeywordFieldPager(String keyword, String field, Pager pager);
	
	Boolean addUser(User user);
	
}
