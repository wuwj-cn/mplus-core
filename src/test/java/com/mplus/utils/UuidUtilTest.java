package com.mplus.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class UuidUtilTest {

	@Test
	public void testGenerateShortUuid() {
		for(int i = 0, len = 10; i<len; i++){
			new Thread(new Task()).start();;
		}
	}
	
	class Task implements Runnable  {
		int random = new Random().nextInt(10);
		int len = Integer.MAX_VALUE;
		String str = null;
		Set<String> set = new HashSet<String>();
		
		@Override
		public void run() {
			System.out.println("random: " + random + ", start...");
			for(int i = random*len/10; i < (random+1)*len/10; i++){
				str = UuidUtil.generateShortUuid();
				if (!set.add(str)) {
					System.out.println("random: " + random + ", 当前数：" + i + ", 存在重复随机数：" + str);
				}
			}
		}

	}
	
	public static void main(String[] args) {
		for(int i = 0, len = 10; i<len; i++){
			new Thread(new UuidUtilTest().new Task()).start();;
		}
	}

}
