package com.mplus.core.service;

import com.mplus.core.domain.User;

public interface UserService {

	User find(String username, String password);
	
	User findByUsername(String username);
}
