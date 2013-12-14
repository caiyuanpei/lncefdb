package com.cathysoft.lncefdb.local;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cathysoft.lncefdb.service.SearchService;

public class QueryExample {

	
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/context.xml");
		JdbcTemplate tpl = new JdbcTemplate(ctx.getBean(DataSource.class));
		
		List<Map<String, Object>> li = tpl.queryForList("SELECT * FROM fdr WHERE lnc=?", "RP11-1103G16.1");
		System.out.println(li);
		
		
		SearchService ser = ctx.getBean(SearchService.class);
		List<Map<String, Object>> li1 = ser.search(true, "RP11-1103G16.1", true, true, "", false, true, "any");
		System.out.println(li1);
	}
}
