package com.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jee.beans.User;

public class UserDaoImplOracle implements UserDao{
	Connection cnx;

	public UserDaoImplOracle(DataSource ds) {
		
		this.cnx = ds.getConnection();
	}

	public void InsertUser(User u) {
		String query ="insert into USER_(username,password)  values(? ,?  )";
		try {
			PreparedStatement pst =cnx.prepareStatement(query);
			
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.executeUpdate();
		} catch (Exception e) {

		}
	}

	public User SelectUser(int id) {

		String query ="select *  from USER_  where id ="+id;
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			User usr =  new User();
			if(rs.next()) {
				usr.setId(rs.getInt(1));
				usr.setUsername(rs.getString(2));
				usr.setPassword(rs.getString(3));
			}
			return usr;
		} catch (Exception e) {

		}
		return null;
	}

	public List<User> SelectAllUsers() {
		String query ="select *  from user_";
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<User> usrs=new ArrayList<>();
			while(rs.next()) {
				User usr =  new User();
				
				usr.setId(rs.getInt(1));
				usr.setUsername(rs.getString(2));
				usr.setPassword(rs.getString(3));
				usrs.add(usr);
				System.out.println(usr);
			}
			
			return usrs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void UpdateUser(User u) {
			
	}

	public void RemoveUser(int id) {
		
	}
}
