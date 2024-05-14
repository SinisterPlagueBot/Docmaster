package com.jee.business;

import com.jee.beans.Access;

public interface AccessBusiness {
	public Boolean checkAccess(int userId,int docId);
	public Access getAccess(int userId,int docId);
	public void removeAccess(int userId,int docId);
	public void addUserAccess(Access a);
	void updateAccess(Access a);
}