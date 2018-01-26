package org.duc.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.duc.hibernate.dao.IUserDAO;
import org.duc.hibernate.entity.User;
import org.duc.hibernate.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO iUserDAO;

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return iUserDAO.getUserById(userId);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return iUserDAO.getAllUser();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		 iUserDAO.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		iUserDAO.updateUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		iUserDAO.deleteUser(userId);
	}

}