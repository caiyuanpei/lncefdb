package com.cathysoft.lncefdb.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;

@Service
public class StatisticsService {

	static Logger logger = LoggerFactory.getLogger(StatisticsService.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Transactional
	@Cacheable(cacheName="statistics")
	public int statisticsLncRnas() {
		
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		int cn = tpl.queryForObject("SELECT COUNT(DISTINCT lnc) FROM fdr", Integer.class);
		
		logger.info("Statisic Lnc RNA ("+cn+" items).");
		return cn;
	}
	
	@Transactional
	@Cacheable(cacheName="statistics")
	public int statisticsEnvironmentFactors() {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		int cn = tpl.queryForObject("SELECT COUNT(DISTINCT ef) FROM fdr", Integer.class);
		
		logger.info("Statisic Environment Factors ("+cn+" items).");
		return cn;
	}
	
	@Transactional
	@Cacheable(cacheName="statistics")
	public int statisticsPredictedFdrs() {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		int cn = tpl.queryForObject("SELECT COUNT(*) FROM fdr WHERE status='predict'", Integer.class);
		
		logger.info("Statisic Predicted Fdrs ("+cn+" items).");
		return cn;
	}
	
	@Transactional
	@Cacheable(cacheName="statistics")
	public int statisticsExperimentFdrs() {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		int cn = tpl.queryForObject("SELECT COUNT(*) FROM fdr WHERE status='experiment'", Integer.class);
		
		logger.info("Statisic Experiment Fdrs ("+cn+" items).");
		return cn;
	}
	
	@Transactional
	@Cacheable(cacheName="statistics")
	public String statisticsLastSubmitTime() {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		Date date = tpl.queryForObject("SELECT MAX(submitTime) FROM fdr", Date.class);
		
		logger.info("Statisic Last Submit Time = ("+ date +").");
		return new SimpleDateFormat("yyyy.MM.dd").format(date);
	}
}
