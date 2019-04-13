package com.bmi.app.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "etat")
public class Etat implements Serializable {

	@EmbeddedId
	EtatId  etatId;

	public Etat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Etat(com.bmi.app.entity.EtatId etatId) {
		super();
		etatId = etatId;
	}

	public EtatId getEtatId() {
		return etatId;
	}

	public void setEtatId(EtatId etatId) {
		etatId = etatId;
	}

	
	


}
