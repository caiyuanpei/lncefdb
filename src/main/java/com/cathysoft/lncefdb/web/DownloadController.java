package com.cathysoft.lncefdb.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadController {

	
	@RequestMapping(
			value="download",
			method=RequestMethod.GET)
	public String showDownloadPage() {
		
		return "lncef.download";
	}
	
	@RequestMapping(
			value="download/{subjet}/{format}",
			method=RequestMethod.GET)
	public void download(
			HttpServletResponse response,
			@PathVariable String subject,
			@PathVariable String format) {
		response.setContentType("application/octet-stream");
		
		
	}
}
