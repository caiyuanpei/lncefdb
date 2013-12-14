package com.cathysoft.lncefdb.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubmitService {

	@Autowired
	private DataSource dataSource;
	
	public boolean submitTerm(String lnc, String ef, String status, String email, String descrip) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		tpl.update(
				"INSERT INTO fdr(lnc, ef, status, email, descrip, fdr) VALUES(?,?,?,?,?,'-')", 
				lnc, ef, status, email, descrip);
		
		return true;
	}
}
