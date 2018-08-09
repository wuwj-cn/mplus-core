package com.mplus.modules.sys.service;

import com.mplus.enums.RuleCode;
import com.mplus.modules.sys.entity.CodeRule;

public interface CodeRuleService {

	CodeRule saveCodeRule(CodeRule rule);
	
	CodeRule updateCodeRule(CodeRule rule);
	
	void removeCodeRule(CodeRule rule);
	
	CodeRule findOneById(String ruleId);
	
	CodeRule findOneByCode(String ruleCode);
	
	String getSerial(RuleCode ruleCode);
}
