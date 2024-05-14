package com.jee.business;

import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.dao.AccessDao;
import com.jee.dao.DocumentDao;
import com.jee.dao.UserDao;

public class DocumentBusinessImpl  implements DocumentBusiness{
	DocumentDao docdb;
	AccessDao accessdb;
	UserDao userdb;
	public DocumentBusinessImpl(DocumentDao docdb, AccessDao accessdb, UserDao userdb) {
		
		this.docdb = docdb;
		this.accessdb = accessdb;
		this.userdb = userdb;
	}

	@Override
	public User getDocOwner(int docId) {
	for(Access a :accessdb.selectAllAccessbyDoc(docId)) {
			if(a.getAccesslvl().equals("o")) {
				return userdb.SelectUser(a.getId_user());
			}
		}
		return null;
	}

	@Override
	public List<User> getAllColaborators(int docId) {
		// TODO Auto-generated method stub
		return accessdb.selectAllUsersbyDoc(docId);
	}

	@Override
	public void addDoc(Document newDoc) {
		docdb.InsertDocument(newDoc);
	}

	@Override
	public void updateDoc(Document newDoc) {
		docdb.UpdateDocument(newDoc);
	}

	@Override
	public void removeDoc(int docId) {
		docdb.RemoveDocument(docId);
		
	}

}
