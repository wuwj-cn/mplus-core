package com.mplus.utils;

import com.mplus.core.entity.CodeRule;

/**
 * 编码规则工具类
 * @author wuwj
 *
 */
public class RuleUtil {

	public static String serial(CodeRule rule) {
		int currentValue = Integer.valueOf(rule.getCurrentValue()).intValue();
		Integer len = rule.getSerialLength();
		return String.format("%0"+len+"d", currentValue + 1);
	}
}
