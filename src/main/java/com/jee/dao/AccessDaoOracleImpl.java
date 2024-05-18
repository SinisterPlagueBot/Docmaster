package com.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
// TODO use the meteor !!!!!
public class AccessDaoOracleImpl implements AccessDao {
	Connection cnx;

	public AccessDaoOracleImpl(DataSource ds) {
		
		this.cnx = ds.getConnection();
	}


	public void InsertAccess(Access a) {
		String query ="insert into ACCESS_ values (? ,? ,? )";
		try {
			PreparedStatement pst =cnx.prepareStatement(query);
			pst.setInt(1, a.getId_doc());
			pst.setInt(2, a.getId_user());
			pst.setString(3, a.getAccesslvl());
			System.out.println("access inserted");
			pst.executeUpdate();
			cnx.commit();
			pst.close();
		} catch (Exception e) {

		}
	}


	public Access SelectAccess(int id_doc,int id_usr) {
		String query ="select *  from ACCESS_ where id_user="+id_usr +" and id_doc="+id_doc;
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			Access d=  new Access();
			if(rs.next()) {
				d.setId_user(id_usr);
				d.setId_doc(id_doc);
				d.setAccesslvl(rs.getString(3));
			}
			return d;
		} catch (Exception e) {

		}
		return null;
	}

	public List<Access> SelectAllAccess() {
		String query ="select *  from  Access_";
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<Access> ds=new ArrayList<>();
			while(rs.next()) {
				Access d =  new Access();
				
				d.setId_user(rs.getInt(1));
				d.setId_doc(rs.getInt(2));
				d.setAccesslvl(rs.getString(3));
				ds.add(d);
			}
			return ds;
		} catch (Exception e) {

		}
		return null;
	}

	public void UpdateAccess(Access a) {
		
	}

	
	public void RemoveAccess(int doc_id,int user_id) {
		try {
	        // Assuming you have a connection object named "connection"
	        
	        // SQL DELETE statement to remove access
	        String sql = "DELETE FROM access_ WHERE id_doc = ? AND id_user = ?";
	        
	        // Prepare the statement
	        PreparedStatement statement = cnx.prepareStatement(sql);
	        
	        // Set parameters
	        statement.setInt(1, doc_id);
	        statement.setInt(2, user_id);
	        
	        // Execute the statement
	        statement.executeUpdate();
	        cnx.commit();
	        statement.close();
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }
	}


	@Override
	public List<Access> selectAllAccessbyDoc(int docId) {
		String query ="select *  from  ACCESS_ where ID_DOC="+docId;
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<Access> ds=new ArrayList<>();
			while(rs.next()) {
				Access d =  new Access();
				
				d.setId_doc(rs.getInt(1));
				d.setId_user(rs.getInt(2));
				d.setAccesslvl(rs.getString(3));
				ds.add(d);
			}
			return ds;
		} catch (Exception e) {

		}
		return null;
	}


	@Override
	public List<User> selectAllUsersbyDoc(int docId) {
		String query ="select u.id,u.username,u.password  from  Access_ a INNER JOIN User u on a.id_user=u.id where id_doc="+docId;
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<User> us=new ArrayList<>();
			while(rs.next()) {
				User u =  new User();
				
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				us.add(u);
			}
			return us;
		} catch (Exception e) {

		}
		return null;
	
	}


	@Override
	public List<Document> selectAllDocsbyUser(int userId) {
		String query ="select d.id,d.titre,d.description ,d.filepath from  Access_ a INNER JOIN DOCUMENT d on a.id_doc=d.id where id_user="+userId;
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			List<Document> ds=new ArrayList<>();
			while(rs.next()) {
				Document d =  new Document();
				
				d.setId(rs.getInt(1));
				d.setTitre(rs.getString(2));
				d.setDescription(rs.getString(3));
				d.setFilePath(rs.getString(4));
				ds.add(d);
			}
			return ds;
		} catch (Exception e) {

		}
		return null;
	}


	

}
