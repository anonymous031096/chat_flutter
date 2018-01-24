package org.duc.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.duc.spring.lang.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainProgram implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(MainProgram.class);
	@Autowired
	private Language language;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MainProgram.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		logger.warn(language.getGreeting());
		logger.error(language.getBye());
	}
}
