package com.mplus.enums;

import java.util.Objects;

public enum RuleCode {

	USER("USER");
	
	private final String name;

	private RuleCode(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
	
	public static RuleCode fromString(String name){
        Objects.requireNonNull(name, "value can not be null");
        switch (name) {
        case "USER":
            return RuleCode.USER;
        default:
            throw new IllegalArgumentException("Name [" + name + "] not supported.");
        }
    }
}
