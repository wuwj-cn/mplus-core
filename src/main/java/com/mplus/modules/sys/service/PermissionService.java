package com.mplus.modules.sys.service;

import com.mplus.modules.sys.entity.Permission;

public interface PermissionService {

	Permission savePermission(Permission permission);
	
	Permission updatePermission(Permission permission);
	
	void removePermission(Permission permission);
	
	Permission findOneByCode(String permissionCode);
}
