package com.cathysoft.lncefdb.service;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.TriggersRemove;

@Service
public class SubmitService {

	@Autowired
	private DataSource dataSource;
	
	@Transactional
	@TriggersRemove(cacheName="statistics", removeAll=true)
	public boolean submitTerm(String lnc, String ef, String status, String pubmedid, String email, String descrip) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		
		/* 2013.12.17 Update For PubMed ID & Submit Time. */
		tpl.update(
				"INSERT INTO fdr(lnc, ef, status, pubmedid, email, descrip, fdr, submitTime) VALUES(?,?,?,?,?,?,'-',?)", 
				lnc, ef, status, pubmedid, email, descrip, new Date());
		
		return true;
	}
	
	@Transactional
	@TriggersRemove(cacheName="statistics", removeAll=true)
	public boolean updateTerm(String lnc, String ef, String status, String pubmedid, String email, String descrip) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		
		/* 2013.12.17 Update For PubMed ID & Submit Time. */
		tpl.update(
				"UPDATE fdr SET status=?, pubmedid=?, email=?, descrip=?, fdr=?, submitTime=? WHERE lnc=? AND ef=?",
				status, pubmedid, email, descrip, "-", new Date(), lnc, ef);
		return true;
	}
}
