package com.cathysoft.lncefdb.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.googlecode.ehcache.annotations.Cacheable;

@Service
public class AjaxSupportService {

	@Autowired
	private DataSource dataSource;
	
	@Transactional
	@Cacheable(cacheName="lncsByTerm")
	public List<Map<String, String>> fetchLncRNAs(String term) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		return tpl.query("SELECT DISTINCT lnc FROM fdr WHERE lnc LIKE ? LIMIT 0, 10", new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row)
					throws SQLException {
				Map<String, String> m = new LinkedCaseInsensitiveMap<String>();
				String lnc = rs.getString("lnc");
				m.put("id", DigestUtils.md5Hex(lnc));
				m.put("label", lnc);
				m.put("value", lnc);
				return m;
			}
		}, term+"%");
	}
	
	@Transactional
	@Cacheable(cacheName="efsByTerm")
	public List<Map<String, String>> fetchEfs(String term) {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		return tpl.query("SELECT DISTINCT ef FROM fdr WHERE ef LIKE ? LIMIT 0, 10", new RowMapper<Map<String, String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row)
					throws SQLException {
				Map<String, String> m = new LinkedCaseInsensitiveMap<String>();
				String ef = rs.getString("ef");
				m.put("id", DigestUtils.md5Hex(ef));
				m.put("label", ef);
				m.put("value", ef);
				return m;
			}
		}, term+"%");
	}
}
