package com.jee.presentation;

import java.util.ArrayList;
import java.util.List;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SigninAction extends Action {

	public SigninAction(BusinessFacade facade) {
		super(facade);
		
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		HttpSession session =req.getSession();
		User user =facade.authenticateUser(username, password);
		session.setAttribute("username", username);
		System.out.println(session.getAttributeNames());		String result;
		if(user !=null) {
			super.HandleMainPageRoutine(user,req,res);
			result="/views/home.jsp";
		}
		else {
			result="signin.jsp";
			req.setAttribute("error",true);
		}
		return result;
	}

	
}
