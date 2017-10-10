package com.mplus.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.core.entity.Org;
import com.mplus.core.entity.Role;
import com.mplus.core.entity.User;
import com.mplus.core.repo.UserRepository;
import com.mplus.core.service.CodeRuleService;
import com.mplus.core.service.OrgService;
import com.mplus.core.service.UserService;
import com.mplus.enums.DataState;
import com.mplus.enums.RuleCode;
import com.mplus.utils.MD5Util;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CodeRuleService codeRuleService;
	
	@Autowired
	private OrgService orgService;

	@Override
	@Transactional(readOnly = true)
	public User find(String userName, String password) {
		return userRepository.find(userName, MD5Util.MD5Salt(password));
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String userName) {
		return userRepository.findByUserName(userName, DataState.ENABLE);
	}

	@Override
	public User saveUser(User user) {
		if (!StringUtils.isEmpty(user.getUserId())) {
			throw new RuntimeException("object id is not null or empty");
		}
		if (null == user.getOrg().getOrgCode()) {
			throw new RuntimeException("org code is null");
		}
		Org org = orgService.findOneByCode(user.getOrg().getOrgCode());
		user.setOrg(org);
		
		String userCode = codeRuleService.getSerial(RuleCode.USER);
		user.setUserCode(userCode);
		
		String password = MD5Util.MD5Salt(user.getPassword());
		user.setPassword(password);
		
		userRepository.save(user);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByOrg(Org org) {
		if (null == org) {
			throw new RuntimeException("org is null");
		}
		return userRepository.findByOrg(org.getOrgId(), DataState.ENABLE);
	}

	@Override
	public User updateUser(User user) {
		if (StringUtils.isEmpty(user.getUserId())) {
			throw new RuntimeException("object id is null or empty");
		}
		user.setUpdateAt(new Date());
		return userRepository.save(user);
	}

	@Override
	public void removeUser(User user) {
		user.setRoles(null);
		user.setDataState(DataState.DELETED);
		user.setUpdateAt(new Date());
		userRepository.save(user);
	}

	@Override
	public User findOneByCode(String userCode) {
		return userRepository.findOneByCode(userCode, DataState.ENABLE);
	}
	
	@Override
	public List<User> findByRole(Role role) {
		if (null == role) {
			throw new RuntimeException("role is null");
		}
		return userRepository.findByRole(role.getRoleId(), DataState.ENABLE);
	}
}
