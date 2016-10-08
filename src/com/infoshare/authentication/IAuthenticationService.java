package com.infoshare.authentication;

import android.content.Context;

import com.infoshare.soap.SoapResultHandler;


public interface IAuthenticationService {

	void authenticate(Context ctx, String email, String password, SoapResultHandler<Integer> handler);
	void register(Context ctx, String email, String password, SoapResultHandler<Integer> handler);
}
