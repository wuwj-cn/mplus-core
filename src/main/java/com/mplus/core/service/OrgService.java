package com.mplus.core.service;

import java.util.List;

import com.mplus.core.entity.Org;

public interface OrgService {

	Org saveOrg(Org org);
	
	Org updateOrg(Org org);
	
	void removeOrg(Org org);
	
	Org findOneByCode(String orgCode);
	
	List<Org> findOrgsByParent(String parentOrgId);
}
