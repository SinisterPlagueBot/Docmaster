package com.jee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jee.beans.Document;

public class DocumentDaoImplOracle implements  DocumentDao{
		Connection cnx;
		
	public DocumentDaoImplOracle(DataSource ds) {
			
			this.cnx = ds.getConnection();
		}

	public void InsertDocument(Document d) {
		String query ="insert into DOCUMENT (titre,description,filepath) values (? ,? ,? )";
		try {
			PreparedStatement pst =cnx.prepareStatement(query);
		
			pst.setString(1, d.getTitre());
			pst.setString(2, d.getDescription());
			pst.setString(3, d.getFilePath());
		
			pst.executeUpdate();
			cnx.commit();
			pst.close();
		} catch (Exception e) {

		}
	}

	public Document SelectDocumentById(int id) {

		String query ="select *  from Document where id ="+id;
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			Document d=  new Document();
			if(rs.next()) {
				d.setId(rs.getInt(1));
				d.setTitre(rs.getString(2));
				d.setDescription(rs.getString(3));
				d.setFilePath(rs.getString(4));
			}
			return d;
		} catch (Exception e) {

		}
		return null;
	}	public Document SelectDocumentByTitle(String title) {

		String query ="select *  from DOCUMENT where titre='"+title+"'";
		try {
			Statement st =cnx.createStatement();
			ResultSet rs = st.executeQuery(query);
			Document d=  new Document();
			if(rs.next()) {
				d.setId(rs.getInt(1));
				d.setTitre(rs.getString(2));
				d.setDescription(rs.getString(3));
				d.setFilePath(rs.getString(4));
			}
			return d;
		} catch (Exception e) {

		}
		return null;
	}

	public List<Document> SelectAllDoc() {
		String query ="select *  from Document";
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



	
	public void UpdateDocument(Document d) {
        String query = "UPDATE DOCUMENT SET titre = ?, description = ?, filepath = ? WHERE id = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, d.getTitre());
            pst.setString(2, d.getDescription());
            pst.setString(3, d.getFilePath());
            pst.setInt(4, d.getId());
            pst.executeUpdate();
            cnx.commit();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public void RemoveDocument(int id) {
		// TODO Auto-generated method stub
		
	}

	}


