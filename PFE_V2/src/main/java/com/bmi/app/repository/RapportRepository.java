package com.bmi.app.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bmi.app.entity.Rapport;
@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
	boolean existsByRapportNom(String rapportNom);
	boolean existsByRapportId(Long rapportid);
	Rapport findByRapportNom(String rapportNom);
	Rapport findByRapportId(Long rapportid);
	@Query(value = "SELECT * FROM rapport WHERE rapport_id LIKE CONCAT('%',?2,'%') OR rapport_nom LIKE CONCAT('%',?1,'%')",nativeQuery = true)
	List<Rapport> findByRapportIdOrRapportName(String rechercheString,Long rechercheint);
}
