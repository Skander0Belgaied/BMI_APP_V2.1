package com.bmi.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.app.entity.Filter;
@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
	
	Filter findByFilterId(Long filterId);

}
