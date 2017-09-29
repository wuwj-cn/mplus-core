package com.mplus.core.service;

import com.mplus.core.entity.CodeRule;
import com.mplus.utils.RuleCode;

public interface CodeRuleService {

	CodeRule saveCodeRule(CodeRule rule);
	
	CodeRule updateCodeRule(CodeRule rule);
	
	void removeCodeRule(CodeRule rule);
	
	CodeRule findOneById(String ruleId);
	
	CodeRule findOneByCode(String ruleCode);
	
	String getSerial(RuleCode ruleCode);
}
