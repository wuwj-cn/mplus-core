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
		String prefix = rule.getRulePrefix();
		Integer len = rule.getSerialLength();
		String serial = null;
		if(StringUtils.isNullOrEmpty(prefix)) {
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
			serial = String.format("%0"+len+"d", currentValue.incrementAndGet());
		} else {
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue().substring(prefix.length())).intValue());
			serial = prefix + String.format("%0"+len+"d", currentValue.incrementAndGet());
		}
		return serial;
	}
	
	public static String getByDate(CodeRule rule) {
		String serial = null;
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd"); //yyyyMMddHHmmss
		String date = df.format(new Date());
		String prefix = rule.getRulePrefix();
		if(StringUtils.isNullOrEmpty(prefix)) {
			serial = date;
		} else {
			serial = prefix + date;
		}
		return serial;
	}
	
	public static String getBySerialAndDate(CodeRule rule) {
		String serial = null;
		String prefix = rule.getRulePrefix();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		Integer len = rule.getSerialLength();
		if(StringUtils.isNullOrEmpty(prefix)) {
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
			serial = String.format("%0"+len+"d", currentValue.incrementAndGet());
		} else {
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue().substring(prefix.length())).intValue());
			serial = prefix + date + String.format("%0"+len+"d", currentValue.incrementAndGet());
		}
		serial = StringUtils.isNullOrEmpty(prefix) ? serial : prefix + date + serial;
		return serial;
	}
}
