package com.infoshare.service.providers;

import com.infoshare.authentication.AuthenticationService;
import com.infoshare.authentication.IAuthenticationService;
import com.infoshare.mock.MockAuthenticationService;
import com.infoshare.mock.MockRetriever;
import com.infoshare.mock.MockSoapService;
import com.infoshare.retrievers.IDataRetriever;
import com.infoshare.retrievers.ServiceRetriever;
import com.infoshare.soap.ISoapService;
import com.infoshare.soap.SoapService;

public class ServiceProvider {

	private static ISoapService mockSoapService = MockSoapService.getInstance();
	private static IDataRetriever mockDataRetriever = MockRetriever.getInstance();
	private static IAuthenticationService mockAuthenticationService = MockAuthenticationService.getInstance();
	
	private static ISoapService soapService = SoapService.getInstance();
	private static IDataRetriever dataRetriever = ServiceRetriever.getInstance();
	private static IAuthenticationService authenticationService = AuthenticationService.getInstance();
	
	private final static boolean shouldUseMock = false;
	
	public static IDataRetriever provideDataRetriever(){
		return shouldUseMock ? mockDataRetriever : dataRetriever;
	}
	
	public static IAuthenticationService provideAuthenticationService(){
		return shouldUseMock ? mockAuthenticationService : authenticationService;
	}
	
	public static ISoapService provideSoapService(){
		return shouldUseMock ? mockSoapService : soapService;
	}
}
