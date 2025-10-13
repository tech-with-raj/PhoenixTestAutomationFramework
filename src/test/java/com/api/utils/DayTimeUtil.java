package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DayTimeUtil {

	
	private DayTimeUtil() {

	}
	
	public static String getTimeWithDayAgo(int days) {
		
		
	return Instant.now().minus(days, ChronoUnit.DAYS).toString();
		
	}
	
}
