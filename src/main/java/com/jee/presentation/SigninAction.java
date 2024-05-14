package com.jee.presentation;

import java.util.ArrayList;
import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SigninAction extends Action {

	public SigninAction(BusinessFacade facade) {
		super(facade);
		
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		User user =facade.authenticateUser(username, password);
		String result;
		if(user !=null) {
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
			System.out.println(userDocs);
			System.out.println(userPermissions);
			System.out.println(userDocsOwners);
			req.setAttribute("userDocsOwners", userDocsOwners);
			req.setAttribute("userDocsPermissions", userPermissions);
			
			result="/views/home.jsp";
		}
		else 
			result="error.html";
		return result;
	}
	
}
