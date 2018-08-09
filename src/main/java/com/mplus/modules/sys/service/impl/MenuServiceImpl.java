package com.mplus.modules.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.enums.DataState;
import com.mplus.modules.sys.entity.Menu;
import com.mplus.modules.sys.repo.MenuRepository;
import com.mplus.modules.sys.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public Menu saveMenu(Menu menu) {
		if (!StringUtils.isEmpty(menu.getMenuId())) {
			throw new RuntimeException("object id is not null or empty");
		}
		return menuRepository.save(menu);
	}

	@Override
	public Menu updateMenu(Menu menu) {
		if (StringUtils.isEmpty(menu.getMenuId())) {
			throw new RuntimeException("object id is null or empty");
		}
		menu.setUpdateAt(new Date());
		return menuRepository.save(menu);
	}

	@Override
	public void removeOrg(Menu menu) {
		menu.setDataState(DataState.DELETED);
		menu.setUpdateAt(new Date());
		menuRepository.save(menu);
	}

	@Override
	@Transactional(readOnly = true)
	public Menu findOneByCode(String menuCode) {
		return menuRepository.findOneByCode(menuCode, DataState.ENABLE);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Menu> findMenusByParent(String parentMenuId) {
		return menuRepository.findMenusByParent(parentMenuId, DataState.ENABLE);
	}

}
