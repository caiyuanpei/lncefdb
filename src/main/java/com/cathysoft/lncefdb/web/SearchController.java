package com.cathysoft.lncefdb.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cathysoft.lncefdb.service.SearchService;

@Controller
public class SearchController {

	@RequestMapping(
			value="search",
			method=RequestMethod.GET)
	public String showSearchPage() {
		
		return "lncef.search";
	}
	
	@Autowired
	private SearchService service;
	
	@RequestMapping(
			value="search",
			method=RequestMethod.POST)
	public String doSearch(
			@RequestParam boolean logic1,
			@RequestParam String lnc, 
			@RequestParam boolean exact1,
			@RequestParam boolean logic2,
			@RequestParam String ef, 
			@RequestParam boolean exact2,
			@RequestParam boolean logic3,
			@RequestParam String status,
			Model model) {
		
		List<Map<String, Object>> list = service.search(logic1, lnc, exact1, logic2, ef, exact2, logic3, status);
		model.addAttribute("search_result", list);
		
		return "lncef.search";
	}
	
}