package de.wk.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	static private Logger logger = LoggerFactory.getLogger(UserServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		logger.info("Started user service, v1.0");
	}
}
