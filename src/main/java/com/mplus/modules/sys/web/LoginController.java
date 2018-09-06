package com.mplus.modules.sys.web;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mplus.core.advice.Result;
import com.mplus.enums.ResponseStatus;
import com.mplus.modules.sys.entity.User;

@Controller
@RequestMapping(value = "/api")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String entry() {
		return "login";
	}

	@RequestMapping(value = "/qc_callback", method = RequestMethod.GET)
	public String hello() {
		return "qc_callback";
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody User user, HttpServletRequest request) {
		String host = getRemoteAddr(request);
		String username = user.getUsername();
		String password = user.getPassword();
		// Boolean rememberMe = user.getRememberMe();

		UsernamePasswordToken token = new UsernamePasswordToken(username, password, false, host);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过");
			return Result.sucess(token);
		} else {
			token.clear();
			return Result.failure(ResponseStatus.AUTH_NOT_PASSED.value(), ResponseStatus.AUTH_NOT_PASSED.getDesc());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public Result unauthorized() {
		return Result.failure(ResponseStatus.AUTH_NOT_PASSED.value(), ResponseStatus.AUTH_NOT_PASSED.getDesc());
	}

	/**
	 * 获取客户端IP地址
	 */
	private String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if (ip.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}
}
