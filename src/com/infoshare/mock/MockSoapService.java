package com.infoshare.mock;

import java.util.List;

import android.content.Context;

import com.infoshare.model.AbstractIdentity.Id;
import com.infoshare.model.Category;
import com.infoshare.model.Information;
import com.infoshare.soap.ISoapService;
import com.infoshare.soap.SoapResultHandler;


public class MockSoapService extends AbstractMock implements ISoapService {

	private static ISoapService soapService;
	
	public static ISoapService getInstance() {
		if(soapService == null){
			soapService = new MockSoapService();
		}
		return soapService;
	}

	@Override
	public void authenticate(Context ctx, String email, String password, SoapResultHandler<Integer> handler) {
		log("MockSoapService.authorize");
	}

	@Override
	public void register(Context ctx, String email, String password, SoapResultHandler<Integer> handler) {
		log("MockSoapService.register");
	}

	@Override
	public void getInformations(Context ctx, String category, SoapResultHandler<List<Information>> handler) {
		log("MockSoapService.getInformations");
	}

	@Override
	public void addCategory(Context ctx, String category, SoapResultHandler<Boolean> handler) {
		log("MockSoapService.addCategory");
	}

	@Override
	public void addInformation(Context ctx, String content, String categoryName, SoapResultHandler<Boolean> handler) {
		log("MockSoapService.addInformation");
	}

	@Override
	public void getCategories(Context ctx, SoapResultHandler<List<Category>> handler) {
		log("MockSoapService.getCategories");
	}

	@Override
	public void deleteInformation(Context ctx, Id id, SoapResultHandler<Boolean> handler) {
		log("MockSoapService.deleteInformation");
	}

}
