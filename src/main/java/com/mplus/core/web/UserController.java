package com.mplus.core.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.core.entity.Org;
import com.mplus.core.entity.User;
import com.mplus.core.service.OrgService;
import com.mplus.core.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody User user) {
		userService.saveUser(user);
		return Result.sucess(user);	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Result update(@RequestBody User user) {
		userService.updateUser(user);
		return Result.sucess(user);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Result remove(@PathVariable String userCode) {
		User user = userService.findOneByCode(userCode);
		userService.removeUser(user);
		return Result.sucess(user);
	}
	
	@RequestMapping(value = "/getOne/{userCode}", method = RequestMethod.GET)
	public Result getOne(@PathVariable String userCode) {
		User user = userService.findOneByCode(userCode);
		return Result.sucess(user);
	}
	
	@RequestMapping(value = "/getUsersByOrg/{userCode}", method = RequestMethod.GET)
	public Result getUsersByOrg(@PathVariable String orgCode) {
		Org org = orgService.findOneByCode(orgCode);
		List<User> users = userService.findByOrg(org);
		return Result.sucess(users);
	}
}
