package com.jee.dao;

import java.util.List;

import com.jee.beans.User;

public interface UserDao {
	public void InsertUser(User u);
	public User SelectUser(int id);
	public List<User> SelectAllUsers();
	public void UpdateUser(User u);
	public void RemoveUser(int id);	
}