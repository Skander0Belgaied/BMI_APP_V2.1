package com.bmi.app.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class EtatId implements Serializable {

	@Column(name = "sujet_type")
	private String sujetType;
	
	@Column(name = "filter_id")
	private Long filterId;
	
	@Column(name = "rapport_id")
	private Long rapportId;

	
	public EtatId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EtatId(String sujetType, Long filterId, Long rapportId) {
		super();
		this.sujetType = sujetType;
		this.filterId = filterId;
		this.rapportId = rapportId;
	}

	public String getSujetType() {
		return sujetType;
	}

	public void setSujetType(String sujetType) {
		this.sujetType = sujetType;
	}

	public Long getFilterId() {
		return filterId;
	}

	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}

	public Long getRapportId() {
		return rapportId;
	}

	public void setRapportId(Long rapportId) {
		this.rapportId = rapportId;
	}


}
