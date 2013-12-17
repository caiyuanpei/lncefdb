package com.cathysoft.lncefdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cathysoft.lncefdb.service.SubmitService;

@Controller
public class UpdateController {

	@RequestMapping(
			value="update",
			method=RequestMethod.GET)
	public String showUpdatePage(
			@RequestParam String lnc,
			@RequestParam String ef,
			Model model) {
		
		model.addAttribute("lnc", lnc);
		model.addAttribute("ef", ef);
		
		return "lncef.update";
	}
	
	@Autowired
	private SubmitService service;
	
	@RequestMapping(
			value="doupdate",
			method=RequestMethod.POST)
	public String doSubmit(
			@RequestParam String lnc, 
			@RequestParam String ef, 
			@RequestParam String status,
			@RequestParam String pubmedid,
			@RequestParam String email, 
			@RequestParam String descrip) {
		
		service.updateTerm(lnc, ef, status, pubmedid, email, descrip);
		
		return "redirect:/search";
	}
}
