package com.mplus.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.core.entity.Privilege;
import com.mplus.core.service.PrivilegeService;

@RestController
@RequestMapping(value = "/privilege")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody Privilege privilege) {
		privilegeService.savePrivilege(privilege);
		return Result.sucess(privilege);	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Result update(@RequestBody Privilege privilege) {
		privilegeService.updatePrivilege(privilege);
		return Result.sucess(privilege);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public Result remove(@PathVariable String priviCode) {
		Privilege privilege = privilegeService.findOneByCode(priviCode);
		privilegeService.removePrivilege(privilege);
		return Result.sucess(privilege);
	}
	
	@RequestMapping(value = "/getOne/{priviCode}", method = RequestMethod.GET)
	public Result getOne(@PathVariable String priviCode) {
		Privilege privilege = privilegeService.findOneByCode(priviCode);
		return Result.sucess(privilege);
	}
}
