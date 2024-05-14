package com.jee.presentation;

import com.jee.business.BusinessFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Action {
	protected BusinessFacade facade;
	public Action(BusinessFacade facade) {
		this.facade=facade;
	}
	public abstract String execute(HttpServletRequest req,HttpServletResponse res);
}
