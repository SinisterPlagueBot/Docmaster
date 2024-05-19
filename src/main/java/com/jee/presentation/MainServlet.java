package com.jee.presentation;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.jee.business.BusinessFacade;
import com.jee.business.LocalDocsManager;
import com.jee.dao.AccessDaoOracleImpl;
import com.jee.dao.DataSource;
import com.jee.dao.DocumentDaoImplOracle;
import com.jee.dao.OracleDataSource;
import com.jee.dao.UserDaoImplOracle;

@MultipartConfig
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Action> actions;
	private BusinessFacade facade;
	private DataSource ds;
	private LocalDocsManager localDocumentdb;

	public void init(ServletConfig config) throws ServletException {
		localDocumentdb = new LocalDocsManager("D:" + File.separator + "docmaster_docdb");
		ds = new OracleDataSource();
		facade = new BusinessFacade(new AccessDaoOracleImpl(ds), new UserDaoImplOracle(ds),
				new DocumentDaoImplOracle(ds));
		actions = new HashMap<String, Action>();
		actions.put("signin", new SigninAction(facade));
		actions.put("signup", new SignupAction(facade));
		actions.put("storeFile", new StoreDocumentAction(facade, this.localDocumentdb));
		actions.put("downloadFile", new DownloadDocumentAction(facade, this.localDocumentdb));
		actions.put("deleteFile", new DeleteDocumentAction(facade, this.localDocumentdb));
		actions.put("updateFile", new UpdateDocumentInfoAction(facade, this.localDocumentdb));
		actions.put("accessUpdate", new ManageAccessAction(facade));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 60);
		String uri = request.getRequestURI();
		int x = uri.lastIndexOf("/");
		int y = uri.lastIndexOf(".do");
		String actionKey = uri.substring(x + 1, y);

		String result = actions.get(actionKey).execute(request, response);
		if (result != null) {
			request.getRequestDispatcher(result).forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("ok");
	}

}
