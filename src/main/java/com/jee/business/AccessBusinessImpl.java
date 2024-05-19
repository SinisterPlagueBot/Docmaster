package com.jee.business;

import com.jee.beans.Access;
import com.jee.dao.AccessDao;

public class AccessBusinessImpl implements AccessBusiness {
	AccessDao accessdb;
	public AccessBusinessImpl(AccessDao accessdb) {
		
		this.accessdb = accessdb;
	}
	public Boolean checkAccess(int userId, int docId) {
		
		return accessdb.SelectAccess(docId, userId) ==null ?false:true;
	}



	@Override
	public Access getAccess(int docId, int userId) {
		// TODO Auto-generated method stub
		return accessdb.SelectAccess(docId, userId);
	}

	@Override
	public void updateAccess(Access a) {
		accessdb.UpdateAccess(a);
	}

	@Override
	public void removeAccess(int userId, int docId) {
		accessdb.RemoveAccess(docId,userId);
		
	}

	@Override
	public void addUserAccess(Access a) {
		accessdb.InsertAccess(a);
		
	}
	@Override
	public void deleteAllAccessByDoc(int doc_id_int) {
		accessdb.removeAccessbyDoc(doc_id_int);
		
	}

}
