package com.infoshare.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Information extends AbstractIdentity implements Serializable {

	private static final long serialVersionUID = 2843693913121933798L;
	public static final String INFO_KEY = "info";
	
	private Information.Id id;
	private Long userId;
	private String userName;
	private Category category;
	private String content;
	private Date creationDate;
	
	public Information.Id getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setId(Information.Id id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void updateContent(String newContent){
		this.content = newContent;
		this.creationDate = Calendar.getInstance().getTime();
	}

}
