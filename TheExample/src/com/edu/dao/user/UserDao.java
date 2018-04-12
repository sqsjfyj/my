package com.edu.dao.user;

import org.springframework.stereotype.Repository;

import com.edu.dao.DaoHibernate;
import com.edu.entity.user.User;

@Repository("userDao")
public class UserDao extends DaoHibernate<User> {
	
	public User findBynameAndPassword(User user){
		String hql = "from User u where u.userName=? and u.userPassword=?";
		String param[] = {user.getUserName(), user.getUserPassword()};
		User user1 = this.findOne(hql, param);
		return user1;
	}
	
	public User findByname(User user){
		String hql = "from User u where u.userName=?";
		String param[] = {user.getUserName()};
		User user1 = this.findOne(hql, param);
		return user1;
	}
	
}
