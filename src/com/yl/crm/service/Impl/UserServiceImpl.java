package com.yl.crm.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import com.yl.crm.dao.UserDao;
import com.yl.crm.dao.Impl.UserDaoImpl;
import com.yl.crm.model.User;
import com.yl.crm.service.UserService;
import com.yl.crm.util.Pager;

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

	@Override
	public Boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	@Override
	public Boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(user);
	}

}
