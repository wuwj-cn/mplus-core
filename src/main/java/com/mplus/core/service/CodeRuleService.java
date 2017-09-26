package com.mplus.core.service;

import org.springframework.util.concurrent.ListenableFuture;

import com.mplus.core.entity.CodeRule;

public interface CodeRuleService {

	CodeRule saveCodeRule(CodeRule rule);
	
	CodeRule updateCodeRule(CodeRule rule);
	
	void removeCodeRule(CodeRule rule);
	
	CodeRule findOneById(String ruleId);
	
	CodeRule findOneByCode(String ruleCode);
	
	String getSerial(String ruleCode);
}
