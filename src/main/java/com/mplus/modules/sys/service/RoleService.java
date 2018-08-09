package com.mplus.modules.sys.service;

import com.mplus.modules.sys.entity.Role;

public interface RoleService {

	Role saveRole(Role role);
	
	Role updateRole(Role role);
	
	void removeRole(Role role);
	
	Role findOneByCode(String roleCode);
}
