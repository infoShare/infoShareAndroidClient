package com.infoshare.builders;

import org.ksoap2.serialization.PropertyInfo;

public class PropertyInfoBuilder {

	private String name;
	private String value;
	private Object type;
	
	public PropertyInfoBuilder(){
	}
	
	public PropertyInfoBuilder withName(String name){
		this.name = name;
		return this;
	}
	
	public PropertyInfoBuilder withValue(String value){
		this.value = value;
		return this;
	}
	
	public PropertyInfoBuilder withType(Object type){
		this.type = type;
		return this;
	}
	
	public PropertyInfo build(){
		final PropertyInfo property = new PropertyInfo();
		property.setName(name);
		property.setValue(value);
		property.setType(type);
		return property;
	}

}
