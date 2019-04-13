package com.bmi.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.app.entity.Rapport;
@Repository
public interface RapportRepository extends JpaRepository<Rapport, Long> {


	Rapport findByRapportId(Long rapportid);
}
