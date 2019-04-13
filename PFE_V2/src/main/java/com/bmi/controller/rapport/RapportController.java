package com.bmi.controller.rapport;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.UtilisateurRepository;

@Controller
public class RapportController {

	@GetMapping("/Filter/{sujet}")
	 String getpage1(@PathVariable(name="sujet") String sujet) {
		if(sujet.equals("ACHAT")) {
			return "Sujet/Achat";
		}
		if(sujet.equals("STOCK")) {
			return "Sujet/Stock";
		}
		if(sujet.equals("TRAVAUX")) {
			return "Sujet/Travaux";
		}
		if(sujet.equals("TEMPS")) {
			return "Sujet/Temps";
		}
		if(sujet.equals("DEPENCE")) {
			return "Sujet/Depence";
		}
		
		return "sujet";
	}
}
