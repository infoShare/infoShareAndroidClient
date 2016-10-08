package com.infoshare.retrievers;

import java.util.List;

import android.content.Context;

import com.infoshare.model.AbstractIdentity.Id;
import com.infoshare.model.Category;
import com.infoshare.model.Information;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.ISoapService;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.DateUtils;

public class ServiceRetriever implements IDataRetriever {

	private static ServiceRetriever serviceRetriever;
	private static ISoapService soapService = ServiceProvider.provideSoapService();
	
	public static IDataRetriever getInstance() {
		if(serviceRetriever == null){
			serviceRetriever = new ServiceRetriever();
		}
		return serviceRetriever;
	}
	
	@Override
	public void updateInfoList(Context ctx, String category, SoapResultHandler<List<Information>> handler) {
		if(category!=null){
			soapService.getInformations(ctx, category, handler);
			PreferencesService.saveToPrefs(ctx, PreferencesService.LAST_UPDATE_KEY, DateUtils.getCurrentDate());
		}
	}

	@Override
	public void updateCategories(Context ctx, SoapResultHandler<List<Category>> handler) {
		soapService.getCategories(ctx, handler);
	}

	@Override
	public void deleteInformation(Context ctx, Id id, SoapResultHandler<Boolean> handler) {
		soapService.deleteInformation(ctx, id, handler);
		
	}

	@Override
	public void addInformation(Context ctx, String content, String categoryName, SoapResultHandler<Boolean> handler) {
		soapService.addInformation(ctx, content, categoryName, handler);
	}

	@Override
	public void addCategory(Context ctx, String category, SoapResultHandler<Boolean> handler) {
		soapService.addCategory(ctx, category, handler);	
	}
}
