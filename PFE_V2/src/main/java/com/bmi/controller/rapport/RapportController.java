package com.bmi.controller.rapport;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.Rapport;
import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.EtatRepository;
import com.bmi.app.repository.RapportRepository;
import com.bmi.app.repository.UtilisateurRepository;
import com.bmi.service.rapport.SujetRapport;

@Controller
public class RapportController {
	@Autowired
RapportRepository rapportRepository;
	@Autowired
UtilisateurRepository utilisateurRepository;
	@Autowired
EtatRepository etatRepository;
List <Utilisateur> utilisateurs;
List <Rapport> rapports;
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
	@GetMapping("/rapport-Management")
	 String ReportManagement() {
	
		
		return "GestionRapport";
	}
	@GetMapping("/rapport-Management/delete")
	 String ReportManagementShow(Model model) {
		rapports=rapportRepository.findAll();
		Map<Rapport,SujetRapport> map = new LinkedHashMap<Rapport,SujetRapport>(); 
		for(Rapport rapport:rapports) {
			Utilisateur user=utilisateurRepository.getOne(rapport.getUtilisateurId());
			Etat sujet=etatRepository.findByEtatIdRapportId((Long)rapport.getRapportId()).get(0);
			SujetRapport sujetr=new SujetRapport(user,sujet.getEtatId().getSujetType());
		map.put(rapport, sujetr);
		}
		
		model.addAttribute("rapports",map);
	
		
		return "DeleteRapport";
	}
	@GetMapping("/rapport-Management/delete/{id}")
	String ReportDelete(@PathVariable(name="id") Long id,Model model) {
		
		rapports=rapportRepository.findAll();
		Map<Rapport,Utilisateur> map = new LinkedHashMap<Rapport,Utilisateur>(); 
		for(Rapport rapport:rapports) {
		map.put(rapport, utilisateurRepository.getOne(rapport.getUtilisateurId()));
		}
		
		String rapportname=rapportRepository.getOne(id).getRapportNom();
		rapportRepository.deleteById(id);
		model.addAttribute("status","Rapport '"+rapportname+"' supprimée avec succès");
		model.addAttribute("rapports",map);
		return "DeleteRapport";
	}
	@GetMapping("/rapport-Management/recherche")
	String Reportrecherche(@RequestParam MultiValueMap<String, String> request ,Model model) {
		Long rechercheint;
		try
		   {
			rechercheint=Long.parseLong(request.get("recherche").get(0));
		      
		   }
		   catch( Exception e )
		   {
		     rechercheint=new Long(0);
		   }
		if(rapportRepository.findByRapportIdOrRapportName(request.get("recherche").get(0),rechercheint).size()>0) {
		rapports=rapportRepository.findByRapportIdOrRapportName(request.get("recherche").get(0),rechercheint);
		Map<Rapport,SujetRapport> map = new LinkedHashMap<Rapport,SujetRapport>(); 
		for(Rapport rapport:rapports) {
			Utilisateur user=utilisateurRepository.getOne(rapport.getUtilisateurId());
			Etat sujet=etatRepository.findByEtatIdRapportId(rapport.getRapportId()).get(0);
			SujetRapport sujetr=new SujetRapport(user,sujet.getEtatId().getSujetType());
		map.put(rapport, sujetr);
		}
		
		model.addAttribute("rapports",map);
		}else {
		model.addAttribute("rapports",null);
		}
		return "DeleteRapport";
	}
	
}
