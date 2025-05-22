package com.nnk.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.setAdditionalProfiles("dev");
		logger.info("Starting application with profile: dev");
		application.run(args);
	}
}
