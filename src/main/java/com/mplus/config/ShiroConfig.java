package com.mplus.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mplus.core.shiro.RetryLimitHashedCredentialsMatcher;
import com.mplus.core.shiro.ShiroRealm;
import com.mplus.utils.EncryptUtil;

@Configuration
public class ShiroConfig {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
//	@Bean
//	public FilterRegistrationBean delegatingFilterProxy(){
//	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//	    DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//	    proxy.setTargetFilterLifecycle(true);
//	    proxy.setTargetBeanName("shiroFilter");
//	    filterRegistrationBean.setFilter(proxy);
//	    return filterRegistrationBean;
//	}
//	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		logger.info("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/", "anon");
		//登录请求加入到匿名访问，否则将因FormAuthenticationFilter.onAccessDenied处理时发生302跳转
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/index", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/api/", "anon");
		filterChainDefinitionMap.put("/api/login", "anon");
		filterChainDefinitionMap.put("/api/index", "anon");
		
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		
		//配置记住我或认证通过可以访问的地址
//        filterChainDefinitionMap.put("/index", "user");
		// <!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/api/");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/activiti/processes");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	@Bean
	public ShiroRealm shiroRealm() {
		ShiroRealm realm = new ShiroRealm();
		realm.setCacheManager(ehCacheManager());
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		securityManager.setCacheManager(ehCacheManager());
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 凭证匹配器
	 * 
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		RetryLimitHashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
		hashedCredentialsMatcher.setHashAlgorithmName(EncryptUtil.HASH_ALGORITHM);// 散列算法
		hashedCredentialsMatcher.setHashIterations(EncryptUtil.HASH_INTERATIONS);// 散列的次数
		return hashedCredentialsMatcher;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 记住我cookie
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		// <!-- 记住我cookie生效时间 ,单位秒;-->
		cookie.setMaxAge(60);
		return cookie;
	}

	/**
	 * cookie管理对象
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager mamager = new CookieRememberMeManager();
		mamager.setCookie(rememberMeCookie());
		return mamager;
	}
}
