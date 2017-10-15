package com.mplus.core.web;

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
import com.mplus.core.entity.User;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/qc_callback")
	public String hello() {
		return "qc_callback";
	}

	@ResponseBody
	@RequestMapping(value = "/login")
	public Result login(@RequestBody User user, HttpServletRequest request) {
		String host = request.getRemoteHost();
		String username = user.getUsername();
		String password = user.getPassword();
		Boolean rememberMe = user.getRememberMe();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe, host);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过");
			return Result.sucess(token);
		} else {
			token.clear();
			return Result.failure(null);
		}
	}
	
	@ResponseBody
	@RequestMapping("/403")
	public Result unauthorized() {
		return Result.failure(null);
	}

}
