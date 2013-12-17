package com.cathysoft.lncefdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cathysoft.lncefdb.service.SubmitService;

@Controller
public class SubmitController {

	@RequestMapping(
			value="submit",
			method=RequestMethod.GET)
	public String showSubmitPage() {
		
		return "lncef.submit";
	}
	
	@Autowired
	private SubmitService service;
	
	@RequestMapping(
			value="submit",
			method=RequestMethod.POST)
	public String doSubmit(
			@RequestParam String lnc, 
			@RequestParam String ef, 
			@RequestParam String status,
			@RequestParam String pubmedid,
			@RequestParam String email, 
			@RequestParam String descrip,
			RedirectAttributes attr) {
		
		/* 2013.12.16 update for lnc, ef's server-side validate */
		if(lnc == null || lnc.trim().length()==0) {
			attr.addFlashAttribute("substatus", "Lnc RNA must not be empty!");
			return "redirect:/submit";
		}
		if(ef == null || ef.trim().length()==0) {
			attr.addFlashAttribute("substatus", "Environmental factors must not be empty!");
			return "redirect:/submit";
		}
		/* update end */
		
		service.submitTerm(lnc, ef, status, pubmedid, email, descrip);
		attr.addFlashAttribute("substatus", "Submit Success.");
		
		return "redirect:/submit";
	}
}
