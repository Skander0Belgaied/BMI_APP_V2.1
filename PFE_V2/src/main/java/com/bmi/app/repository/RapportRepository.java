package com.bmi.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.app.entity.Rapport;
@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {
	boolean existsByRapportNom(String rapportNom);
	boolean existsByRapportId(Long rapportid);
	Rapport findByRapportNom(String rapportNom);
	Rapport findByRapportId(Long rapportid);
}
