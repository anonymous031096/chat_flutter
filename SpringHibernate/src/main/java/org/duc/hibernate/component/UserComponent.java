package org.duc.hibernate.component;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.duc.hibernate.SpringHibernateApplication;
import org.duc.hibernate.entity.User;
import org.duc.hibernate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserComponent implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(SpringHibernateApplication.class);

	@Autowired
	private IUserService iUserService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User us = new User(3,"Leq","Ducw");
		//showAllUser();
		//iUserService.deleteArticle(2);
		//iUserService.addUser(us);
		iUserService.updateUser(us);
		showAllUser();
	}

	public void showUserById(int userID) {
		User user = iUserService.getUserById(userID);
		logger.info("User ID: " + user.getId() + "| First name: " + user.getFisrtName()+"| Last name: "+user.getLastName());
	}
	
	public void showAllUser() {
		List<User> lUser = iUserService.getAllUser();
		for(User user : lUser) {
			logger.info("User ID: " + user.getId() + "| First name: " + user.getFisrtName()+"| Last name: "+user.getLastName());
		}
	}
}
