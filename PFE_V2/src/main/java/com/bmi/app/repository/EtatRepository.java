package com.bmi.app.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.EtatId;

@Repository
public interface EtatRepository extends JpaRepository<Etat, EtatId>{
	@Query(value="select DISTINCT rapport_id from etat where sujet_type=?1",nativeQuery=true)
	List<BigInteger> findByEtatIdSujetType(String sujetType);
//	@Query(value="select * from etat e where rapport_id=?1",nativeQuery=true)
	List<Etat> findByEtatIdRapportId(Long rapportId);
	//List<Etat> findBySujetType(String sujetType);
	//List<Etat> findByRapportId(Long rapportId);
	
}
