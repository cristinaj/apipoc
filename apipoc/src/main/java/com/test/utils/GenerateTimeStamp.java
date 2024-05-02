package com.test.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenerateTimeStamp {

	public static String getTimeStamp() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		LocalDate date = LocalDate.now();
		return dateTimeFormatter.format(date);
	}
	
	public static long getCurrentTime() {
		return System.currentTimeMillis();
	}
}
