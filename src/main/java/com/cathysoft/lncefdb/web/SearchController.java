package com.cathysoft.lncefdb.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			value="dosearch",
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
			RedirectAttributes attr) {
		
		List<Map<String, Object>> list = service.search(logic1, lnc, exact1, logic2, ef, exact2, logic3, status);
		
		attr.addFlashAttribute("logic1", logic1);
		attr.addFlashAttribute("lnc", lnc);
		attr.addFlashAttribute("exact1", exact1);
		attr.addFlashAttribute("logic2", logic2);
		attr.addFlashAttribute("ef", ef);
		attr.addFlashAttribute("exact2", exact2);
		attr.addFlashAttribute("logic3", logic3);
		attr.addFlashAttribute("status", status);
		
		attr.addFlashAttribute("showresult", true);
		
		attr.addFlashAttribute("search_result", list);
		
		return "redirect:/search";
	}
	
}