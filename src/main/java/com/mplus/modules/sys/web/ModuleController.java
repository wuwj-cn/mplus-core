package com.mplus.modules.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.modules.sys.entity.Module;
import com.mplus.modules.sys.service.ModuleService;

@RestController
@RequestMapping(value = "/api/module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody Module module) {
		moduleService.save(module);
		return Result.sucess(module);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Result getAll() {
		List<Module> modules = moduleService.findAll();
		return Result.sucess(modules);
	}
	
	@RequestMapping(value = "/getOne/{menuCode}", method = RequestMethod.GET)
	public Result getOneByCode(@PathVariable String moduleCode) {
		return Result.sucess(null);
	}
}
