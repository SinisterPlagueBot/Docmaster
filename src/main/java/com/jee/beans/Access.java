package com.jee.beans;

public class Access {
  int id_user;
  int id_doc;
  /* o :owner
   * r : read
   * r+u:read and update
   *
   **/
  String accesslvl;
public int getId_user() {
	return id_user;
}
public void setId_user(int id_user) {
	this.id_user = id_user;
}
public int getId_doc() {
	return id_doc;
}
public void setId_doc(int id_doc) {
	this.id_doc = id_doc;
}
public String getAccesslvl() {
	return accesslvl;
}
public void setAccesslvl(String accesslvl) {
	this.accesslvl = accesslvl;
}
public Access(int id_user, int id_doc, String accesslvl) {
	
	this.id_user = id_user;
	this.id_doc = id_doc;
	this.accesslvl = accesslvl;
}

public Access() {
	
}
@Override
public String toString() {
	return "Access [id_user=" + id_user + ", id_doc=" + id_doc + ", accesslvl=" + accesslvl + "]";
}

}
