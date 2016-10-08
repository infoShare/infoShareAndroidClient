package com.infoshare.builders;

import java.util.Date;

import com.infoshare.model.Category;
import com.infoshare.model.Information;

public class InformationBuilder {

	private Information.Id id;
	private Long userId;
	private String userName;
	private Category category;
	private String content;
	private Date creationDate;

	public InformationBuilder() {
	}

	public InformationBuilder withId(Information.Id id) {
		this.id = id;
		return this;
	}

	public InformationBuilder withUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	
	public InformationBuilder withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public InformationBuilder withCategory(Category category) {
		this.category = category;
		return this;
	}

	public InformationBuilder withContent(String content) {
		this.content = content;
		return this;
	}

	public InformationBuilder withDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}
	
	public Information build(){
		Information information = new Information();
		if(id!=null){
			information.setId(id);
		}
		information.setCategory(category);
		information.setContent(content);
		information.setCreationDate(creationDate);
		information.setUserId(userId);
		information.setUserName(userName);
		return information;
		
		
	}
}
