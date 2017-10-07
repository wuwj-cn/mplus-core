package com.mplus.core.service;

import java.util.List;

import com.mplus.core.entity.Org;
import com.mplus.core.entity.Role;
import com.mplus.core.entity.User;

public interface UserService {

	User saveUser(User user);
	
	User updateUser(User user);
	
	void removeUser(User user);
	
	User find(String userName, String password);
	
	User findByUserName(String userName);
	
	List<User> findByOrg(Org org);
	
	User findOneByCode(String userCode);
	
	List<User> findByRole(Role role);
}
