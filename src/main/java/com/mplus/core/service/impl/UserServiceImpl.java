package com.mplus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplus.core.entity.User;
import com.mplus.core.repo.UserRepository;
import com.mplus.core.service.UserService;
import com.mplus.utils.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User find(String username, String password) {
		return userRepository.find(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		String password = MD5Util.MD5Salt(user.getPassword());
		user.setPassword(password);
		userRepository.save(user);
	}

}
