package com.duc.condata.dao;

import java.util.List;

import com.duc.condata.entity.User;

public interface IUserDAO {
	List<User> getAllUser();

	User getUserById(int userID);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(int userID);
}
