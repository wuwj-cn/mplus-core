package com.mplus.core.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.core.entity.Org;
import com.mplus.core.entity.User;
import com.mplus.enums.UserState;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;

	public void testSaveUser() {
		User user = new User();
		user.setUserName("wuwj");
		user.setPassword("123456");
		user.setUserState(UserState.ENABLE);
		Org org = orgService.findOneByCode("001101");
		user.setOrg(org);
		userService.saveUser(user);
	}

	public void testFindByUserName() {
		// 该测试用例在懒加载情况下执行会出现no session异常
		User user = userService.findByUserName("wuwj");
		assertArrayEquals(new Object[]{"001101"}, new Object[]{user.getOrg().getOrgCode()});
	}
	
	@Test
	public void testFindByOrg(){
		Org org = orgService.findOneByCode("001101");
		List<User> users = userService.findByOrg(org);
		assertNotNull(users);
	}
}
