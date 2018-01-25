package com.duc.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.duc.hibernate.entity.User;
import com.duc.hibernate.service.IUserService;

@SpringBootApplication
public class SpringHibernateApplication implements CommandLineRunner{

	private static final Logger logger = LogManager.getLogger(SpringHibernateApplication.class);
	
	@Autowired
	private IUserService iUserService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = iUserService.getUserById(1);
		logger.warn(user.getName());
	}
}
