package com.example.CoderHack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CoderHackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoderHackApplication.class, args);
	}

}

