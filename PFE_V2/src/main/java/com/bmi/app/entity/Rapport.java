package com.bmi.app.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rapport implements Serializable {

	@Id
	@Column(name="rapport_id")
	private Long rapportId;
	@Column(name="rapport_nom",unique=true)
	private String rapportNom;
	@Column(name="utilisateur_id")
	private Long utilisateurId;
	@Column(name="rapport_url",unique=true)
	private String rapportUrl;
	@Column(name = "date_creation")
	  @Temporal(TemporalType.DATE)
	  private Date dateCreation = new Date();
	
	public Rapport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rapport(Long rapportId, String rapportNom, String rapportUrl) {
		super();
		this.rapportId = rapportId;
		this.rapportNom = rapportNom;
		this.rapportUrl = rapportUrl;
	}
	public Rapport(String string, String string2) {
		this.rapportNom = rapportNom;
		this.rapportUrl = rapportUrl;
	}
	public Long getRapportId() {
		return rapportId;
	}
	public void setRapportId(Long rapportId) {
		this.rapportId = rapportId;
	}
	public String getRapportNom() {
		return rapportNom;
	}
	public void setRapportNom(String rapportNom) {
		this.rapportNom = rapportNom;
	}
	public Long getUtilisateurId() {
		return utilisateurId;
	}
	public void setUtilisateurId(Long utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	public String getRapportUrl() {
		return rapportUrl;
	}
	public void setRapportUrl(String rapportUrl) {
		this.rapportUrl = rapportUrl;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	


}
