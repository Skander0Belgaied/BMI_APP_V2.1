package com.bmi.service.rapport;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.Utilisateur;

public class SujetRapport {

	public Utilisateur utilisateur;
	public String sujet;
	public SujetRapport(Utilisateur utilisateur, String sujet) {
		super();
		this.utilisateur = utilisateur;
		this.sujet = sujet;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	
	
	

}
