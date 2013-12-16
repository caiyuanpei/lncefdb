package com.cathysoft.lncefdb.local;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

public class BuildFdrDB2MySQL {

	public static void main(String[] args) throws Exception {
		
		Date today = new Date();
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/context.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("db/LncRNA-EF.csv").getInputStream()));
		String line = br.readLine(); //skip #1 line, as the title.
		
		JdbcTemplate tpl = new JdbcTemplate(ctx.getBean(DataSource.class));
		
		tpl.update("DELETE FROM fdr");
		
		Map<String, String> map = new HashMap<String, String>();
		
		String insertSql = 
				"INSERT INTO fdr(lnc, ef, fdr, status, submitTime) VALUES(?, ?, ?, ?, ?)";
		List<Object[]> params = new ArrayList<Object[]>();
		while((line=br.readLine())!=null){
			if(line == null || line.trim().length()==0) continue;
			
			String[] tokens = line.split(",");
			if(tokens.length != 3) continue;
			
			if(map.containsKey(tokens[0].trim()+";"+tokens[1].trim())) {
				System.out.println("SKIP: "+line);
				continue;
			} else {
				map.put(tokens[0].trim()+";"+tokens[1].trim(), "");
			}
			
			params.add(new Object[]{tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), "predict"/*experiment*/, today});
		}
		tpl.batchUpdate(insertSql, params);
		br.close();
		
		
		br = new BufferedReader(new InputStreamReader(new ClassPathResource("db/experiment.csv").getInputStream()));
		
		insertSql = 
				"INSERT INTO fdr(lnc, ef, fdr, status, pubmedid, submitTime) VALUES(?, ?, ?, ?, ?, ?)";
		line = br.readLine(); //skip #1 line, as title.
		params.clear();
		
		while((line=br.readLine())!=null){
			if(line == null || line.trim().length()==0) continue;
			
			String[] tokens = line.split(",");
			if(tokens.length != 5) continue;
			
			if(map.containsKey(tokens[0].trim()+";"+tokens[1].trim())) {
				System.out.println("SKIP: "+line);
				continue;
			} else {
				map.put(tokens[0].trim()+";"+tokens[1].trim(), "");
			}
			
			params.add(new Object[]{tokens[0].trim(), tokens[1].trim(), "-", tokens[3].trim(), tokens[4].trim(), today});
		}
		tpl.batchUpdate(insertSql, params);
		br.close();
		
		System.out.println("Finished.");
	}
}
