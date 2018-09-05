package com.mplus.modules.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.enums.Status;
import com.mplus.modules.sys.entity.Org;
import com.mplus.modules.sys.entity.User;
import com.mplus.modules.sys.service.OrgService;
import com.mplus.modules.sys.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user")
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
		userService.update(user);
		return Result.sucess(user);
	}
	
	@ApiOperation(value="删除用户", notes="根据userCode来删除用户")
	@ApiImplicitParam(name = "userCode", value = "用户编码", required = true, dataType = "String", paramType = "path")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Result remove(@PathVariable String userCode) {
		User user = userService.findOneByCode(userCode);
		userService.delete(user);
		return Result.sucess(user);
	}
	
	@RequestMapping(value = "/list/{userCode}", method = RequestMethod.GET)
	public Result list(@PathVariable String userCode) {
		User user = userService.findOneByCode(userCode);
		return Result.sucess(user);
	}
	
	@RequestMapping(value = "/listByOrg/{orgCode}", method = RequestMethod.GET)
	public Result listByOrg(@PathVariable String orgCode) {
		Org org = orgService.findOneByCode(orgCode);
		List<User> users = userService.findByOrg(org);
		return Result.sucess(users);
	}
	
	@RequestMapping(value = "/list/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public Result list(
			@PathVariable int pageIndex, 
			@PathVariable int pageSize, 
			@RequestParam(required=false) String username, 
			@RequestParam(required=false) String nickName, 
			@RequestParam(required=false) String status,
			@RequestParam(required=false) String sortProperties,
			@RequestParam(required=false) String sortDirection) {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(username)) searchParams.put("username:like", username);
		if(StringUtils.isNotBlank(nickName)) searchParams.put("nickName:like", nickName);
		if(StringUtils.isBlank(status)) {
			searchParams.put("status:ne", Status.DELETED.getCode());
		} else {
			searchParams.put("status:eq", status);
		}
		
		List<String> properties = new ArrayList<String>();
		if(StringUtils.isNotBlank(sortProperties)) {
			properties.add(sortProperties);
		} else {
			properties.add("createDate"); //默认排序条件
		}
		
		Direction direction = Direction.DESC; //默认倒序
		if(StringUtils.isNotBlank(sortDirection) && sortDirection.equals("ascend")) {
			direction = Direction.ASC;
		}
		
		Pageable pageable = new PageRequest(pageIndex, pageSize, new Sort(direction, properties));
		Page<User> users = userService.list(searchParams, pageable);
		return Result.sucess(users);
	}
}
