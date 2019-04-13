package com.bmi.controller.app;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.Filter;
import com.bmi.app.entity.Rapport;
import com.bmi.app.entity.Utilisateur;
import com.bmi.app.repository.EtatRepository;
import com.bmi.app.repository.FilterRepository;
import com.bmi.app.repository.RapportRepository;
import com.bmi.app.repository.SujetRepository;
import com.bmi.app.repository.UtilisateurRepository;
import com.bmi.rapport.entity.Bmiequ;
import com.bmi.rapport.repository.BmiequRepository;
import com.bmi.service.app.ServicerResponseApi;

@RestController
public class ControllerApiApp {
//-------------------> JPA DataBase App 
	@Autowired
	UtilisateurRepository utilisateurRepository;
//-------------------> JPA DataBase Repport
	@Autowired
	BmiequRepository bmiequRepository;

	/****************************
	 ******** >CRUD users<*********
	 *****************************/
//------------------->BEGIN ADD user  <-------------------//
	@PostMapping("/saveUser")
	public ResponseEntity<Object> addUser(@RequestBody Utilisateur utilisateur) {

		if (utilisateur.getUtilisateurType() == null) {
			utilisateur.setUtilisateurType("USER");
		}
		utilisateurRepository.save(utilisateur);
		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(null);
		response.setStatus("success");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
//------------------->END  ADD user  <-------------------//

//------------------->BEGIN delete user all <-------------------//
	@GetMapping("/delete/{id}")
	void deleteAccount(@PathVariable(name = "id") long utilisateur) {
		utilisateurRepository.deleteById(utilisateur);
	}
//------------------->END  delete user all <-------------------//

//------------------->BEGIN search for user by(mail/F-name/L-name) or all <-------------------//
	@GetMapping(path = { "/Users/{email}", "/Users/" })
	public ResponseEntity<Object> getAllUseres(@PathVariable(name = "email", required = false) Optional<String> email) {
		List<Utilisateur> user;
		utilisateurRepository.findAll();
		if (email.isPresent()) {
			String newEmail = newEmail = email.get();
			user = utilisateurRepository.findLikeMailOrFirstNameOrLastName(newEmail);
		} else {
			user = utilisateurRepository.findAll();
		}
		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(user);
		response.setStatus("success");
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
//------------------->END search for user by(mail/F-name/L-name) or all <-------------------//

//------------------->BEGIN testing both database (db app / db mainta)<-------------------//
	@GetMapping("/testdb1")
	List<Utilisateur> db1() {
		return utilisateurRepository.findAll();
	}

	@GetMapping("/testdb2")
	List<Bmiequ> db2() {
		return bmiequRepository.findAll();
	}
//------------------->END testing both database (db app / db mainta)<-------------------//

	/*******************************************
	 * >BEGIN get all rapport of the 5 subject<*
	 *********************************************/
	@Autowired
	EtatRepository etatRepository;
	@Autowired
	FilterRepository filterRepository;
	@Autowired
	RapportRepository rapportRepository;
	@Autowired
	SujetRepository sujetRepository;
//------------------->get All Repports of Subject Stock

	@GetMapping(path = { "/STOCK" })
	public ResponseEntity<Object> getAllRepportsStock() {

		List<BigInteger > Etats = etatRepository.findByEtatIdSujetType("STOCK");
		List<Rapport> rapports = new ArrayList<Rapport>();

		for (BigInteger  etat : Etats) {
			rapports.add(rapportRepository.findByRapportId(etat.longValue()));
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(rapports);
		response.setStatus("success");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

//------------------->	get All Repports of Subject Travaux
	@GetMapping(path = { "/TRAVAUX" })
	public ResponseEntity<Object> getAllRepportsTravaux() {

		List<BigInteger > Etats = etatRepository.findByEtatIdSujetType("TRAVAUX");
		List<Rapport> rapports = new ArrayList<Rapport>();

		for (BigInteger  etat : Etats) {
			rapports.add(rapportRepository.findByRapportId(etat.longValue()));
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(rapports);
		response.setStatus("success");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

// -------------------> get All Repports of Subject Achat
	@GetMapping(path = { "/ACHAT" })
	public ResponseEntity<Object> getAllRepportsAchat() {

		List<BigInteger > Etats = etatRepository.findByEtatIdSujetType("ACHAT");
		List<Rapport> rapports = new ArrayList<Rapport>();

		for (BigInteger  etat : Etats) {
			rapports.add(rapportRepository.findByRapportId(etat.longValue()));
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(rapports);
		response.setStatus("success");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

//------------------->	get All Repports of Subject Temps
	@GetMapping(path = { "/TEMPS" })
	public ResponseEntity<Object> getAllRepportsTemps() {

		List<BigInteger > Etats = etatRepository.findByEtatIdSujetType("TEMPS");
		List<Rapport> rapports = new ArrayList<Rapport>();

		for (BigInteger  etat : Etats) {
			
			rapports.add(rapportRepository.findByRapportId(etat.longValue()));
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(rapports);
		response.setStatus("success");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

//------------------->	get All Repports of Subject Depence

	@GetMapping(path = { "/DEPENCE" })
	public ResponseEntity<Object> getAllRepportsDepence() {

		List<BigInteger > Etats = etatRepository.findByEtatIdSujetType("DEPENCE");
		List<Rapport> rapports = new ArrayList<Rapport>();

		for (BigInteger  etat : Etats) {
			rapports.add(rapportRepository.findByRapportId(etat.longValue()));
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(rapports);
		response.setStatus("success");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	/****************************************
	 * >END get all rapport of the 5 subject<*
	 ****************************************/
//------------------------------><------------------------------//	
	/****************************************
	 * >BEGIN get all filter for a rapport<**
	 ****************************************/

	@GetMapping(path = { "/filters/{rapportid}" })
	public ResponseEntity<Object> getAllFillter(@PathVariable(name = "rapportid", required = true) long rapportid) {

		List<Filter> filtersList = new ArrayList<Filter>();
		List<Etat> etatsFilters = etatRepository.findByEtatIdRapportId(rapportid);
		for (Etat etat : etatsFilters) {
			filtersList.add(filterRepository.findByFilterId(etat.getEtatId().getFilterId()));
		}

		ServicerResponseApi response = new ServicerResponseApi();
		response.setData(filtersList);
		response.setStatus("success");

		 return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
