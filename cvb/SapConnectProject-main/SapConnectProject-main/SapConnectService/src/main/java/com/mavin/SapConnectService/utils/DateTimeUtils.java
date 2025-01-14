package com.mavin.SapConnectService.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
	
	//----- LocalDateTime -----//
	public static LocalDateTime toLocalDateTime(String datetime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(datetime, formatter);
	}
		
	public static String toDateTimeString(LocalDateTime datetime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return datetime.format(formatter);
	}
	
	//----- LocalDate -----//
	public static LocalDate toLocalDate(String date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(date, formatter);
	}
	
	public static String toDateString(LocalDate date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(formatter);
	}
}
