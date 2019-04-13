package com.bmi.controller.app;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmi.app.repository.UtilisateurRepository;

@Controller
public class HomeController {
@Autowired
UtilisateurRepository utilisateurRepository;
	@GetMapping("/")
	String home() {
		return "index";
	}
	@GetMapping("/index")
	String test(Principal principal) {
		
		return "index";
	}
	@GetMapping("/sujet")
	String sujet() {
		return "sujet";
	}
	
	@GetMapping(path = "/cfg-account")
	String configUsers() {
		return "cfguser";
	}
	@GetMapping(path = "/cfg-database-system")
	String configDatabaseSystem() {
		return "settings";
	}


}
