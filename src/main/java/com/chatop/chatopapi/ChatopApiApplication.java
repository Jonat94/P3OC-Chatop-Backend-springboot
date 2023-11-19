package com.chatop.chatopapi;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@ComponentScan(basePackages = "org.springframework.security.authentication.AuthenticationManager")
@EnableSwagger2
public class ChatopApiApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ChatopApiApplication.class, args);
	}

	public static String getDate() {
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		System.out.println(new Timestamp(new Date().getTime()));
		return sdf3.format(new Timestamp(new Date().getTime())).toString();

	}

	
	public static String formatDate(String dateToConvert) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/mm/dd");
		LocalDateTime dateTime = LocalDateTime.parse(dateToConvert,formatter);
		
		return dateTime.format(formatter2);
}
	
/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
*/
}
