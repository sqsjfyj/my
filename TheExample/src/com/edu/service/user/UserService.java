package com.edu.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edu.dao.user.UserDao;
import com.edu.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService implements IUserService {
	
	@Resource
	private UserDao userDao;
	
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findBynameAndPassword(User user) {
		// TODO Auto-generated method stub
		return userDao.findBynameAndPassword(user);
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

}
