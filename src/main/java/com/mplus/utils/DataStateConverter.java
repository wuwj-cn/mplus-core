package com.mplus.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DataStateConverter implements AttributeConverter<DataState, String> {

	@Override
	public String convertToDatabaseColumn(DataState state) {
		return state.getcName();
	}

	@Override
	public DataState convertToEntityAttribute(String dbData) {
		return DataState.fromString(dbData);
	}
	
}
