package com.mplus.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mplus.core.entity.CodeRule;

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

	public static String serial(CodeRule rule) {
		String serial = null;
		try {
			lock.readLock().lock();  
			currentValue = new AtomicInteger(Integer.valueOf(rule.getCurrentValue()).intValue());
			Integer len = rule.getSerialLength();
			serial = String.format("%0"+len+"d", currentValue.getAndIncrement());
		} catch(Exception e) {
			e.printStackTrace(); 
		} finally {
			lock.readLock().unlock(); 
		}
		return serial;
	}
}
