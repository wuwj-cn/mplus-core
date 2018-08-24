package com.mplus.modules.sys.service;

import java.util.List;

import com.mplus.core.base.service.BaseService;
import com.mplus.modules.sys.entity.Org;
import com.mplus.modules.sys.entity.Role;
import com.mplus.modules.sys.entity.User;

public interface UserService extends BaseService<User, String> {

	User saveUser(User user);
	
	User updateUser(User user);
	
	void removeUser(User user);
	
	User findByUsername(String username);
	
	List<User> findByOrg(Org org);
	
	User findOneByCode(String userCode);
	
	List<User> findByRole(Role role);
}
