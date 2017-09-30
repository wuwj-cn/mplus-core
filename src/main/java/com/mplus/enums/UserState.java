package com.mplus.enums;

import java.util.Objects;

public enum UserState {

	ENABLE("正常"), DISABLED("停用");
	
	private final String name;

	private UserState(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
	
	public static UserState fromString(String name){
        Objects.requireNonNull(name, "value can not be null");
        switch (name) {
        case "0":
            return UserState.ENABLE;
        case "1":
            return UserState.DISABLED;
        default:
            throw new IllegalArgumentException("Name [" + name + "] not supported.");
        }
    }
}
