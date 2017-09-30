package com.mplus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.mplus.core.entity.CodeRule;
import com.mplus.enums.RulePolicy;
import com.mysql.jdbc.StringUtils;

/**
 * 编码规则工具类
 * 
 * @author wuwj
 *
 */
public class RuleUtil {

	private static AtomicInteger currentValue;

	public RuleUtil() {
		currentValue = new AtomicInteger(0);
	}

	public static String getSerial(CodeRule rule) {
		String serial = null;
		RulePolicy policy = rule.getRulePolicy();
		switch (policy) {
		case SERIAL: serial = serial(rule);
		case DATE: serial = date(rule);
		case DATE_SERIAL: serial = serialDate(rule);
		default: serial = serial(rule);
		}
		return serial;
	}

	private static String serial(CodeRule rule) {
		String prefix = rule.getRulePrefix();
		Integer len = rule.getSerialLength();
		String serial = null;
		if (StringUtils.isNullOrEmpty(prefix)) {
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
			serial = String.format("%0" + len + "d", currentValue.incrementAndGet());
		} else {
			currentValue = new AtomicInteger(
					Integer.valueOf(rule.getCurrentValue().substring(prefix.length())).intValue());
			serial = prefix + String.format("%0" + len + "d", currentValue.incrementAndGet());
		}
		return serial;
	}

	private static String date(CodeRule rule) {
		String serial = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); // yyyyMMddHHmmss
		String date = df.format(new Date());
		String prefix = rule.getRulePrefix();
		if (StringUtils.isNullOrEmpty(prefix)) {
			serial = date;
		} else {
			serial = prefix + date;
		}
		return serial;
	}

	private static String serialDate(CodeRule rule) {
		String serial = null;
		String prefix = rule.getRulePrefix();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		Integer len = rule.getSerialLength();
		if (StringUtils.isNullOrEmpty(prefix)) {
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
			serial = String.format("%0" + len + "d", currentValue.incrementAndGet());
		} else {
			currentValue = new AtomicInteger(
					Integer.valueOf(rule.getCurrentValue().substring(prefix.length())).intValue());
			serial = prefix + date + String.format("%0" + len + "d", currentValue.incrementAndGet());
		}
		serial = StringUtils.isNullOrEmpty(prefix) ? serial : prefix + date + serial;
		return serial;
	}
}
