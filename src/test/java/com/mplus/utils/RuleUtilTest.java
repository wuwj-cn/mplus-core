package com.mplus.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mplus.core.entity.CodeRule;
import com.mplus.core.service.CodeRuleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RuleUtilTest {

	@Autowired
	private CodeRuleService codeRuleService;
	
	@Test
	public void testSerial() {
		CodeRule rule = codeRuleService.findOneByCode("001");
		String serial = RuleUtil.serial(rule);
		assertArrayEquals(new Object[]{"00001"}, new Object[]{serial});
	}

}
