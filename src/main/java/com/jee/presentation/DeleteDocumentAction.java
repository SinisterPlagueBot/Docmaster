package com.jee.presentation;

import java.io.File;

import com.jee.beans.Access;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;
import com.jee.business.LocalDocsManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteDocumentAction extends Action {
	LocalDocsManager localStorage = new LocalDocsManager("D:" + File.separator + "docmaster_docdb");

	public DeleteDocumentAction(BusinessFacade facade) {
		super(facade);

	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();

		String username = (String) session.getAttribute("username");
//		System.out.println("doc_id:"+req.getParameter("doc_id"));
		User user = facade.getUserbyUsername(username);
		String doc_id = (String) req.getParameter("doc_id");
		if (doc_id != null) {
			int doc_id_int = Integer.parseInt(doc_id);
//			System.out.println("doc _id to be deleted"+doc_id);
			Access access = facade.getAccess (doc_id_int,user.getId());
//			System.out.println(access);
			if (access.getAccesslvl().equals("o")) {
				// remove from disk
				String filePath = facade.getDocById(doc_id_int).getFilePath();
				facade.removeAccess( doc_id_int,user.getId());
				localStorage.deleteFile(filePath);
			}
			// Rest of your code here
		} else {
			// Handle the case where "doc_id" is null
		}

		
		super.HandleMainPageRoutine(user, req, res);
		return "views/home.jsp";
	}

}
