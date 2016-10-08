package com.infoshare.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.content.Context;

import com.infoshare.builders.CategoryBuilder;
import com.infoshare.builders.InformationBuilder;
import com.infoshare.model.AbstractIdentity.Id;
import com.infoshare.model.Category;
import com.infoshare.model.Information;
import com.infoshare.retrievers.IDataRetriever;
import com.infoshare.soap.SoapResultHandler;

public class MockRetriever extends AbstractMock implements IDataRetriever {

	private static IDataRetriever dataRetriever;
	
	public static IDataRetriever getInstance() {
		if(dataRetriever==null){
			dataRetriever = new MockRetriever();
		}
		return dataRetriever;
	}
	
	private List<Category> categories = new ArrayList<Category>(Arrays.asList(
			new CategoryBuilder().withUserId(1).withDate(Calendar.getInstance().getTime())
				.withName("Lublin").build(),
			new CategoryBuilder().withUserId(2).withDate(Calendar.getInstance().getTime())
				.withName("Krakow").build(),
			new CategoryBuilder().withUserId(3).withDate(Calendar.getInstance().getTime())
				.withName("Warszawa").build(),
			new CategoryBuilder().withUserId(1).withDate(Calendar.getInstance().getTime())
				.withName("Rzochow").build()
	));
	
	private List<Information> informations = new ArrayList<Information>(Arrays.asList(
				new InformationBuilder().withContent("Mock 1").withCategory(categories.get(1))
					.withUserId(1L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 2").withCategory(categories.get(1))
					.withUserId(2L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 3").withCategory(categories.get(1))
					.withUserId(3L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 4").withCategory(categories.get(2))
					.withUserId(4L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 5").withCategory(categories.get(2))
					.withUserId(5L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 6").withCategory(categories.get(2))
					.withUserId(1L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 7").withCategory(categories.get(3))
					.withUserId(2L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 8").withCategory(categories.get(3))
					.withUserId(3L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 9").withCategory(categories.get(3))
					.withUserId(4L).withDate(Calendar.getInstance().getTime()).build(),
				new InformationBuilder().withContent("Mock 10").withCategory(categories.get(3))
					.withUserId(5L).withDate(Calendar.getInstance().getTime()).build()
	));	
	public void addInfo(Information info){
		informations.add(info);
	}
	
	public void deleteInfo(Information.Id infoId){
		Iterator<Information> it = informations.iterator();
		while(it.hasNext()){
			Information info = it.next();
			if(info.getId().equals(infoId)){
				it.remove();
				return;
			}
		}
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void addCategory(Category category){
		categories.add(category);
	}


	@Override
	public void updateInfoList(Context ctx, String category,SoapResultHandler<List<Information>> handler) {
		log("MockRetriever.updateInfoList");
	}

	@Override
	public void deleteInformation(Context ctx, Id id, SoapResultHandler<Boolean> handler) {
		log("MockRetriever.deleteInformation");
	}

	@Override
	public void updateCategories(Context ctx, SoapResultHandler<List<Category>> handler) {
		log("MockRetriever.updateCategories");
	}

	@Override
	public void addCategory(Context ctx, String category, SoapResultHandler<Boolean> handler) {
		log("MockRetriever.addCategory");
		
	}

	@Override
	public void addInformation(Context ctx, String content, String categoryName, SoapResultHandler<Boolean> handler) {
		log("MockRetriever.addInformation");
	}

}
