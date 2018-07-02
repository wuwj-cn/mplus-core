package com.mplus.core.service;

import java.util.List;

import com.mplus.core.entity.Menu;
import com.mplus.core.entity.Org;

public interface MenuService {

	Menu saveMenu(Menu menu);
	
	Menu updateMenu(Menu menu);
	
	void removeOrg(Menu menu);
	
	Menu findOneByCode(String menuCode);
	
	List<Org> findMenusByParent(String parentMenuId);
	
}
