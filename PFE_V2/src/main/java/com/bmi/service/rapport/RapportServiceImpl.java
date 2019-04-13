package com.bmi.service.rapport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmi.app.entity.Etat;
import com.bmi.app.entity.Filter;
import com.bmi.app.repository.EtatRepository;
import com.bmi.app.repository.FilterRepository;
import com.bmi.app.repository.RapportRepository;
@Service
public class RapportServiceImpl implements RapportService {
@Autowired
EtatRepository EtatRepository;
@Autowired
RapportRepository rapportRepository;
@Autowired
FilterRepository filtersRepository;

	@Override
	public List<Filter> GetAllFiltersRapportX(long rapportId) {
		List<Etat> etats=EtatRepository.findByEtatIdRapportId(rapportId);
		List<Filter> filters=new ArrayList<Filter>();
		for(Etat etat : etats) {
			filters.add(filtersRepository.findByFilterId(etat.getEtatId().getFilterId()));
		}
		return filters;
	}

	@Override
	public Boolean FilterExistInFilterRapport(List<Filter> rapportFilter, List<Filter> allRapportFilter) {
		
		for(Filter Rfilters : allRapportFilter)
		{
			rapportFilter.contains(Rfilters);
		}
		
		
		return null;
	}

}
