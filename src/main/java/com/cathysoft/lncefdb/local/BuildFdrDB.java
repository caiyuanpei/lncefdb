package com.cathysoft.lncefdb.local;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

public class BuildFdrDB {

	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/context.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("db/LncRNA-EF.csv").getInputStream()));
		String line = br.readLine(); //skip #1 line, as the title.
		
		JdbcTemplate tpl = new JdbcTemplate(ctx.getBean(DataSource.class));
		String createSql = 
				"CREATE TABLE fdr(lnc VARCHAR(50), ef VARCHAR(100), fdr VARCHAR(30), status VARCHAR(10), email VARCHAR(50), descrip VARCHAR(250))";
		tpl.update(createSql); //build table;
		
		String insertSql = 
				"INSERT INTO fdr(lnc, ef, fdr, status) VALUES(?,?,?, ?)";
		List<Object[]> params = new ArrayList<Object[]>();
		while((line=br.readLine())!=null){
			if(line == null || line.trim().length()==0) continue;
			
			String[] tokens = line.split(",");
			if(tokens.length != 3) continue;
			
			params.add(new Object[]{tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), "predict"/*experiment*/});
		}
		tpl.batchUpdate(insertSql, params);
		
		br.close();
	}
	
}
