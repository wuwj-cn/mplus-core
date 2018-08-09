package com.mplus.modules.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.modules.sys.entity.Org;
import com.mplus.modules.sys.service.OrgService;

@RestController
@RequestMapping(value = "/org")
public class OrgController {

	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody Org org) {
		orgService.saveOrg(org);
		return Result.sucess(org);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Result getAll() {
		Org parent = orgService.findOneByCode("0");
		List<Org> orgs = orgService.findOrgsByParent(parent.getOrgId());
		return Result.sucess(orgs);
	}
	
	@RequestMapping(value = "/getOne/{orgCode}", method = RequestMethod.GET)
	public Result getOneByCode(@PathVariable String orgCode) {
		Org org = orgService.findOneByCode(orgCode);
		return Result.sucess(org);
	}
	
	@RequestMapping(value = "/getChildren/{orgCode}", method = RequestMethod.GET)
	public Result getChildren(@PathVariable String orgCode) {
		Org parent = orgService.findOneByCode(orgCode);
		List<Org> children = orgService.findOrgsByParent(parent.getOrgId());
		return Result.sucess(children);
	}
}