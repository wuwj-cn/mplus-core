package com.mplus.core.service;

import com.mplus.core.entity.Privilege;

public interface PrivilegeService {

	Privilege savePrivilege(Privilege privilege);
	
	Privilege updatePrivilege(Privilege privilege);
	
	void removePrivilege(Privilege privilege);
	
	Privilege findOneByCode(String priviCode);
}
