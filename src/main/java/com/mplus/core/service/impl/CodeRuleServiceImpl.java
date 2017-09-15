package com.mplus.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mplus.core.entity.CodeRule;
import com.mplus.core.repo.CodeRuleRepository;
import com.mplus.core.service.CodeRuleService;
import com.mplus.utils.DataState;
import com.mysql.jdbc.StringUtils;

@Service
public class CodeRuleServiceImpl implements CodeRuleService {

	@Autowired
	CodeRuleRepository codeRuleRespository;
	
	@Override
	public CodeRule saveCodeRule(CodeRule rule) {
		CodeRule r = codeRuleRespository.findOneByCode(rule.getRuleCode(), DataState.ENABLE);
		if (r != null) {
			throw new RuntimeException("规则编码已存在");
		}
		codeRuleRespository.save(rule);
		return rule;
	}

	@Override
	public CodeRule updateCodeRule(CodeRule rule) {
		if (StringUtils.isNullOrEmpty(rule.getRuleId())) {
			throw new RuntimeException("object id is null or empty");
		}
		codeRuleRespository.save(rule);
		return rule;
	}

	@Override
	public void removeCodeRule(CodeRule rule) {
		rule.setDataState(DataState.DELETED);
		codeRuleRespository.save(rule);
	}

	@Override
	public CodeRule findOneById(String ruleId) {
		return codeRuleRespository.findOneById(ruleId);
	}

	@Override
	public CodeRule findOneByCode(String ruleCode) {
		return codeRuleRespository.findOneByCode(ruleCode, DataState.ENABLE);
	}

}
