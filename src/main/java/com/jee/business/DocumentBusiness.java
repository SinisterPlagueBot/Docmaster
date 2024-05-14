package com.jee.business;

import java.util.List;

import com.jee.beans.Document;
import com.jee.beans.User;

public interface DocumentBusiness {
	public User getDocOwner(int docId);
	public List<User>getAllColaborators(int docId);
	public void addDoc(Document newDoc);
	public void updateDoc(Document newDoc);
	public void removeDoc(int docId);
	
}
