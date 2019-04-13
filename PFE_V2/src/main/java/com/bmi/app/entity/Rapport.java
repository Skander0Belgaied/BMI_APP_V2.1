package com.bmi.app.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Rapport implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="rapport_id")
	private Long rapportId;
	@Column(name="rapport_nom",unique=true)
	private String rapportNom;
	@Column(name="rapport_url",unique=true)
	private String rapportUrl;
	
	
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
	public Long getRapportid() {
		return rapportId;
	}
	public void setRapportid(Long rapportid) {
		this.rapportId = rapportid;
	}
	public String getRapportNom() {
		return rapportNom;
	}
	public void setRapportNom(String rapportNomd) {
		this.rapportNom = rapportNomd;
	}
	public String getRapportUrl() {
		return rapportUrl;
	}
	public void setRapportUrl(String rapportUrl) {
		this.rapportUrl = rapportUrl;
	}
	
}
