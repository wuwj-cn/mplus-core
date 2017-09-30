package com.mplus.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RulePolicyConverter implements AttributeConverter<RulePolicy, String> {

	@Override
	public String convertToDatabaseColumn(RulePolicy policy) {
		return policy.getName();
	}

	@Override
	public RulePolicy convertToEntityAttribute(String dbData) {
		return RulePolicy.fromString(dbData);
	}
	
}
