package com.bmi.controller.rapport;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmi.app.entity.Filter;
import com.bmi.app.repository.FilterRepository;

@Controller
public class GenerateRapportsController {
	@Autowired
	FilterRepository filterRepository;
	
	@GetMapping("/generate-Rapport")
	String show(@RequestParam MultiValueMap<String, String> queryMap,Model model,HttpServletRequest req)
	{

		   //   List<String> itemIds = queryMap.get("Filters-rapport");

		     
		if(req.getQueryString().contains("Filters-rapport")){

		      List<Filter> filtersList = new ArrayList<Filter>();
		     List<String> itemIds=queryMap.get("Filters-rapport");
		      for (String itemId : itemIds) {
		    	  filtersList.add(filterRepository.findByFilterId(Long.parseLong(itemId)));
		      } 
		      model.addAttribute("filtersList",filtersList);
		}else
		{
			model.addAttribute("filtersList",null);
		}
		      String rapportId=queryMap.get("selectrapport").get(0);
		      model.addAttribute("rapportId",rapportId);
		      
		return "Rapport-Filter";
		
	}

}
