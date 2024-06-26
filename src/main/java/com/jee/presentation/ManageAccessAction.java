package com.jee.presentation;

import com.jee.beans.Access;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ManageAccessAction extends Action {

	public ManageAccessAction(BusinessFacade facade) {
		super(facade);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session =req.getSession();
		String username=(String) session.getAttribute("username");
		User user =facade.getUserbyUsername(username);
		String doc_id =req.getParameter("doc_id");
		String newuser_username_access=req.getParameter("username");
		User new_user =facade.getUserbyUsername(newuser_username_access);
		String accesslvl=req.getParameter("accesslvl");
		if(req.getRequestURI().contains("grant")) {
			
			Access new_access =new Access();
			new_access.setId_doc(Integer.parseInt(doc_id));
			new_access.setId_user(new_user.getId());
			new_access.setAccesslvl(accesslvl);
						

			facade.addUserAccess(new_access);
		}
		else if (req.getRequestURI().contains("remove")) {
			System.out.println(new_user +"doc id "+doc_id);
			Access access =facade.getAccess(Integer.parseInt(doc_id), new_user.getId());
			System.out.println("remove access" +access);
			facade.removeAccess(access.getId_doc(), access.getId_user());
			
		}
		else if (req.getRequestURI().contains("update")) {
			Access access =facade.getAccess(Integer.parseInt(doc_id),new_user.getId() );
			access.setAccesslvl(accesslvl);
			System.out.println("updated access :"+access);
			facade.updateAccess(access);
		}
		
		
		
		
		super.HandleMainPageRoutine(user, req, res);
		return"views/home.jsp";
	}

}
