package com.mplus.modules.sys.web;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.enums.Status;
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
	
	@RequestMapping(value = "/list/{page}/{size}", method = RequestMethod.GET)
	public Result list(@PathVariable int page, @PathVariable int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", Status.NORMAL.getCode());
		
		Pageable pageable = new PageRequest(0, 10,new Sort(Direction.DESC, "updateDate"));
		Page<Module> modules = moduleService.list(params, pageable);
		return Result.sucess(modules);
	}
	
	@RequestMapping(value = "/get/{moduleCode}", method = RequestMethod.GET)
	public Result getOneByCode(@PathVariable String moduleCode) {
		return Result.sucess(null);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Result update(@RequestBody Module module) {
		moduleService.update(module);
		return Result.sucess(module);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public Result delete(@PathVariable String id) {
		moduleService.delete(id);
		return Result.sucess(id);
	}
}
