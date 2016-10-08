package com.infoshare.parsers;

import org.ksoap2.serialization.SoapObject;

public class BooleanParser extends SoapParser<Boolean>{

	@Override
	public Boolean parse(SoapObject response) {
		return Boolean.valueOf(response.getPropertyAsString(0));
	}

}
