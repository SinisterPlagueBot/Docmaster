package com.jee.presentation;

import java.io.IOException;

import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;
import com.jee.business.LocalDocsManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateDocumentInfoAction extends Action{
	private LocalDocsManager localDocumentdb;
	public UpdateDocumentInfoAction(BusinessFacade facade,LocalDocsManager localDocDb) {
		super(facade);
		this.localDocumentdb=localDocDb;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session =req.getSession();
		User user = facade.getUserbyUsername((String)session.getAttribute("username"));
		
		String doc_id = req.getParameter("doc_id");
		String doc_title=req.getParameter("doc_title");
		String doc_desc=req.getParameter("doc_desc");
		Document doc = facade.getDocById(Integer.parseInt(doc_id));
		doc.setDescription(doc_desc);
		doc.setTitre(doc_title);
		try {
			doc.setFilePath(localDocumentdb.updateFilenameAndGetFilePath(doc.getFilePath(), doc.getTitre()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		facade.updateDoc(doc);
		super.HandleMainPageRoutine(user, req, res);
		return "views/home.jsp";
	}

}
