package com.bmi.app.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Filter implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="filter_id")
	private Long filterId;
	@Column(name="filter_nom",unique=true)
	private String filterNom;
	@Column(name="filter_champ")
	private String filterChamp;
	@Column(name="filter_type",columnDefinition="varchar(255) default 'text'")
	private String filterType;
	
	

	public Filter() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Filter(Long filterId, String filterNom, String filterChamp, String filterType) {
		super();
		this.filterId = filterId;
		this.filterNom = filterNom;
		this.filterChamp = filterChamp;
		
		this.filterType = filterType;
	}



	public Long getFilterId() {
		return filterId;
	}



	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}



	public String getFilterNom() {
		return filterNom;
	}



	public void setFilterNom(String filterNom) {
		this.filterNom = filterNom;
	}



	public String getFilterChamp() {
		return filterChamp;
	}



	public void setFilterChamp(String filterChamp) {
		this.filterChamp = filterChamp;
	}





	public String getFilterType() {
		return filterType;
	}



	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	

}
