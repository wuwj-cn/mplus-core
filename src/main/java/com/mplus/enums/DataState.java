package com.mplus.utils;

import java.util.Objects;

public enum DataState {
	ENABLE("0"), DELETED("1");
	
	private final String name;

	private DataState(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
	
	public static DataState fromString(String name){
        Objects.requireNonNull(name, "value can not be null");
        switch (name) {
        case "0":
            return DataState.ENABLE;
        case "1":
            return DataState.DELETED;
        default:
            throw new IllegalArgumentException("Name [" + name + "] not supported.");
        }
    }
}
