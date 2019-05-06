package com.bmi.controller.rapport;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.EtatId;
import com.bmi.app.entity.Filter;
import com.bmi.app.entity.Rapport;
import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.EtatRepository;
import com.bmi.app.repository.FilterRepository;
import com.bmi.app.repository.RapportRepository;
import com.bmi.app.repository.UtilisateurRepository;
import com.bmi.service.rapport.UploadServiceImpl;

@Controller

public class UploadController {
	@Autowired
	UploadServiceImpl uploadServiceImpl;
	@Autowired
	RapportRepository rapportRepository;
	@Autowired
	FilterRepository FilterRepository;
	@Autowired
	EtatRepository etatRepository;
	@Autowired
	UtilisateurRepository utilisateurRepository;
	Filter filter;
	Utilisateur utilisateur;
	Etat etat;
	EtatId etatId;
	Rapport rapport;
	@GetMapping(path = "/addEtat")
	String addreport() {
		return "addEtat";

	}

	// @GetMapping(path = "/upload")
	@PostMapping("/upload") 
	public String singleFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,
			@RequestParam MultiValueMap<String, String> getform, RedirectAttributes redirectAttributes, Model model
	
	) {
		Map<String, Object> FileStatus = new HashMap<String, Object>();
		
		List<Filter> filters = new ArrayList<Filter>();

		FileStatus = uploadServiceImpl.Uploadfile(file);
		long rapportid=Long.parseLong(getform.get("rapportid").get(0));
		String rapportNom=getform.get("rapportName").get(0);
		String sujet=getform.get("sujet").get(0);
		Principal principal= request.getUserPrincipal();
		utilisateur=utilisateurRepository.findByUtilisateurEmail(principal.getName());
		System.out.println(rapportid+"  "+rapportNom+"  "+ sujet+"  "+utilisateur.getUtilisateurId());
		rapport=new Rapport();
		etat=new Etat();
		etatId=new EtatId();
		rapport.setRapportId(rapportid);
		rapport.setRapportNom(rapportNom);
		rapport.setUtilisateurId(utilisateur.getUtilisateurId());
		rapport.setRapportUrl(file.getOriginalFilename());
		etatId.setRapportId(rapportid);
		etatId.setSujetType(sujet);
		//System.out.println(rapportRepository.existsByRapportId(rapportid)+" ---- "+rapportRepository.existsByRapportNom(rapportNom));
		
		
		
		
		if(rapportRepository.existsByRapportId(rapportid)) {
			model.addAttribute("status", "Identifiant '"+rapportid+"' du rapport  existant ");
			return "/addEtat";
		}
		if(rapportRepository.existsByRapportNom(rapportNom)) {
			model.addAttribute("status", "Nom '"+rapportNom+"' du rapport existant ");
			return "/addEtat";
		}
		 if(getform.containsKey("filterNom")) {
		for (int i = 0; i < getform.get("filterNom").size(); i++) 
		{
			filter = new Filter();
			filter.setFilterNom(getform.get("filterNom").get(i));
			filter.setFilterChamp(getform.get("filterChamp").get(i));
			filter.setFilterType(getform.get("filterType").get(i));
			filters.add(filter);
			
			if (!FilterRepository.findByFilterNom(filter.getFilterNom()).isEmpty()) {
				model.addAttribute("status", "Filter '"+filter.getFilterNom()+"' existant");
				return "/addEtat";
			}
		}}
		 if((boolean) FileStatus.get("status")) 
		 { if(getform.containsKey("filtersDisponible")) {
			 for (int i = 0; i < getform.get("filtersDisponible").size(); i++) 
			 {	
				filter=FilterRepository.findByFilterId(Long.parseLong(getform.get("filtersDisponible").get(i)));
				filters.add(filter);
			 }
		 }
			 FilterRepository.saveAll(filters);
			 rapportRepository.save(rapport);
			 if(getform.containsKey("filterNom")||getform.containsKey("filtersDisponible")) {
			 for(Filter f:filters){
			 EtatId etatID=new EtatId(sujet,f.getFilterId(),rapportid);
				Etat e=new Etat();
				e.setEtatId(etatID);
				System.out.println(e.getEtatId().getSujetType());
				etatRepository.save(e);
			 }
			 }else
			 { 
				 EtatId etatID=new EtatId(sujet,(long) -1,rapportid);
					Etat e=new Etat();
					e.setEtatId(etatID);
					System.out.println(e.getEtatId().getSujetType());
					etatRepository.save(e);
			 }
			 model.addAttribute("status",FileStatus.get("msg"));
			 return "redirect:/rapport-Management/delete";
		 }
		 else 
		 {
			model.addAttribute("status", FileStatus.get("msg"));
			return "/addEtat";
		 }
	}

}
