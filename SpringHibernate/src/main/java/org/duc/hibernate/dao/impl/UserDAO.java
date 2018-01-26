package org.duc.hibernate.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.duc.hibernate.dao.IUserDAO;
import org.duc.hibernate.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String qr = "from User";
		return (List<User>) entityManager.createQuery(qr).getResultList();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		User us = getUserById(user.getId());
		us.setFisrtName(user.getFisrtName());
		us.setLastName(user.getLastName());
		entityManager.flush();
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		entityManager.remove(getUserById(userId));
	}

}