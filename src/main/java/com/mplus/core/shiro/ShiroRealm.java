package com.mplus.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mplus.core.entity.Permission;
import com.mplus.core.entity.Role;
import com.mplus.core.entity.User;
import com.mplus.core.service.UserService;

@Component
public class ShiroRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("权限配置-->ShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		for (Role role : user.getRoles()) {
			authorizationInfo.addRole(role.getRoleCode());
			for (Permission p : role.getPermissions()) {
				authorizationInfo.addStringPermission(p.getPermissionCode());
			}
		}
		return authorizationInfo;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("ShiroRealm.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User user = userService.findByUsername(username);
		if (null == user) {
			return null;
		} 
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

}
