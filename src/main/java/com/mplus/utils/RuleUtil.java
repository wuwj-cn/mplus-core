package com.mplus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mplus.core.entity.CodeRule;
import com.mysql.jdbc.StringUtils;

/**
 * 编码规则工具类
 * @author wuwj
 *
 */
public class RuleUtil {
	
	private static AtomicInteger currentValue; 
	private static ReentrantReadWriteLock lock = null;  
	
	public RuleUtil() {
		currentValue = new AtomicInteger(0);
		lock = new ReentrantReadWriteLock();  
	}

	public static String get(CodeRule rule) {
		currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
		Integer len = rule.getSerialLength();
		String serial = String.format("%0"+len+"d", currentValue.incrementAndGet());
		String prefix = rule.getRulePrefix();
		serial = StringUtils.isNullOrEmpty(prefix) ? serial : prefix + serial;
		return serial;
	}
	
	public static String getByDate(CodeRule rule) {
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		String serial = df.format(new Date());
		String prefix = rule.getRulePrefix();
		serial = StringUtils.isNullOrEmpty(prefix) ? serial : prefix + serial;
		return serial;
	}
	
	public static String getBySerialAndDate(CodeRule rule) {
		currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
		Integer len = rule.getSerialLength();
		String serial = String.format("%0"+len+"d", currentValue.incrementAndGet());
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		String prefix = rule.getRulePrefix();
		serial = StringUtils.isNullOrEmpty(prefix) ? serial : prefix + date + serial;
		return serial;
	}
}
