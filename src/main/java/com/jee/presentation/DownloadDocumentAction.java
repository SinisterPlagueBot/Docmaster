package com.jee.presentation;

import java.io.*;

import com.jee.beans.Document;
import com.jee.business.BusinessFacade;
import com.jee.business.LocalDocsManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DownloadDocumentAction extends Action {

	private LocalDocsManager docsdb;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	public DownloadDocumentAction(BusinessFacade facade) {
		super(facade);
		docsdb = new LocalDocsManager("D:" + File.separator + "docmaster_docdb");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String doc_id = request.getParameter("doc_id");
		HttpSession session =request.getSession();
		String username = (String) session.getAttribute("username");

		// Log parameters for debugging
		System.out.println("Username: " + username);
		System.out.println("Doc ID: " + doc_id);

		// Handling the home page routing
		super.HandleMainPageRoutine(facade.getUserbyUsername(username), request, response);

		Document doc = facade.getDocById(Integer.parseInt(doc_id));
		File file = docsdb.getDocumentByFilePath(doc.getFilePath());
		String doc_filepath= doc.getFilePath();
		request.setAttribute("doc_filepath", "");
//		// Check if the file exists
		if (file.exists()) {
			System.out.println("File exists: " + file.getAbsolutePath());

			
			response.reset();
	        response.setBufferSize(DEFAULT_BUFFER_SIZE);
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Length", String.valueOf(file.length()));
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");


	     // Prepare streams.
	        BufferedInputStream input = null;
	        BufferedOutputStream output = null;

	        try {
	            // Open streams.
	            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
	            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

	            // Write file contents to response.
	            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
	            int length;
	            while ((length = input.read(buffer)) > 0) {
	                output.write(buffer, 0, length);
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            // Gently close streams.
	            close(output);
	            close(input);
	        }
		} else {
			// Output message if the file doesn't exist
			System.out.println("File not found: " + file.getAbsolutePath());
		}

		// Return null to prevent forwarding to a view
		return null;
	}
	private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }
}
