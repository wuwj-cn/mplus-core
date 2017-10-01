package com.mplus.core.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.core.entity.Privilege;
import com.mplus.core.repo.PrivilegeRepository;
import com.mplus.core.service.PrivilegeService;
import com.mplus.enums.DataState;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Override
	public Privilege savePrivilege(Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	@Override
	public Privilege updatePrivilege(Privilege privilege) {
		if(StringUtils.isEmpty(privilege.getPriviId())) {
			throw new RuntimeException("object id is null or empty");
		}
		privilege.setUpdateAt(new Date());
		return privilegeRepository.save(privilege);
	}

	@Override
	public void removePrivilege(Privilege privilege) {
		privilege.setDataState(DataState.DELETED);
		privilege.setUpdateAt(new Date());
		privilegeRepository.save(privilege);
	}

	@Override
	public Privilege findOneByCode(String priviCode) {
		return privilegeRepository.findOneByCode(priviCode, DataState.ENABLE);
	}

}
