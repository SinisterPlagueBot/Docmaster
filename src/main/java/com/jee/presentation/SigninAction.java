package com.jee.presentation;

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
			result="success.html";
		}
		else 
			result="error.html";
		return result;
	}
	
}
