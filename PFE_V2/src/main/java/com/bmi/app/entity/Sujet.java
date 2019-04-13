package com.bmi.app.entity;

import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Sujet implements Serializable {
	
	@Id
	@Column(name="sujet_type",unique=true)
	private String sujetType;
	@Column(name="sujet_nom",unique=true)
	private String sujetNom;
	
	
	public Sujet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sujet(String sujetType, String sujetNom) {
		super();
		this.sujetType = sujetType;
		this.sujetNom = sujetNom;
	}
	public String getSujetType() {
		return sujetType;
	}
	public void setSujetType(String sujetType) {
		this.sujetType = sujetType;
	}
	public String getSujetNom() {
		return sujetNom;
	}
	public void setSujetNom(String sujetNom) {
		this.sujetNom = sujetNom;
	}
	

}
