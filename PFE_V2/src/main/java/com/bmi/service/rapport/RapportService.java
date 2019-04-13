package com.bmi.service.rapport;

import java.util.List;

import com.bmi.app.entity.Filter;

public interface RapportService {
	
	List<Filter> GetAllFiltersRapportX(long RapportId);	
	Boolean FilterExistInFilterRapport(List<Filter> rapportFilter,List<Filter> allRapportFilter);
	
}
