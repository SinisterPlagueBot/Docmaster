package com.jee.presentation;

import java.util.ArrayList;
import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Action {
	protected BusinessFacade facade;
	public Action(BusinessFacade facade) {
		this.facade=facade;
	}
	protected void HandleMainPageRoutine(User user,HttpServletRequest req,HttpServletResponse res) {
		req.setAttribute("user",user);
		//all documents where user is a part of
		List<Document> userDocs =facade.getAllDocsByUser(user.getId());
		
		req.setAttribute("userDocs",userDocs );
		List<User> userDocsOwners =new ArrayList<User>();
		// users permission for every doc
		List<Access> userPermissions =new ArrayList<>();
		
		for (Document d :userDocs) {
			userDocsOwners.add(facade.getDocOwner(d.getId()));
			userPermissions.add(facade.getAccess( d.getId(),user.getId()));
		}

		req.setAttribute("userDocsOwners", userDocsOwners);
		req.setAttribute("userDocsPermissions", userPermissions);
			
	}
	public abstract String execute(HttpServletRequest req,HttpServletResponse res);
}
