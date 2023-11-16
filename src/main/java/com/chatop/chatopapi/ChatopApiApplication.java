package com.chatop.chatopapi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
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
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}

}

