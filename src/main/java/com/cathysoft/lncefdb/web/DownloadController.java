package com.cathysoft.lncefdb.web;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cathysoft.lncefdb.service.DownloadService;

@Controller
public class DownloadController {

	
	@RequestMapping(
			value="download",
			method=RequestMethod.GET)
	public String showDownloadPage() {
		
		return "lncef.download";
	}
	
	@Autowired
	private DownloadService service;
	
	@RequestMapping(
			value="download/{subject}/{format}",
			method=RequestMethod.GET)
	public void download(
			HttpServletResponse response,
			@PathVariable String subject,
			@PathVariable String format) throws Exception {
		if(format.equals("csv")||format.equals("tsv")) {
			response.setContentType("application/octet-stream");
		} else {
			response.setContentType("application/vnd.ms-excel");
		}
		response.setHeader("Content-Disposition", "attachment; filename="+subject+"."+format);
		
		InputStream is = null;
		if(subject.equals("lnc")) {
			is = service.downloadLncRNA(format);
		} else if(subject.equals("ef")) {
			is = service.downloadEnvironmentFactors(format);
		} else if(subject.equals("experiment")) {
			is = service.downloadExperimentRelations(format);
		} else if(subject.equals("predict")) {
			is = service.downloadPredictRelations(format);
		} else {
			return;
		}
		
		if(is != null) {
			OutputStream os = response.getOutputStream();
			byte[] cache = new byte[256];
			int n;
			while((n=is.read(cache))>-1) {
				os.write(cache, 0, n);
			}
			is.close();
			os.flush();
		}
	}
}
