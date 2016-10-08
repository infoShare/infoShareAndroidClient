package com.infoshare.authentication;

import android.content.Context;

import com.infoshare.security.SecurityService;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.ISoapService;
import com.infoshare.soap.SoapResultHandler;

public class AuthenticationService implements IAuthenticationService {

	private static AuthenticationService authenticationService;
	private static ISoapService soapService = ServiceProvider.provideSoapService();
	
	public static IAuthenticationService getInstance() {
		if(authenticationService == null){
			authenticationService = new AuthenticationService();
		}
		return authenticationService;
	}
	
	@Override
	public void authenticate(Context ctx, String email, String password, SoapResultHandler<Integer> handler) {
		String passwordHash = SecurityService.calculateHash(password);
		soapService.authenticate(ctx, email, passwordHash, handler);
	}

	@Override
	public void register(Context ctx, String email, String password, SoapResultHandler<Integer> handler) {
		soapService.register(ctx, email, password, handler);
	}
	
}
