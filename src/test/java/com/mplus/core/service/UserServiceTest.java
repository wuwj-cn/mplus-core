package com.mplus.core.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.enums.UserState;
import com.mplus.modules.sys.entity.Org;
import com.mplus.modules.sys.entity.Role;
import com.mplus.modules.sys.entity.User;
import com.mplus.modules.sys.service.OrgService;
import com.mplus.modules.sys.service.RoleService;
import com.mplus.modules.sys.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private RoleService roleService;

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUserName("wuwj");
		user.setPassword("123456");
//		user.setUserState(UserState.ENABLE);
		Org org = orgService.findOneByCode("001101");
		user.setOrg(org);
		userService.saveUser(user);
	}

	public void testFindByUserName() {
		// 该测试用例在懒加载情况下执行会出现no session异常
		User user = userService.findByUsername("wuwj");
		assertArrayEquals(new Object[]{"001101"}, new Object[]{user.getOrg().getOrgCode()});
	}
	
	@Test
	public void testFindByOrg(){
		Org org = orgService.findOneByCode("001101");
		List<User> users = userService.findByOrg(org);
		assertNotNull(users);
	}
	
	@Test
	public void testFindByRole(){
		Role role = roleService.findOneByCode("00003");
		List<User> users = userService.findByRole(role);
		assertNotNull(users);
	}
	
	@Test
	public void testRemoveUser() {
		User user =  userService.findOneByCode("00004");
		userService.delete(user);
	}
}
