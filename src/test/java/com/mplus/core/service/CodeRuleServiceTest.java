package com.mplus.core.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.enums.RuleCode;
import com.mplus.enums.RulePolicy;
import com.mplus.modules.sys.entity.CodeRule;
import com.mplus.modules.sys.service.CodeRuleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CodeRuleServiceTest {

	@Autowired
	private CodeRuleService codeRuleService;

	@Test
	public void testSaveCodeRule() {
		CodeRule rule = new CodeRule();
		rule.setRuleCode(RuleCode.USER.getCode());
		rule.setRuleName(RuleCode.USER.getName());
		rule.setRulePolicy(RulePolicy.SERIAL);
		rule.setSerialLength(5);
		rule.setCurrentValue(String.format("%0"+rule.getSerialLength()+"d", 0));
		codeRuleService.saveCodeRule(rule);
		CodeRule r = codeRuleService.findOneByCode("001");
		assertNotNull(r);
	}
	
	@Test
	public void testRemoveCodeRule() {
		CodeRule rule = codeRuleService.findOneByCode("001");
		codeRuleService.removeCodeRule(rule);
		rule = codeRuleService.findOneByCode("001");
		assertNull(rule);
	}
}
