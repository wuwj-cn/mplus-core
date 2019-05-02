package com.mplus.modules.sys.service;

import java.util.List;

import com.mplus.core.tree.service.TreeService;
import com.mplus.modules.sys.entity.Org;

public interface OrgService extends TreeService {

	Org saveOrg(Org org);
	
	Org updateOrg(Org org);
	
	void removeOrg(Org org);
	
	Org findOneByCode(String orgCode);
	
	List<Org> findOrgsByParent(String parentOrgId);
}
