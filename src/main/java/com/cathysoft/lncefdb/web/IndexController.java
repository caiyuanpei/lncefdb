package com.cathysoft.lncefdb.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cathysoft.lncefdb.service.DateFormatService;
import com.cathysoft.lncefdb.service.StatisticsService;

@Controller
public class IndexController {

	@Autowired
	private StatisticsService service;
	
	@Autowired
	private DateFormatService service2;
	
	@RequestMapping(
			value  = "index",
			method = RequestMethod.GET )
	public String showIndexPage(Model model) {
		model.addAttribute("lnc", service.statisticsLncRnas());
		model.addAttribute("ef",  service.statisticsEnvironmentFactors());
		model.addAttribute("predict", service.statisticsPredictedFdrs());
		model.addAttribute("experiment", service.statisticsExperimentFdrs());
		model.addAttribute("today", service2.format(new Date()));
		return "lncef.index";
	}	
}
