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

public class SignupAction extends Action{

	public SignupAction(BusinessFacade facade) {
		super(facade);
		
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String username =req.getParameter("username");
		String confirmed_password =req.getParameter("confirmed_password");
		String password =req.getParameter("password");
		String resultPath ;
		HttpSession session =req.getSession();
		session.setAttribute("username", username);
		if(password.equals(confirmed_password)) {
			User user = new User(username,password);
			super.HandleMainPageRoutine(user, req, res);
			resultPath="/views/home.jsp";
		}
		else {
			resultPath="signup.jsp";
			req.setAttribute("error",true);
		}return resultPath;
	}

}
