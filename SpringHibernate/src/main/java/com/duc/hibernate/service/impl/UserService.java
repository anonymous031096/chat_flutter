package com.duc.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duc.hibernate.dao.IUserDAO;
import com.duc.hibernate.entity.User;
import com.duc.hibernate.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDAO iUserDAO;
	
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return iUserDAO.getUserById(userId);
	}
	
}
