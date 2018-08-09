package com.mplus.modules.sys.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.enums.DataState;
import com.mplus.modules.sys.entity.Permission;
import com.mplus.modules.sys.repo.PermissionRepository;
import com.mplus.modules.sys.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository privilegeRepository;
	
	@Override
	public Permission savePermission(Permission permission) {
		if (!StringUtils.isEmpty(permission.getPermissionId())) {
			throw new RuntimeException("object id is not null or empty");
		}
		return privilegeRepository.save(permission);
	}

	@Override
	public Permission updatePermission(Permission permission) {
		if(StringUtils.isEmpty(permission.getPermissionId())) {
			throw new RuntimeException("object id is null or empty");
		}
		permission.setUpdateAt(new Date());
		return privilegeRepository.save(permission);
	}

	@Override
	public void removePermission(Permission permission) {
		permission.setRoles(null);
		permission.setDataState(DataState.DELETED);
		permission.setUpdateAt(new Date());
		privilegeRepository.save(permission);
	}

	@Override
	public Permission findOneByCode(String priviCode) {
		return privilegeRepository.findOneByCode(priviCode, DataState.ENABLE);
	}

}
