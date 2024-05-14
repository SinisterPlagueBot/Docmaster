package com.jee.presentation;

import com.jee.business.BusinessFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupAction extends Action{

	public SignupAction(BusinessFacade facade) {
		super(facade);
		
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String username =req.getParameter("username");
		String password =req.getParameter("password");
		return null;
	}

}
