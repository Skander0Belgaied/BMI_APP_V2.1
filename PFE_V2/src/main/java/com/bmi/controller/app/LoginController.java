package com.bmi.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/logout-success")
	String logout() {
		return "login";
	}

	@RequestMapping("/login")
	String login() {
		return "login";
	}
	
	@RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/cfg-account";
        }
        return "redirect:/sujet";
    }

}
