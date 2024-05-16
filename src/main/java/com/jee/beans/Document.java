package com.jee.beans;



public class Document {
	private int id;
	private String titre;
	private String description;
	private String FilePath;


	    // Constructor
	    public Document(int id, String titre, String description, String filePath) {
	        this.id = id;
	        this.titre = titre;
	        this.description = description;
	        this.FilePath = filePath;
	    }

	    public Document() {
			// TODO Auto-generated constructor stub
		}

		public Document( String titre, String description, String filePath) {
			this.titre = titre;
	        this.description = description;
	        this.FilePath = filePath;
	    }
		

		// Getters
	    public int getId() {
	        return id;
	    }

	    public String getTitre() {
	        return titre;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getFilePath() {
	        return FilePath;
	    }

	    // Setters
	    public void setId(int id) {
	        this.id = id;
	    }

		public void setTitre(String titre) {
			this.titre = titre;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setFilePath(String filePath) {
			FilePath = filePath;
		}

		@Override
		public String toString() {
			return "Document [id=" + id + ", titre=" + titre + ", description=" + description + ", FilePath=" + FilePath
					+ "]";
		}

	
}
