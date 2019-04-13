package com.bmi.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateur implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "utilisateur_id")
	private Long utilisateurId;
	@Column(name = "utilisateur_nom")
	private String utilisateurNom;
	@Column(name = "utilisateur_prenom")
	private String utilisateurPrenom;
	@Column(name = "utilisateur_password")
	private String utilisateurPassword;
	@Column(name = "utilisateur_email")
	private String utilisateurEmail;
	@Column(name = "utilisateur_type")	
	private String utilisateurType;

	public Long getUtilisateurId() {
		return utilisateurId;
	}

	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}

	public String getUtilisateurNom() {
		return utilisateurNom;
	}

	public void setUtilisateurNom(String utilisateurNom) {
		this.utilisateurNom = utilisateurNom;
	}

	public String getUtilisateurPrenom() {
		return utilisateurPrenom;
	}

	public void setUtilisateurPrenom(String utilisateurPrenom) {
		this.utilisateurPrenom = utilisateurPrenom;
	}

	public String getUtilisateurPassword() {
		return this.utilisateurPassword;
	}

	public void setUtilisateurPassword(String utilisateurPassword) {
		this.utilisateurPassword = utilisateurPassword;
	}

	public String getUtilisateurEmail() {
		return this.utilisateurEmail;
	}

	public void setUtilisateurEmail(String utilisateurEmail) {
		this.utilisateurEmail = utilisateurEmail;
	}

	public String getUtilisateurType() {
		return utilisateurType;
	}

	public void setUtilisateurType(String utilisateurType) {
		this.utilisateurType = utilisateurType;
	}

	

}
