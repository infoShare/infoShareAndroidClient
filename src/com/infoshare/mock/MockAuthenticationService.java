package com.infoshare.mock;

import android.content.Context;

import com.infoshare.authentication.IAuthenticationService;
import com.infoshare.soap.SoapResultHandler;


public class MockAuthenticationService extends AbstractMock implements IAuthenticationService {

	private static IAuthenticationService authenticationService;
	
	public static IAuthenticationService getInstance() {
		if(authenticationService == null){
			authenticationService = new MockAuthenticationService();
		}
		return authenticationService;
	}

	@Override
	public void authenticate(Context ctx, String email, String password, SoapResultHandler<Integer> handler){
		log("MockAuthenticationService.authenticate");
	}

	@Override
	public void register(Context ctx, String email, String password, SoapResultHandler<Integer> handler) {
		log("MockAuthenticationService.register");
	}
}
