package com.duc.condata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duc.condata.dao.IUserDAO;
import com.duc.condata.entity.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDAO iUserDAO;
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return iUserDAO.getAllUser();
	}

	@Override
	public User getUserById(int userID) {
		// TODO Auto-generated method stub
		return iUserDAO.getUserById(userID);
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
	public void deleteUser(int userID) {
		// TODO Auto-generated method stub
		iUserDAO.deleteUser(userID);
	}

}
