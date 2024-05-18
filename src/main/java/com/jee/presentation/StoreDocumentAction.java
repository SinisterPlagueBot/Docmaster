package com.jee.presentation;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jee.beans.Access;
import com.jee.beans.Document;
import com.jee.beans.User;
import com.jee.business.BusinessFacade;
import com.jee.business.LocalDocsManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class StoreDocumentAction extends Action {
	 LocalDocsManager docsdb;
	public StoreDocumentAction(BusinessFacade facade) {
		super(facade);
		docsdb=new LocalDocsManager("D:"+File.separator+"docmaster_docdb");
	      
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if(request.getRequestURI().contains("step2")) {
			HttpSession session = request.getSession();
			System.out.println("ste^2");
			String username= (String) session.getAttribute("username");
			System.out.println(username);
			User user =facade.getUserbyUsername(username);
			
			Document new_doc = new Document();

			new_doc.setTitre((String)session.getAttribute("doc_title"));
			new_doc.setDescription((String)session.getAttribute("doc_desc"));
			String file_path ="D:"+File.separator+"docmaster_docdb"+File.separator+new_doc.getTitre();  
			new_doc.setFilePath(file_path);
			facade.addDoc(new_doc);
			new_doc=facade.getDocBytitle((String)session.getAttribute("doc_title"));
			System.out.println(new_doc);
			Access doc_ownership= new Access(user.getId(),new_doc.getId(),"o");
			System.out.println(doc_ownership);
			facade.addUserAccess(doc_ownership);
			File tempDir = new File("D:"+File.separator+"docmaster_docdb_tmp");
	        tempDir.mkdirs();
	try {
	        // Iterate over the parts of the uploaded file
	        for (Part part : request.getParts()) {
	            String fileName = docsdb.getFileName(part);
	            if (fileName != null && !fileName.isEmpty()) {
	                // Create a temporary file to save the content of the part
	                File tempFile = new File(tempDir, fileName);
	                
	                // Write the content of the part to the temporary file
	                try (InputStream inputStream = part.getInputStream();
	                     OutputStream outputStream = new FileOutputStream(tempFile)) {
	                    byte[] buffer = new byte[4096];
	                    int bytesRead;
	                    while ((bytesRead = inputStream.read(buffer)) != -1) {
	                        outputStream.write(buffer, 0, bytesRead);
	                    }
	                }
	            }
	        }
	        
	        // Concatenate all the parts into a single file
	        File outputFile = new File(tempDir,new_doc.getTitre());
	        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
	            for (File partFile : tempDir.listFiles()) {
	                if (!partFile.equals(outputFile)) {
	                    try (InputStream inputStream = new FileInputStream(partFile)) {
	                        byte[] buffer = new byte[4096];
	                        int bytesRead;
	                        while ((bytesRead = inputStream.read(buffer)) != -1) {
	                            outputStream.write(buffer, 0, bytesRead);
	                        }
	                    }
	                }
	            }
	        }
	       docsdb.storeDocument(outputFile);
	        super.HandleMainPageRoutine(user, request, response);
	        // Delete the temporary directory and its contents
	        docsdb.deleteDirectory(tempDir);
	        
	}catch(IOException | ServletException e) {e.printStackTrace();}
	return "views/home.jsp";	
		}
		else if (request.getRequestURI().contains("step1")) {
			System.out.println("step1");
			String doc_title= request.getParameter("doc_title");
			String doc_desc = request.getParameter("doc_desc");
			HttpSession session = request.getSession();
			session.setAttribute("doc_title",doc_title);
			session.setAttribute("doc_desc",doc_desc);
			return "docstep2.html";
			
		}
		return null;
		
	}
	

}
