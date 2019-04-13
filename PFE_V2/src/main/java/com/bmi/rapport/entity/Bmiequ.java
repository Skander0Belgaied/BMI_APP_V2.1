package com.bmi.rapport.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity(name = "VPER_BMIEQU")
@Immutable
/*@Subselect("SELECT TOP 1 BT.ID_NUMBT AS ID_NUMBT_BT," + 
		"ISNULL(BT.ID_NUMEQU,0)AS ID_NUMEQU_BT," + 
		"EQU.ST_CODLON AS ST_CODLON_EQU ," + 
		"ISNULL(BT.ID_CODTRA,'type travail inconnu')AS ID_CODTRA_BT," + 
		"ISNULL(EQU.ST_DESEQU,'Désignation EQU inconnu') AS ST_DES_EQU," + 
		"CAT.ID_CODCAT AS ID_CODCAT_CAT," + 
		"ISNULL(CAT.ST_DES,'Désignation EQU inconnu') AS ST_DES_CAT," + 
		"ISNULL(GRO.ID_NUMGRO,0) as ID_NUMGRO_GRO," + 
		"ISNULL(GRO.ST_DES,'groupe inconnu') as ST_DES_GRO," + 
		"ISNULL(BTSYN.NU_DURARR,0) AS Duree_arret," + 
		"ISNULL(BTSYN.NU_TOTMAT,0) AS Total_matiere," + 
		"ISNULL(BTSYN.NU_TOTEXT,0) AS Total_exterieur," + 
		"ISNULL(BTSYN.NU_TOTMO,0) AS Total_main_oeuvre," + 
		"(ISNULL(BTSYN.NU_TOTMAT,0) + ISNULL(BTSYN.NU_TOTEXT,0) + ISNULL(BTSYN.NU_TOTMO,0)) AS Total_cout ," + 
		"BTSYN.DT_INTDEB AS Date_intervention" + 
		"FROM            BTSYN INNER JOIN" + 
		"                         BT ON BT.ID_NUMBT = BTSYN.ID_NUMBT LEFT JOIN" + 
		"                         TEM ON BT.ID_NUMBT = TEM.ID_NUMBT LEFT JOIN" + 
		"                         CAT ON TEM.ID_CODCAT = CAT.ID_CODCAT LEFT JOIN" + 
		"                         EQU ON BT.ID_NUMEQU = EQU.ID_NUMEQU LEFT JOIN" + 
		"                         GRO ON EQU.ID_NUMGRO = GRO.ID_NUMGRO" + 
		"					 ")*/
@Table(name="VPER_BMIEQU")
public class Bmiequ implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_NUMBT_BT")
	long IdNumbtBt=0;
	@Column(name = "ID_NUMEQU_BT")
	long IdNumequBt=0;
	@Column(name = "ST_CODLON_EQU")
	String StCodlongEqu;
	@Column(name = "ID_CODTRA_BT")
	String IdCodtraBt;
	@Column(name = "ST_DES_EQU")
	String StDesEqu;
	@Column(name = "ID_CODCAT_CAT")
	String IdCodcatCat;
	@Column(name = "ST_DES_CAT")
	String StDesCat;
	@Column(name = "ID_NUMGRO_GRO")
	long IdNumgroGro=0;
	@Column(name = "ST_DES_GRO")
	String StDesGro;
	@Column(name = "Duree_arret")
	double DureeArret=0;
	@Column(name = "Total_matiere")
	double TotalMatiere=0;
	@Column(name = "Total_exterieur")
	double TotalExterieur=0;
	@Column(name = "Total_main_oeuvre")
	double TotalmainOeuvre=0;
	@Column(name = "Total_cout")
	double Total_cout;
	@Column(name = "Date_intervention")
	Date DateIntervention;
	public long getIdNumbtBt() {
		return IdNumbtBt;
	}
	public void setIdNumbtBt(long idNumbtBt) {
		IdNumbtBt = idNumbtBt;
	}
	public long getIdNumequBt() {
		return IdNumequBt;
	}
	public void setIdNumequBt(long idNumequBt) {
		IdNumequBt = idNumequBt;
	}
	public String getStCodlongEqu() {
		return StCodlongEqu;
	}
	public void setStCodlongEqu(String stCodlongEqu) {
		StCodlongEqu = stCodlongEqu;
	}
	public String getIdCodtraBt() {
		return IdCodtraBt;
	}
	public void setIdCodtraBt(String idCodtraBt) {
		IdCodtraBt = idCodtraBt;
	}
	public String getStDesEqu() {
		return StDesEqu;
	}
	public void setStDesEqu(String stDesEqu) {
		StDesEqu = stDesEqu;
	}
	public String getIdCodcatCat() {
		return IdCodcatCat;
	}
	public void setIdCodcatCat(String idCodcatCat) {
		IdCodcatCat = idCodcatCat;
	}
	public String getStDesCat() {
		return StDesCat;
	}
	public void setStDesCat(String stDesCat) {
		StDesCat = stDesCat;
	}
	public long getIdNumgroGro() {
		return IdNumgroGro;
	}
	public void setIdNumgroGro(long idNumgroGro) {
		IdNumgroGro = idNumgroGro;
	}
	public String getStDesGro() {
		return StDesGro;
	}
	public void setStDesGro(String stDesGro) {
		StDesGro = stDesGro;
	}
	public double getDureeArret() {
		return DureeArret;
	}
	public void setDureeArret(double dureeArret) {
		DureeArret = dureeArret;
	}
	public double getTotalMatiere() {
		return TotalMatiere;
	}
	public void setTotalMatiere(double totalMatiere) {
		TotalMatiere = totalMatiere;
	}
	public double getTotalExterieur() {
		return TotalExterieur;
	}
	public void setTotalExterieur(double totalExterieur) {
		TotalExterieur = totalExterieur;
	}
	public double getTotalmainOeuvre() {
		return TotalmainOeuvre;
	}
	public void setTotalmainOeuvre(double totalmainOeuvre) {
		TotalmainOeuvre = totalmainOeuvre;
	}
	public double getTotalCout() {
		return Total_cout;
	}
	public void setTotalCout(double totalCout) {
		this.Total_cout = totalCout;
	}
	public Date getDateIntervention() {
		return DateIntervention;
	}
	public void setDateIntervention(Date dateIntervention) {
		DateIntervention = dateIntervention;
	}

	
}
