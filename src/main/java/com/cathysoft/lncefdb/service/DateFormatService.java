package com.cathysoft.lncefdb.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateFormatService {

	
	public String format(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return String.format("%s %s, %d",  monthStr(month), ordinal(day), year );
	}

	public String monthStr(int month) {
		String[] monthes = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		return monthes[month];
	}
	
	public String ordinal(int i) {
	    String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	    switch (i % 100) {
	    case 11:
	    case 12:
	    case 13:
	        return i + "th";
	    default:
	        return i + sufixes[i % 10];
	    }
	}
}
