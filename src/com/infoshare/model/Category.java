package com.infoshare.model;

import java.io.Serializable;
import java.util.Date;

public class Category extends AbstractIdentity implements Serializable {

	private static final long serialVersionUID = 2245889815387665496L;
	
	private Category.Id id;
	private int userId;
	private String name;
	private Date creationDate;

	public Category.Id getId() {
		return id;
	}

	public void setId(Category.Id id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
