package com.mplus.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/qc_callback")
    public String hello() {
        return "qc_callback";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
}
