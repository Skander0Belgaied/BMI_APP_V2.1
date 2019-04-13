package com.bmi.controller.app;

import java.security.Principal;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.UtilisateurRepository;
import com.bmi.service.app.UtilisateurDetails;

@Controller
public class TestController {
	@Autowired
	UtilisateurRepository u;
	/*@RequestMapping("/t")
	@ResponseBody
	public String welcome(Authentication authentication) {
		UtilisateurDetails utilisateurDetails = (UtilisateurDetails) authentication.getPrincipal();
		return "User has authorities: " + utilisateurDetails.getUtilisateur().getUtilisateurNom();
	      
	}*/
	
	@RequestMapping("/addEtat")
	public String welcome() {
		return "addEtat";
	      
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/table")
	public String test() {
		return "tabetry";
	      
	}
	
	

}