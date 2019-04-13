package com.bmi.rapport.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bmi.rapport.entity.Bmiequ;

@Repository
public interface BmiequRepository extends JpaRepository<Bmiequ, Long> {

	@Query(nativeQuery=true,value="select dbo.totale()")
	Double findbyTotal();
	
}

