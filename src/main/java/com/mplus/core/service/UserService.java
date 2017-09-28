package com.mplus.core.service;

import com.mplus.core.entity.User;

public interface UserService {

	User find(String userName, String password);
	
	User findByUserName(String userName);
	
	void saveUser(User user);
}
