package com.zhiyou100.crm.service;

import java.util.List;

import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.Pager;

public interface UserService {
	
	User getUserByName(String username);
	
	int getTotalCount(String keyword, String field);
	
	@Deprecated
	List<User> getAllUsers();
	
	List<User> getUserByKeywordFieldPager(String keyword, String field, Pager pager);
	
	Boolean addUser(User user);
	
}
