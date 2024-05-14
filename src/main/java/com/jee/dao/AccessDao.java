package com.jee.dao;

import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;

public interface AccessDao {
	public void InsertAccess(Access a);
	public Access SelectAccess(int id_doc,int id_usr);
	public List<Access> SelectAllAccess();
	public void UpdateAccess(Access a);
	public void RemoveAccess(int id, int docId);
	public List<Access> selectAllAccessbyDoc(int docId);
	public List<User> selectAllUsersbyDoc(int docId);	
	public List<Document> selectAllDocsbyUser(int userId);	
}