package com.edu.service.user;

import com.edu.entity.user.User;

public interface IUserService {
	
	User findBynameAndPassword(User user);
	
	int insert(User user);

}
