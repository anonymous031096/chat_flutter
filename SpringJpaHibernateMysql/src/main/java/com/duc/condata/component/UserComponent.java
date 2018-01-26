package com.duc.condata.component;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.duc.condata.entity.User;
import com.duc.condata.service.IUserService;

@Component
public class UserComponent implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(UserComponent.class);

	@Autowired
	private IUserService iUserService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		// Add user
		User user1 = new User("le","duc");
		iUserService.addUser(user1);
		// Update user
		User user2 = new User(33,"huong","pham");
		iUserService.updateUser(user2);
		// delete user
		iUserService.deleteUser(101);
		
		showUserById(33);
		showAllUser();
	}

	public void showAllUser() {
		List<User> lUser = iUserService.getAllUser();
		for (User user : lUser) {
			logger.info("User ID: " + user.getId() + "| First Name: " + user.getFirstName() + "| Last Name: "
					+ user.getLastName());
		}
	}

	public void showUserById(int userID) {
		User user = iUserService.getUserById(userID);
		logger.info("User ID: " + user.getId() + "| First Name: " + user.getFirstName() + "| Last Name: "
				+ user.getLastName());
	}
}
