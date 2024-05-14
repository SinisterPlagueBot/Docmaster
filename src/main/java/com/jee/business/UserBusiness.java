package com.jee.business;

import java.util.List;

import com.jee.beans.Document;
import com.jee.beans.User;

public interface UserBusiness{
	public User authenticateUser(String username,String password);
	public User getUserbyUsername(String username);
	public void addUser(User newUser);
	public User getUser(int userId);
	public void updateUser(User newUser);
	public void removeUser(int userId);
	public List<Document> getAllDocsByUser(int userId);
	
}
