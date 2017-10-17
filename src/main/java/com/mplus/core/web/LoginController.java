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
import org.springframework.web.bind.annotation.ResponseBody;

import com.mplus.core.advice.Result;
import com.mplus.core.entity.User;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/")
    public String entry() {
        return "login";
    }

	@RequestMapping(value = "/qc_callback")
	public String hello() {
		return "qc_callback";
	}

	@ResponseBody
	@RequestMapping(value = "/login")
	public Result login(@RequestBody User user, HttpServletRequest request) {
		String host = request.getRemoteHost();
		String username = user.getUsername();
		String password = user.getPassword();
//		Boolean rememberMe = user.getRememberMe();
		
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
			return Result.failure("用户[" + username + "]登录认证未通过");
		}
	}
	
	@ResponseBody
	@RequestMapping("/403")
	public Result unauthorized() {
		return Result.failure(null);
	}

}
