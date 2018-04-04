package com.mplus.core.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.core.entity.Role;
import com.mplus.core.entity.User;
import com.mplus.enums.DataState;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
//	@Test
	public void testSaveRole() {
		Role role = new Role();
		role.setRoleName("admin");
		Set<User> users = new HashSet<User>();
		users.add(userService.findOneByCode("00004"));
		role.setUsers(users);
		roleService.saveRole(role);
		assertNotNull(role.getRoleId());
	}
	
//	@Test
	public void testRemoveRole() {
		Role role = roleService.findOneByCode("00004");
		roleService.removeRole(role);;
		assertEquals(DataState.DELETED, role.getDataState());
	}

}
