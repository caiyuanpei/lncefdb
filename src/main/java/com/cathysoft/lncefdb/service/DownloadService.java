package com.cathysoft.lncefdb.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DownloadService {

	@Autowired
	private DataSource dataSource;
	
	@Transactional
	public InputStream downloadLncRNA(String format) throws Exception {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<String> list = tpl.queryForList("SELECT DISTINCT lnc FROM fdr", String.class);
		
		if(format.equals("csv")) {
			File tmp = File.createTempFile("lnctmp", null);
			PrintWriter writer = new PrintWriter(tmp);
			buildCsv("LncRNA", list, writer);
			writer.close();
			return new FileInputStream(tmp);
		} else {
			File tmp = File.createTempFile("lnctmp", null);
			FileOutputStream os = new FileOutputStream(tmp);
			buildExcel("LncRNA", list, os);
			os.close();
			return new FileInputStream(tmp);
		}
	}
	
	@Transactional
	public InputStream downloadEnvironmentFactors(String format) throws Exception {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<String> list = tpl.queryForList("SELECT DISTINCT ef FROM fdr", String.class);
		
		if(format.equals("csv")) {
			File tmp = File.createTempFile("eftmp", null);
			PrintWriter writer = new PrintWriter(tmp);
			buildCsv("Environmental Factors", list, writer);
			writer.close();
			return new FileInputStream(tmp);
		} else {
			File tmp = File.createTempFile("eftmp", null);
			FileOutputStream os = new FileOutputStream(tmp);
			buildExcel("Environmental Factors", list, os);
			os.close();
			return new FileInputStream(tmp);
		}
	}
	
	@Transactional
	public InputStream downloadPredictRelations(String format) throws Exception {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<String[]> list = tpl.query("SELECT lnc, ef, fdr FROM fdr WHERE status='predict'", new ObjectArrMapper());
		
		if(format.equals("csv")) {
			File tmp = File.createTempFile("eftmp", null);
			PrintWriter writer = new PrintWriter(tmp);
			buildCsv(new String[]{"LncRNA","Environmental Factors","P value (FDR correction)"}, list, writer, ",");
			writer.close();
			return new FileInputStream(tmp);
		} else if(format.equals("tsv")) { 
			File tmp = File.createTempFile("eftmp", null);
			PrintWriter writer = new PrintWriter(tmp);
			buildCsv(new String[]{"LncRNA","Environmental Factors","P value (FDR correction)"}, list, writer, "\t");
			writer.close();
			return new FileInputStream(tmp);
		} else {
			File tmp = File.createTempFile("eftmp", null);
			FileOutputStream os = new FileOutputStream(tmp);
			buildExcel(new String[]{"LncRNA","Environmental Factors","P value (FDR correction)"}, list, os);
			os.close();
			return new FileInputStream(tmp);
		}
	}
	
	@Transactional
	public InputStream downloadExperimentRelations(String format) throws Exception {
		JdbcTemplate tpl = new JdbcTemplate(dataSource);
		List<String[]> list = tpl.query("SELECT lnc, ef, pubmedid FROM fdr WHERE status='experiment'", new ObjectArrMapper());
		
		if(format.equals("csv")) {
			File tmp = File.createTempFile("eftmp", null);
			PrintWriter writer = new PrintWriter(tmp);
			buildCsv(new String[]{"LncRNA","Environmental Factors","PubMed ID"}, list, writer, ",");
			writer.close();
			return new FileInputStream(tmp);
		} else if(format.equals("tsv")) { 
			File tmp = File.createTempFile("eftmp", null);
			PrintWriter writer = new PrintWriter(tmp);
			buildCsv(new String[]{"LncRNA","Environmental Factors","PubMed ID"}, list, writer, "\t");
			writer.close();
			return new FileInputStream(tmp);
		} else {
			File tmp = File.createTempFile("eftmp", null);
			FileOutputStream os = new FileOutputStream(tmp);
			buildExcel(new String[]{"LncRNA","Environmental Factors","PubMed ID"}, list, os);
			os.close();
			return new FileInputStream(tmp);
		}
	}
	
	private void buildCsv(String title, List<String> values, PrintWriter writer) throws Exception {
		writer.println(title);
		for(String val : values) {
			writer.println(val);
		}
	}
	
	private void buildCsv(String[] titles, List<String[]> values, PrintWriter writer, String sep) throws Exception {
		writer.println(StringUtils.join(titles, sep));
		for(String[] v : values) {
			writer.println(StringUtils.join(v, sep));
		}
	}
	
	public void buildExcel(String title, List<String> values, OutputStream os) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("List");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(title);
		
		for(int i=0; i<values.size(); i++) {
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(values.get(i));
		}
		workbook.write(os);
	}
	
	public void buildExcel(String[] titles, List<String[]> values, OutputStream os) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("List");
		HSSFRow row = sheet.createRow(0);
		for(int i=0; i<titles.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(titles[i]);
		}
		
		for(int i=0; i<values.size(); i++) {
			row = sheet.createRow(i+1);
			for(int j=0; j<titles.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(values.get(i)[j]);
			}
		}
		workbook.write(os);
	}
	
	class ObjectArrMapper implements RowMapper<String[]> {
		@Override
		public String[] mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			String[] val = new String[rs.getMetaData().getColumnCount()];
			for(int i=0; i<val.length; i++) {
				val[i] = rs.getString(i+1);
			}
			return val;
		}
	}
}
