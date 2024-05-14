package com.jee.dao;

import java.util.List;

import com.jee.beans.Document;

public interface DocumentDao {
	public void InsertDocument(Document d);
	public Document SelectDocument(int id);
	public List<Document> SelectAllDoc();
	public void UpdateDocument(Document d);
	public void RemoveDocument(int id);	
}