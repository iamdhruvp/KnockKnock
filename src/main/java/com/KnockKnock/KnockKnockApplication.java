package com.KnockKnock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KnockKnockApplication implements ApplicationRunner {

	public static final Logger logger = LogManager.getLogger(KnockKnockApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KnockKnockApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments applicationArguments) {
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
	}
}
