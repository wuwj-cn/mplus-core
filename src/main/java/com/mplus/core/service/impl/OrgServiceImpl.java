package com.mplus.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mplus.core.entity.Org;
import com.mplus.core.repo.OrgRepository;
import com.mplus.core.service.OrgService;
import com.mplus.core.tree.entity.CheckboxTreeNode;
import com.mplus.core.tree.entity.TreeNode;
import com.mplus.enums.DataState;

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
		if (StringUtils.isEmpty(org.getOrgId())) {
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
	@Transactional(readOnly = true)
	public Org findOneByCode(String orgCode) {
		return orgRepository.findOneByCode(orgCode, DataState.ENABLE);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Org> findOrgsByParent(String parentOrgId) {
		return orgRepository.findOrgsByParent(parentOrgId, DataState.ENABLE);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TreeNode> getNodes(String id) {
		List<Org> orgs = this.findOrgsByParent(id);
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		orgs.stream().forEach(
				org -> nodes.add(new TreeNode(org.getOrgId(), org.getOrgCode(), org.getOrgName(), false, false)));
		return nodes;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TreeNode> getCheckboxNodes(String id) {
		List<Org> orgs = this.findOrgsByParent(id);
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		orgs.stream().forEach(org -> nodes
				.add(new CheckboxTreeNode(org.getOrgId(), org.getOrgCode(), org.getOrgName(), false, false, false)));
		return nodes;
	}

}
