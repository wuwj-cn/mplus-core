package com.mplus.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class UuidUtilTest {

	@Test
	public void testGetShortUuid() {
		Runnable run = null;
		int maxValue = Integer.MAX_VALUE;
		run = () -> {
			int random = new Random().nextInt(10);
			Set<String> set = new HashSet<String>();
			int low = maxValue / 10 * random;
			int high = maxValue / 10 * (random + 1);
			System.out.println(Thread.currentThread().getName() + ", random: " + random + ", 取值区间：" + low + "-" + high
					+ ", 个数：" + (high - low) + ", start...");
			for (int ix = low; ix < high; ix++) {
				String str = UuidUtil.getShortUuid();
				System.out.println(Thread.currentThread().getName() + ", random: " + random + ", uuid：" + str);
				if (!set.add(str)) {
					System.out.println(Thread.currentThread().getName() + ", random: " + random + ", 当前数：" + ix
							+ ", 存在重复随机数：" + str);
					return;
				}
			}
			System.out.println(Thread.currentThread().getName() + ", random: " + random + ", end...");
		};

		for (int i = 0, length = 3; i < length; i++) {
			new Thread(run).start();
		}
	}

	public static void main(String[] args) {
		new UuidUtilTest().testGetShortUuid();
	}

}
