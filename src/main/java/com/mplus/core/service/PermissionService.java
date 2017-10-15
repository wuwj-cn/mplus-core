package com.mplus.core.service;

import com.mplus.core.entity.Permission;

public interface PermissionService {

	Permission savePermission(Permission permission);
	
	Permission updatePermission(Permission permission);
	
	void removePermission(Permission permission);
	
	Permission findOneByCode(String permissionCode);
}
