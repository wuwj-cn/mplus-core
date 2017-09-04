package com.mplus.core.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.core.entity.User;
import com.mplus.core.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("wuwj");
		user.setCode("001");
		user.setPassword("123456");
		userRepository.save(user);
		User u = userRepository.findByUsername("wuwj");
		assertNotNull(u);
	}

}
