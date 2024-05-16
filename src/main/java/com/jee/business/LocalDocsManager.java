package com.jee.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class LocalDocsManager {
	private String LocalStorageDir;
	public LocalDocsManager(String dir) {
		LocalStorageDir=dir;
	}
	public String storeDocument(File file) throws IOException {
        String fileName = file.getName();
        String filePath = LocalStorageDir + File.separator + fileName;
        try (FileInputStream inputStream = new FileInputStream(file);
             FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("File stored successfully at: " + filePath);
        } catch (IOException e) {
            System.out.println("Error storing file: " + e.getMessage());
            throw e;
        }
        return filePath;
    }
	public void deleteDirectory(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
        directory.delete();
    }
	public String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        if (contentDisposition != null) {
            for (String token : contentDisposition.split(";")) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }
	
}
