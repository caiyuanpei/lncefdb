package com.cathysoft.lncefdb.service;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BrowseService {

	@Autowired
	private DataSource dataSource;
	
	@Transactional
	public JSONArray fetchData(String id, String grp) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		
		List<Map<String, Object>> list = null;
		if(grp.equals("_lnc"))
			list = tpl.queryForList("SELECT lnc, ef, fdr, status FROM fdr WHERE lnc=?", id);
		else
			list = tpl.queryForList("SELECT lnc, ef, fdr, status FROM fdr WHERE ef=?", id);
		
		return JSONArray.fromObject(list);
	}
	
	@Transactional
	public JSONArray fetchChildren(String pid, String grp) {
		JSONArray arr = new JSONArray();
		
		if(pid.equals("__root")) {
			buildRoot(arr);
		} else if(pid.equals("IncRNA")) {//fetch alpha list for IncRNA
			buildAlphaForInc(arr);
		} else if(pid.equals("Environment_Factor")) {//fetch alpha list for ef
			buildAlphaForEf(arr);
		} else {//fetch data by grp and pid
			buildData(pid, grp, arr);
		}
		
		return arr;
	}
	
	@Transactional
	private void buildData(String pid, String grp, JSONArray arr) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<Map<String, Object>> ls = null;
		if(grp.equals("lnc"))
			ls = tpl.queryForList("SELECT DISTINCT lnc as n FROM fdr WHERE LEFT(UPPER(lnc),1)=?", pid.toUpperCase());
		else
			ls = tpl.queryForList("SELECT DISTINCT ef as n FROM fdr WHERE LEFT(UPPER(ef),1)=?", pid.toUpperCase());
		
		for(Map<String, Object> m : ls) {
			JSONObject obj = new JSONObject();
			obj.accumulate("data", (String)m.get("n"));
			
			JSONObject attr = new JSONObject();
			attr.accumulate("id", (String)m.get("n"));
			attr.accumulate("grp", "_"+grp);
			attr.accumulate("rel", "file");
			obj.accumulate("attr", attr);
			
			arr.add(obj);
		}
	}

	@Transactional
	private void buildAlphaForInc(JSONArray arr) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<Map<String, Object>> ls = tpl.queryForList("SELECT DISTINCT LEFT(UPPER(lnc), 1) AS st FROM fdr");
		for(Map<String, Object> m : ls) {
			JSONObject obj = new JSONObject();
			obj.accumulate("data", (String)m.get("st"));
			obj.accumulate("state", "closed");
			
			JSONObject attr = new JSONObject();
			attr.accumulate("id", (String)m.get("st"));
			attr.accumulate("grp", "lnc");
			attr.accumulate("rel", "folder");
			obj.accumulate("attr", attr);
			
			arr.add(obj);
		}
	}

	@Transactional
	private void buildAlphaForEf(JSONArray arr) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<Map<String, Object>> ls = tpl.queryForList("SELECT DISTINCT LEFT(UPPER(ef), 1) AS st FROM fdr");
		for(Map<String, Object> m : ls) {
			JSONObject obj = new JSONObject();
			obj.accumulate("data", (String)m.get("st"));
			obj.accumulate("state", "closed");
			
			JSONObject attr = new JSONObject();
			attr.accumulate("id", (String)m.get("st"));
			attr.accumulate("grp", "ef");
			attr.accumulate("rel", "folder");
			obj.accumulate("attr", attr);
			
			arr.add(obj);
		}
	}
	
	@Transactional
	private void buildRoot(JSONArray arr) {
		JSONObject obj = new JSONObject();
		obj.accumulate("data", "IncRNA");
		obj.accumulate("state", "closed");
		JSONObject attr = new JSONObject();
		attr.accumulate("id", "IncRNA");
		attr.accumulate("rel", "folder");
		obj.accumulate("attr", attr);
		
		arr.add(obj);
		
		obj = new JSONObject();
		obj.accumulate("data", "Environment Factor");
		obj.accumulate("state", "closed");
		attr = new JSONObject();
		attr.accumulate("id", "Environment_Factor");
		attr.accumulate("rel", "folder");
		obj.accumulate("attr", attr);
		
		arr.add(obj);
	}
	
}
