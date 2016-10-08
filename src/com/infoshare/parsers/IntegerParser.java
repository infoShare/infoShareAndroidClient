package com.infoshare.parsers;

import org.ksoap2.serialization.SoapObject;

public class IntegerParser extends SoapParser<Integer> {

	@Override
	public Integer parse(SoapObject response) {
		return Integer.valueOf(response.getPropertyAsString(0));
	}


}
