package com.mplus.modules.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mplus.core.advice.Result;
import com.mplus.modules.sys.entity.Menu;
import com.mplus.modules.sys.service.MenuService;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result add(@RequestBody Menu menu) {
		menuService.saveMenu(menu);
		return Result.sucess(menu);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Result getAll() {
		Menu parent = menuService.findOneByCode("0");
		List<Menu> menus = menuService.findMenusByParent(parent.getMenuId());
		return Result.sucess(menus);
	}
	
	@RequestMapping(value = "/getOne/{menuCode}", method = RequestMethod.GET)
	public Result getOneByCode(@PathVariable String menuCode) {
		Menu menu = menuService.findOneByCode(menuCode);
		return Result.sucess(menu);
	}
	
	@RequestMapping(value = "/getChildren/{menuCode}", method = RequestMethod.GET)
	public Result getChildren(@PathVariable String menuCode) {
		Menu parent = menuService.findOneByCode(menuCode);
		List<Menu> children = menuService.findMenusByParent(parent.getMenuId());
		return Result.sucess(children);
	}
}
