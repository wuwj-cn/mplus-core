package com.mplus.core.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.core.entity.Role;
import com.mplus.core.repo.RoleRepository;
import com.mplus.core.service.RoleService;
import com.mplus.enums.DataState;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		if (StringUtils.isEmpty(role.getRoleId())) {
			throw new RuntimeException("object id is null or empty");
		}
		role.setUpdateAt(new Date());
		return roleRepository.save(role);
	}

	@Override
	public void removeRole(Role role) {
		role.setDataState(DataState.DELETED);
		role.setUpdateAt(new Date());
		roleRepository.save(role);
	}

	@Override
	public Role fineOneByCode(String roleCode) {
		return roleRepository.findOneByCode(roleCode, DataState.ENABLE);
	}

}
