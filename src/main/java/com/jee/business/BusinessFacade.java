package com.jee.business;

import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.dao.AccessDao;
import com.jee.dao.DocumentDao;
import com.jee.dao.UserDao;

public class BusinessFacade {
		AccessBusiness accessbs;
		UserBusiness userbs;
		DocumentBusiness docbs;
		public BusinessFacade (AccessDao adb,UserDao udb,DocumentDao ddb) {
			accessbs=new AccessBusinessImpl(adb);
			userbs=new UserBusinessImpl(udb, adb);
			docbs=new DocumentBusinessImpl(ddb, adb, udb);
		
		}
		public Boolean checkAccess(int userId,int docId) {
			return accessbs.checkAccess(userId, docId);
		}
		public Access getAccess(int docId,int userId) {
			return accessbs.getAccess(docId, userId);
		}
		public void removeAccess(int userId,int docId) {
			accessbs.removeAccess(docId, userId);
		}
		public void addUserAccess(Access a) {
			accessbs.addUserAccess(a);
		}
		public void updateAccess(Access a) {
			accessbs.updateAccess(a);
		}
		public User getDocOwner(int docId) {
			return docbs.getDocOwner(docId);
		}
		public List<User>getAllColaborators(int docId) {
			return docbs.getAllColaborators(docId);
		}
		public void addDoc(Document newDoc) {
			docbs.addDoc(newDoc);
		}
		public void updateDoc(Document newDoc) {
			docbs.updateDoc(newDoc);
		}
		public void removeDoc(int docId) {
			docbs.removeDoc(docId);
		}
		public User authenticateUser(String username,String password) {
			return userbs.authenticateUser(username, password);
		}
		public User getUserbyUsername(String username) {
			return userbs.getUserbyUsername(username);
		}
		public void addUser(User newUser) {
			userbs.addUser(newUser);
		}
		public User getUser(int userId) {
			return userbs.getUser(userId);
		}
		public void updateUser(User newUser) {
			userbs.updateUser(newUser);
		}
		public void removeUser(int userId) {
			userbs.removeUser(userId);
		}
		public List<Document> getAllDocsByUser(int userId) {
			return userbs.getAllDocsByUser(userId);
		}
		public Document getDocBytitle(String doc_title) {
			// TODO Auto-generated method stub
			return docbs.getDocByTitle(doc_title);
		}
		public Document getDocById(int int1) {
			// TODO Auto-generated method stub
			return docbs.getDocById(int1);
		}
		public void removeAccessByDoc(int doc_id_int) {
			// TODO Auto-generated method stub
			accessbs.deleteAllAccessByDoc(doc_id_int);
		}
}
