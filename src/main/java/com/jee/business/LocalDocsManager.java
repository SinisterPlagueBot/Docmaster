package com.jee.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;


import jakarta.servlet.http.Part;

public class LocalDocsManager {
	private String LocalStorageDir;

	public LocalDocsManager(String dir) {
		LocalStorageDir = dir;
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

	public void deleteFile(String filePath) {
		Path path = Paths.get(filePath);

		try {
			Files.delete(path);

		} catch (java.nio.file.NoSuchFileException e) {

		} catch (IOException e) {

		}
	}

	public File getDocumentByFilePath(String filePath) {
		// Create a File object with the provided file path
		File file = new File(filePath);

		// Check if the file exists and is a file (not a directory)
		if (file.exists() && file.isFile()) {
			// If it exists and is a file, return the File object
			return file;
		} else {
			// If the file doesn't exist or is a directory, return null
			return null;
		}
	}
	public String updateFilenameAndGetFilePath(String currentFilePath, String newFileName) throws IOException {
        File currentFile = new File(currentFilePath);
        if (!currentFile.exists() || !currentFile.isFile()) {
            throw new NoSuchFileException("File not found: " + currentFilePath);
        }

        String newFilePath = LocalStorageDir + File.separator + newFileName;
        File newFile = new File(newFilePath);

        if (currentFile.renameTo(newFile)) {
            System.out.println("File renamed successfully to: " + newFilePath);
            return newFilePath;
        } else {
            throw new IOException("Error renaming file to: " + newFilePath);
        }
    }

}
