package org.duc.hibernate.service;

import java.util.List;

import org.duc.hibernate.entity.User;

public interface IUserService {
	User getUserById(int userId);

	List<User> getAllUser();

	void addUser(User user);

	void deleteUser(int userId);

	void updateUser(User user);
}
