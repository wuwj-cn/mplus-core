package com.mplus.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.modules.sys.entity.CodeRule;
import com.mplus.modules.sys.service.CodeRuleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RuleUtilTest {

	@Autowired
	private CodeRuleService codeRuleService;
	
	@Test
	public void testSerial() {
		CodeRule rule = codeRuleService.findOneByCode("SERIAL_DATE");
		String serial = RuleUtil.getSerial(rule);
		assertNotNull(serial);
	}
	
}
