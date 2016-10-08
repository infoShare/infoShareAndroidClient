package com.infoshare.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import com.infoshare.builders.CategoryBuilder;
import com.infoshare.model.Category;

public class CategoryParser extends SoapParser<List<Category>>{

	public List<Category> parse(SoapObject response){
		List<Category> categories = new ArrayList<Category>();
		for(int i=0;i<response.getPropertyCount();i++){    
		       Vector resV = (Vector) response.getProperty(i); 
		       int resVlenght = resV.size();
		       for(int count = 0;count<resVlenght;count++)
		       {
		           SoapObject p = (SoapObject) resV.elementAt(count);
		           categories.add(parseCategory(p));
		       }

		}
		return categories;
	}

	private Category parseCategory(SoapObject o) {
		return new CategoryBuilder().withDate(getDate(o, "creationDate"))
				.withId(getId(o, "id"))
				.withName(getString(o, "name"))
				.withUserId(getInt(o, "userId"))
				.build();
	}

}
