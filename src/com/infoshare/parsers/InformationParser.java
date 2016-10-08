package com.infoshare.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import com.infoshare.builders.CategoryBuilder;
import com.infoshare.builders.InformationBuilder;
import com.infoshare.model.Information;

public class InformationParser extends SoapParser<List<Information>> {

	public List<Information> parse(SoapObject response){
		List<Information> informations = new ArrayList<Information>();
		for(int i=0;i<response.getPropertyCount();i++){    
		       Vector resV = (Vector) response.getProperty(i); 
		       int resVlenght = resV.size();
		       for(int count = 0;count<resVlenght;count++)
		       {
		           SoapObject p = (SoapObject) resV.elementAt(count);
		           informations.add(parseInformation(p));
		       }

		}
		return informations;
	}

	private Information parseInformation(SoapObject o) {
		return new InformationBuilder().withContent(getString(o, "content"))
				.withCategory(new CategoryBuilder().withId(getId(o, "categoryId")).build())
				.withDate(getDate(o, "creationDate"))
				.withUserId(getLong(o, "userId"))
				.withId(getId(o, "id"))
				.withUserName(getString(o, "email"))
				.build();
	}	
}
