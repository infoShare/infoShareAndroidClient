package com.infoshare.builders;

import java.util.Date;

import com.infoshare.model.Category;

public class CategoryBuilder {

	private Category.Id id;
	private int userId;
	private String name;
	private Date creationDate;

	public CategoryBuilder() {
	}

	public CategoryBuilder withId(Category.Id id) {
		this.id = id;
		return this;
	}

	public CategoryBuilder withUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public CategoryBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public CategoryBuilder withDate(Date creationDate) {
		this.creationDate = creationDate;
		return this;
	}

	public Category build() {
		Category category = new Category();
		if (id != null) {
			category.setId(id);
		}
		category.setUserId(userId);
		category.setName(name);
		category.setCreationDate(creationDate);
		return category;
	}

}
