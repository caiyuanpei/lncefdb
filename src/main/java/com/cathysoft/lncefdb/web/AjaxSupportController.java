package com.cathysoft.lncefdb.web;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cathysoft.lncefdb.service.AjaxSupportService;

@Controller
public class AjaxSupportController {

	@Autowired
	private AjaxSupportService service;
	
	@RequestMapping(
			value="lncRNAs",
			method=RequestMethod.GET)
	public void fetchLncRNAofJson(
			@RequestParam(required=false) String term,
			HttpServletResponse response) throws Exception {
		if(term == null || term.trim().length() ==0) return; //safe check
		
		List<Map<String, String>> list = service.fetchLncRNAs(term);
		JSONArray arr = JSONArray.fromObject(list);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(arr.toString());
		out.flush();
	}
	
	
	@RequestMapping(
			value="Efs",
			method=RequestMethod.GET)
	public void fetchEfofJson(
			@RequestParam(required=false) String term,
			HttpServletResponse response) throws Exception {
		if(term == null || term.trim().length() ==0) return; //safe check
		
		List<Map<String, String>> list = service.fetchEfs(term);
		JSONArray arr = JSONArray.fromObject(list);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(arr.toString());
		out.flush();
	}
}
