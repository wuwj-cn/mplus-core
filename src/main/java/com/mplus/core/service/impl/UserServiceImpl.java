package com.mplus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mplus.core.entity.User;
import com.mplus.core.repo.UserRepository;
import com.mplus.core.service.CodeRuleService;
import com.mplus.core.service.UserService;
import com.mplus.enums.RuleCode;
import com.mplus.utils.MD5Util;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CodeRuleService codeRuleService;

	@Override
	@Transactional(readOnly = true)
	public User find(String userName, String password) {
		return userRepository.find(userName, MD5Util.MD5Salt(password));
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void saveUser(User user) {
		String userCode = codeRuleService.getSerial(RuleCode.USER);
		user.setUserCode(userCode);
		String password = MD5Util.MD5Salt(user.getPassword());
		user.setPassword(password);
		userRepository.save(user);
	}

}
