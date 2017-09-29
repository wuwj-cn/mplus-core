package com.mplus.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mplus.core.entity.Org;
import com.mplus.core.repo.OrgRepository;
import com.mplus.core.service.OrgService;
import com.mplus.utils.DataState;
import com.mysql.jdbc.StringUtils;

@Service
@Transactional
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgRepository orgRepository;
	
	@Override
	public Org saveOrg(Org org) {
		return orgRepository.save(org);
	}

	@Override
	public Org updateOrg(Org org) {
		if (StringUtils.isNullOrEmpty(org.getOrgId())) {
			throw new RuntimeException("object id is null or empty");
		}
		org.setUpdateAt(new Date());
		return orgRepository.save(org);
	}

	@Override
	public void removeOrg(Org org) {
		org.setDataState(DataState.DELETED);
		org.setUpdateAt(new Date());
		orgRepository.save(org);
	}

	@Override
	public Org findOneByCode(String orgCode) {
		return orgRepository.findOneByCode(orgCode, DataState.ENABLE);
	}

	@Override
	public List<Org> findOrgsByParent(String parentOrgId) {
		return orgRepository.findOrgsByParent(parentOrgId, DataState.ENABLE);
	}

}
