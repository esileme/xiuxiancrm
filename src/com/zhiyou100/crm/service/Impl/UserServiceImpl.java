package com.zhiyou100.crm.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import com.zhiyou100.crm.dao.UserDao;
import com.zhiyou100.crm.dao.Impl.UserDaoImpl;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.UserService;
import com.zhiyou100.crm.util.Pager;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();

	@Override
	public User getUserByName(String username) {

		return userDao.getUserByName(username);
	}

	@Override
	public int getTotalCount(String keyword, String field) {

		return userDao.getTotalCount(keyword, field);
	}

	@Deprecated
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public List<User> getUserByKeywordFieldPager(String keyword, String field, Pager pager) {
		// TODO Auto-generated method stub
		return userDao.getUserByKeywordFieldPager(keyword, field, pager);
	}

	@Override
	public Boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

}
