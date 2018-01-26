package com.duc.condata.service;

import java.util.List;

import com.duc.condata.entity.User;

public interface IUserService {
	List<User> getAllUser();

	User getUserById(int userID);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(int userID);
}
