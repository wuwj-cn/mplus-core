package com.mplus.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.enums.Status;
import com.mplus.modules.sys.entity.Role;
import com.mplus.modules.sys.entity.User;
import com.mplus.modules.sys.service.RoleService;
import com.mplus.modules.sys.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testSaveRole() {
		Role role = new Role();
		role.setRoleName("admin");
		Set<User> users = new HashSet<User>();
		users.add(userService.findOneByCode("00004"));
		role.setUsers(users);
		roleService.saveRole(role);
		assertNotNull(role.getId());
	}
	
	@Test
	public void testRemoveRole() {
		Role role = roleService.findOneByCode("00004");
		roleService.removeRole(role);;
		assertEquals(Status.DELETED.getCode(), role.getStatus());
	}

}
