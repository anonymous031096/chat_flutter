package com.duc.hibernate.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duc.hibernate.dao.IUserDAO;
import com.duc.hibernate.entity.User;

@Transactional
@Repository
public class UserDAO implements IUserDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public User getUserById(int UserID) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, UserID);
	}

}
