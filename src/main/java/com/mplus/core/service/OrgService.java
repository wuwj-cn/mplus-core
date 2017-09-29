package com.mplus.core.service;

import java.util.List;

import com.mplus.core.entity.Org;
import com.mplus.core.tree.service.TreeService;

public interface OrgService extends TreeService {

	Org saveOrg(Org org);
	
	Org updateOrg(Org org);
	
	void removeOrg(Org org);
	
	Org findOneByCode(String orgCode);
	
	List<Org> findOrgsByParent(String parentOrgId);
}
