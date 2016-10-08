package com.infoshare.parsers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.ksoap2.serialization.SoapObject;

import com.infoshare.model.AbstractIdentity;
import com.infoshare.model.AbstractIdentity.Id;

public abstract class SoapParser<T> {
	
	abstract T parse(SoapObject response);
	
	protected Date getDate(SoapObject o, String property){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
		    return format.parse(getString(o, property));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	protected String getString(SoapObject o, String property){
		return o.getPrimitivePropertyAsString(property);
	}

	protected Long getLong(SoapObject o, String property){
		return Long.parseLong(getString(o, property));
	}
	
	protected Integer getInt(SoapObject o, String property){
		return Integer.parseInt(getString(o, property));
	}
	
	protected Id getId(SoapObject o, String property){
		return new AbstractIdentity().new Id(getLong(o, property));
	}
}
