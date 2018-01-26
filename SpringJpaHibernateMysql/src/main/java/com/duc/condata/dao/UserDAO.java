package com.duc.condata.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.duc.condata.entity.User;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String qr = "from User";
		return (List<User>) entityManager.createQuery(qr).getResultList();
	}

	@Override
	public User getUserById(int userID) {
		// TODO Auto-generated method stub
		return entityManager.find(User.class, userID);
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
		us.setFirstName(user.getFirstName());
		us.setLastName(user.getLastName());
		entityManager.flush();
	}

	@Override
	public void deleteUser(int userID) {
		// TODO Auto-generated method stub
		entityManager.remove(getUserById(userID));
	}
}
