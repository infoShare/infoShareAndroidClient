package com.infoshare.soap;

import java.util.List;

import org.ksoap2.serialization.PropertyInfo;

import android.content.Context;

import com.infoshare.model.AbstractIdentity.Id;
import com.infoshare.model.Category;
import com.infoshare.model.Information;
import com.infoshare.preferences.PreferencesService;

public class SoapService extends AbstractSoapService implements ISoapService {

	private static SoapService soapService;
	
	public static SoapService getInstance(){
		if(soapService == null){
			soapService = new SoapService();
		}
		return soapService;
	}
	
	@Override
	public void getInformations(final Context ctx, final String category, final SoapResultHandler<List<Information>> handler) {
		final PropertyInfo categoryPI = createProperty("category", category, String.class);
		executeTask(ctx, SoapDetails.GET_INFOS, handler, categoryPI);
	}

	@Override
	public void authenticate(final Context ctx, final String email, final String password, final SoapResultHandler<Integer> handler) {
		final PropertyInfo emailPI = createProperty("email", email, String.class);
		final PropertyInfo passwordPI = createProperty("password", password, String.class);
		executeTask(ctx, SoapDetails.AUTHENTICATION, handler, emailPI, passwordPI);
	}

	@Override
	public void register(Context ctx, String email, String password, SoapResultHandler<Integer> handler) {
		final PropertyInfo emailPI = createProperty("email", email, String.class);
		final PropertyInfo passwordPI = createProperty("password", password, String.class);
		executeTask(ctx, SoapDetails.REGISTER, handler, emailPI, passwordPI);
	}
	
	@Override
	public void addCategory(Context ctx, String categoryName, SoapResultHandler<Boolean> handler) {
		Long userId = PreferencesService.getLoggedUser(ctx);
		final PropertyInfo userPI = createProperty("userId", String.valueOf(userId), String.class);
		final PropertyInfo namePI = createProperty("name", categoryName, String.class);
		executeTask(ctx, SoapDetails.ADD_CATEGORY, handler, userPI, namePI);
	}

	@Override
	public void addInformation(Context ctx, String content, String categoryName, SoapResultHandler<Boolean> handler) {
		Long userId = PreferencesService.getLoggedUser(ctx);
		final PropertyInfo userPI = createProperty("userId", userId.toString(), String.class);
		final PropertyInfo categoryPI = createProperty("categoryName", categoryName, String.class);
		final PropertyInfo contentPI = createProperty("content", content, String.class);
		executeTask(ctx, SoapDetails.ADD_INFO, handler, userPI, categoryPI, contentPI);
	}

	@Override
	public void getCategories(Context ctx, SoapResultHandler<List<Category>> handler) {
		executeTask(ctx, SoapDetails.GET_CATEGORIES, handler);
	}

	@Override
	public void deleteInformation(Context ctx, Id id, SoapResultHandler<Boolean> handler) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
}
