package com.infoshare.soap;

import org.ksoap2.serialization.SoapObject;


public interface SoapResultHandler<T>{
	void handle(SoapObject response);
	T parseResponse(SoapObject response);
}
