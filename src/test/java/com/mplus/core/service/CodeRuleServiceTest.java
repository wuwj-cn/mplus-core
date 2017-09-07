package com.mplus.core.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.core.entity.CodeRule;
import com.mplus.utils.RulePolicy;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CodeRuleServiceTest {

	@Autowired
	private CodeRuleService codeRuleService;

	@Test
	public void testSaveCodeRule() {
		CodeRule rule = new CodeRule();
		rule.setRuleCode("001");
		rule.setRuleName("test");
		rule.setRulePolicy(RulePolicy.SERIAL);
		codeRuleService.saveCodeRule(rule);
//		CodeRule r = codeRuleService.findOneByCode("001");
//		assertNotNull(r);
	}
}
