package com.mplus.modules.sys.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.enums.Status;
import com.mplus.enums.RuleCode;
import com.mplus.modules.sys.entity.Role;
import com.mplus.modules.sys.repo.RoleRepository;
import com.mplus.modules.sys.service.CodeRuleService;
import com.mplus.modules.sys.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private CodeRuleService codeRuleService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role saveRole(Role role) {
		if (!StringUtils.isEmpty(role.getId())) {
			throw new RuntimeException("object id is not null or empty");
		}
		String roleCode = codeRuleService.getSerial(RuleCode.ROLE);
		role.setRoleCode(roleCode);
		return roleRepository.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		if (StringUtils.isEmpty(role.getId())) {
			throw new RuntimeException("object id is null or empty");
		}
		return roleRepository.save(role);
	}

	@Override
	public void removeRole(Role role) {
		role.setUsers(null); // remove all user
		role.setPermissions(null); // remove all permissions
		role.setStatus(Status.DELETED.getCode());
		roleRepository.save(role);
	}

	@Override
	public Role findOneByCode(String roleCode) {
		return roleRepository.findOneByCode(roleCode, Status.NORMAL.getCode());
	}

}
