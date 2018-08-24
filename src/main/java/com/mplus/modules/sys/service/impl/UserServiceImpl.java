package com.mplus.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.core.base.repo.BaseRepository;
import com.mplus.core.base.service.impl.BaseServiceImpl;
import com.mplus.enums.RuleCode;
import com.mplus.enums.Status;
import com.mplus.modules.sys.entity.Org;
import com.mplus.modules.sys.entity.Role;
import com.mplus.modules.sys.entity.User;
import com.mplus.modules.sys.repo.UserRepository;
import com.mplus.modules.sys.service.CodeRuleService;
import com.mplus.modules.sys.service.OrgService;
import com.mplus.modules.sys.service.UserService;
import com.mplus.utils.EncryptUtil;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CodeRuleService codeRuleService;
	
	@Autowired
	private OrgService orgService;

	@Override
	public BaseRepository<User, String> getRepository() {
		return userRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String userName) {
		return userRepository.findByUserName(userName, Status.NORMAL.getCode());
	}

	@Override
	public User saveUser(User user) {
		if (!StringUtils.isEmpty(user.getId())) {
			throw new RuntimeException("object id is not null or empty");
		}
		if (null == user.getOrg().getOrgCode()) {
			throw new RuntimeException("org code is null");
		}
		Org org = orgService.findOneByCode(user.getOrg().getOrgCode());
		user.setOrg(org);
		
		String userCode = codeRuleService.getSerial(RuleCode.USER);
		user.setUserCode(userCode);
		
		//对用户进行散列加密
		String hashPassword = EncryptUtil.encryptPassword(user.getPassword());
		user.setPassword(hashPassword);
		
		userRepository.save(user);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByOrg(Org org) {
		if (null == org) {
			throw new RuntimeException("org is null");
		}
		return userRepository.findByOrg(org.getId(), Status.NORMAL.getCode());
	}

	@Override
	public User updateUser(User user) {
		if (StringUtils.isEmpty(user.getId())) {
			throw new RuntimeException("object id is null or empty");
		}
		return userRepository.save(user);
	}

	@Override
	public void removeUser(User user) {
		user.setRoles(null);
		user.setStatus(Status.DELETED.getCode());
		userRepository.save(user);
	}

	@Override
	public User findOneByCode(String userCode) {
		return userRepository.findOneByCode(userCode, Status.NORMAL.getCode());
	}
	
	@Override
	public List<User> findByRole(Role role) {
		if (null == role) {
			throw new RuntimeException("role is null");
		}
		return userRepository.findByRole(role.getId(), Status.NORMAL.getCode());
	}
}
