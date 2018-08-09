package com.mplus.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.mplus.modules.sys.entity.User;

public class EncryptUtil {
	// 随机数生成器
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	// 指定散列算法为md5
	public static final String algorithmName = "MD5";
	// 散列迭代次数
	public static final int hashIterations = 5;

	/**
	 * 生成随机盐值对密码进行加密
	 * @param user 登录识别串（用户名）
	 * @return
	 */
	public static User encrypt(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String encryptPwd = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
		user.setPassword(encryptPwd);
		return user;
	}
}
