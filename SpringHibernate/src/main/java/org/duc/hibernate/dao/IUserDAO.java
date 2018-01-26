package org.duc.hibernate.dao;

import java.util.List;

import org.duc.hibernate.entity.User;

public interface IUserDAO {
	User getUserById(int UserID);

	List<User> getAllUser();

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(int userId);
}
