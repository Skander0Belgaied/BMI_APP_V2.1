package com.bmi.controller.app;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.UtilisateurRepository;

@Controller
public class HomeController {
@Autowired
UtilisateurRepository utilisateurRepository;
Utilisateur utilisateur;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
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

	
	@GetMapping(path ="/Profil")
	String Profile(Model model,HttpServletRequest request,@RequestParam MultiValueMap<String, String> updateprofile ) {
		Principal principal= request.getUserPrincipal();
		utilisateur =utilisateurRepository.findByUtilisateurEmail(principal.getName());
		System.out.println(request.toString());
		model.addAttribute("user",utilisateur);
		return "profil";
	}
	@PostMapping("/updateProfile")
    public String updateutilisateur(HttpServletRequest request,@RequestParam MultiValueMap<String, String> updateprofile ,Model model) {
		Principal principal= request.getUserPrincipal();
		utilisateur =utilisateurRepository.findByUtilisateurEmail(principal.getName());
		utilisateur.setUtilisateurNom(updateprofile.get("Nom").get(0));
		utilisateur.setUtilisateurPrenom(updateprofile.get("Prenom").get(0));
		utilisateurRepository.save(utilisateur);
		model.addAttribute("user",utilisateur);
		model.addAttribute("status","profil mis à jour avec succès");
        return "profil";
	}
	@PostMapping("/changePassword")
    public String changePassword(HttpServletRequest request,@RequestParam MultiValueMap<String, String> password ,Model model) throws Exception {
		Principal principal= request.getUserPrincipal();
		utilisateur =utilisateurRepository.findByUtilisateurEmail(principal.getName());
		utilisateur.setUtilisateurPassword(bCryptPasswordEncoder.encode(password.get("password").get(0)));
		utilisateurRepository.save(utilisateur);
		model.addAttribute("user",utilisateur);
		model.addAttribute("status","profil mis à jour avec succès");
        return "profil";
	}
	
	

}
