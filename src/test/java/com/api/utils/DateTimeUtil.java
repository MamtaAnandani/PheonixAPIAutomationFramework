package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	private DateTimeUtil() {
		//object won't be created outside the class with this constructor
		
	}
 public static String getTimeWithDaysAgo(int days) {
	return Instant.now().minus(days, ChronoUnit.DAYS).toString();//the time is in string format is in script
	 
 }
}
