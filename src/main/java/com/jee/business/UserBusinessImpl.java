package com.jee.business;

import java.util.List;

import com.jee.beans.*;
import com.jee.dao.AccessDao;
import com.jee.dao.UserDao;

public class UserBusinessImpl implements UserBusiness {
		UserDao userdb;
		AccessDao accessdb;
		
	public UserBusinessImpl(UserDao udb,AccessDao accessdb) {
		
			this.userdb = udb;
			this.accessdb = accessdb;
		}

	@Override
	public User authenticateUser(String username, String password) {
		for(User u: userdb.SelectAllUsers()) {
			if( u.getUsername().equals(username) && u.getPassword().equals(password)) {
				System.out.println("authenticated User :"+u);
				return u;
			}
		};
		return null;
		
	}

	@Override
	public User getUserbyUsername(String username) {
		for(User u: userdb.SelectAllUsers()) {
			if( u.getUsername().equals(username) ) {
				return u;
			}
		};
		return null;
			}

	@Override
	public void addUser(User newUser) {
		userdb.InsertUser(newUser);
		
	}

	@Override
	public User getUser(int userId) {
		return userdb.SelectUser(userId);
	}

	@Override
	public void updateUser(User newUser) {
		userdb.UpdateUser(newUser);
		
	}

	@Override
	public void removeUser(int userId) {
		userdb.RemoveUser(userId);
		
	}

	@Override
	public List<Document> getAllDocsByUser(int userId) {
		return accessdb.selectAllDocsbyUser(userId);
	}

}
