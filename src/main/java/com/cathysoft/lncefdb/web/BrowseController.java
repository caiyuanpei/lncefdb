package com.cathysoft.lncefdb.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cathysoft.lncefdb.service.BrowseService;
import com.cathysoft.lncefdb.service.DateFormatService;
import com.cathysoft.lncefdb.service.StatisticsService;

@Controller
public class BrowseController {

	@Autowired
	private StatisticsService service2;
	
	@Autowired
	private DateFormatService service3;
	
	@RequestMapping(
			value="browse",
			method=RequestMethod.GET)
	public String showBrowsePage(Model model) {
		model.addAttribute("lastSubmitTime", service3.format(service2.statisticsLastSubmitTime()));
		return "lncef.browse";
	}
	
	@Autowired
	private BrowseService service;
	
	@RequestMapping(
			value="fetch-tree",
			method=RequestMethod.GET)
	public void fetchTreeAjax(
			HttpServletResponse response,
			@RequestParam(required=false) String id,
			@RequestParam(required=false) String grp) throws Exception {
		
		//System.out.println(id+";"+grp);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Pragma", "no-cache"); //Pragma: no-cache
		
		PrintWriter out = response.getWriter();
		
		JSONArray arr = service.fetchChildren(id, grp);
		
		out.println(arr.toString());
		out.flush();
	}
	
	@RequestMapping(
			value="fetch-data",
			method=RequestMethod.GET)
	public void fetchDataAjax(
			HttpServletResponse response,
			@RequestParam(required=false) String id,
			@RequestParam(required=false) String grp) throws Exception {
		
		//System.out.println(id+";"+grp);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Pragma", "no-cache"); //Pragma: no-cache
		
		PrintWriter out = response.getWriter();
		
		JSONArray arr = service.fetchData(id, grp);
		JSONObject obj = new JSONObject();
		obj.accumulate("page", "1");
		obj.accumulate("total", "1");
		obj.accumulate("records", arr.size());
		obj.accumulate("rows", arr);
		
		out.println(obj.toString());
		out.flush();
	}
}
