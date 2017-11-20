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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;
	
	@ApiOperation(value="添加用户", notes="根据User对象创建新用户")
	@ApiImplicitParam(name = "user", value = "用户实体信息", required = true, dataType = "User")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody User user) {
		userService.saveUser(user);
		return Result.sucess(user);	
	}
	
	@ApiOperation(value="更新用户", notes="根据User对象更新用户信息")
	@ApiImplicitParam(name = "user", value = "用户实体信息", required = true, dataType = "User")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Result update(@RequestBody User user) {
		userService.updateUser(user);
		return Result.sucess(user);
	}
	
	@ApiOperation(value="删除用户", notes="根据userCode来删除用户")
	@ApiImplicitParam(name = "userCode", value = "用户编码", required = true, dataType = "String", paramType = "path")
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
