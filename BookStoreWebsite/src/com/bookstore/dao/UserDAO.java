package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {

	}

	@Override
	public Users create(Users user) {
		// String encryptedPassword=HashGenerator.generateMD5(user.getPassword());
		user.setPassword(user.getPassword());
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub
		return super.update(user);
	}

	@Override
	public Users get(Object userID) {

		return super.find(Users.class, userID);
	}

	public Users findByEmail(String email) {
		// "email" refer to query =:email"
		List<Users> listUsers = super.findWithNamedQuery("Users.findByEmail", "email", email);
		if (listUsers != null && listUsers.size() > 0) {
			return listUsers.get(0);
		}
		return null;

	}

	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameters = new HashMap<>();
		String encryptedPassword = HashGenerator.generateMD5(password);
		parameters.put("email", email);
		parameters.put("password", password);

		List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameters);
		if (listUsers.size() == 1) {
			return true;
		}
		return false;

	}

	@Override
	public void delete(Object userId) {
		super.delete(Users.class, userId);

	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public long count() {

		return super.countWithNamedQuery("Users.countAll");
	}

}
