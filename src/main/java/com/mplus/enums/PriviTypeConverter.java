package com.mplus.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PriviTypeConverter implements AttributeConverter<PriviType, String> {
	@Override
	public String convertToDatabaseColumn(PriviType type) {
		return type.getCode();
	}

	@Override
	public PriviType convertToEntityAttribute(String dbData) {
		return PriviType.fromString(dbData);
	}
}
