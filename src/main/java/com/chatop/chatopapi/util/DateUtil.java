package com.chatop.chatopapi.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	// Get the current date in a string format "yyyy-MM-dd HH:mm:ss"
	public String getDate() {
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf3.format(new Timestamp(new Date().getTime())).toString();
	}

	
	//convert string date format from "yyyy-MM-dd HH:mm:ss" to "yyyy/MM/dd"
	public String formatDate(String dateToConvert) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime dateTime = LocalDateTime.parse(dateToConvert, formatter);

		return dateTime.format(formatter2);
	}
}
