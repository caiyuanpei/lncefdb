package com.cathysoft.lncefdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchService {

	@Autowired
	private DataSource dataSource;
	
	@Transactional
	public List<Map<String, Object>> search(
			boolean logic1, String lnc, boolean exact1,
			boolean logic2, String ef,  boolean exact2,
			boolean logic3, String status) {
		
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM fdr");
		boolean where = false;
		
		if(lnc != null && lnc.trim().length() > 0) {//lnc
			String condition = null;
			if(exact1) {
				if(logic1) {
					condition = "lnc='"+lnc.trim().replaceAll("'", "''")+"'";
				} else {
					condition = "lnc<>'"+lnc.trim().replaceAll("'", "''")+"'";
				}
			} else {
				if(logic1) {
					condition = "lnc LIKE '%"+lnc.trim().replaceAll("'", "''")+"%'";
				} else {
					condition = "lnc NOT LIKE '%"+lnc.trim().replaceAll("'", "''")+"%'";
				}
			}
			if(!where) { //no where contained
				builder.append(" WHERE ").append(condition);
				where=true;
			} else {
				builder.append(" AND ").append(condition);
			}
		}
		
		if(ef != null && ef.trim().length() > 0) {//lnc
			String condition = null;
			if(exact2) {
				if(logic2) {
					condition = "ef='"+ef.trim().replaceAll("'", "''")+"'";
				} else {
					condition = "ef<>'"+ef.trim().replaceAll("'", "''")+"'";
				}
			} else {
				if(logic2) {
					condition = "ef LIKE '%"+ef.trim().replaceAll("'", "''")+"%'";
				} else {
					condition = "ef NOT LIKE '%"+ef.trim().replaceAll("'", "''")+"%'";
				}
			}
			if(!where) { //no where contained
				builder.append(" WHERE ").append(condition);
				where=true;
			} else {
				builder.append(" AND ").append(condition);
			}
		}
		
		if(status.equals("any")) {
			if(!logic3) { //not any = empty
				return new ArrayList<Map<String,Object>>();
			} else {
				//append nothing
			}
		} else {
			String condition = null;
			if(logic3)
				condition = "status = '"+status+"'";
			else
				condition = "status <> '"+status+"'";
			
			if(!where) {
				builder.append(" WHERE ").append(condition);
				where=true;
			} else {
				builder.append(" AND ").append(condition);
			}
		}
		
		String sql = builder.toString();
		System.out.println("Try Query: " +sql+ "");
		
		return tpl.queryForList(sql);
	}
	
}
