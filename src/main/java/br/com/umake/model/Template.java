package br.com.umake.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Template {

	private String name;
	private String author;
	private String authorEmail;
	private String authorWebsite;
	private String description;
	private String indexFileName = "index.jsp";
	private String pageFileName = "page.jsp";
	private List<Css> cssFiles = new ArrayList<Template.Css>();

	public Template(){
		
	}
	
	public String toString(){
		
		return this.getName();
		
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorWebsite() {
		return authorWebsite;
	}

	public void setAuthorWebsite(String authorWebsite) {
		this.authorWebsite = authorWebsite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndexFileName() {
		return indexFileName;
	}

	public void setIndexFileName(String indexFileName) {
		this.indexFileName = indexFileName;
	}

	public String getPageFileName() {
		return pageFileName;
	}

	public void setPageFileName(String pageFileName) {
		this.pageFileName = pageFileName;
	}

	public List<Css> getCssFiles() {
		return cssFiles;
	}

	public void setCssFiles(List<Css> cssFiles) {
		this.cssFiles = cssFiles;
	}

	private class Css{
		
		private String fileName;
		private String media;
		
		public String toString(){
			
			return fileName;
			
		}

		public String getFileName() {
			
			return fileName;
			
		}

		public void setFileName(String fileName) {
			
			this.fileName = fileName;
			
		}

		public String getMedia() {
			
			return media;
			
		}

		public void setMedia(String media) {
			
			this.media = media;
			
		}
		
		
	}
	
}
