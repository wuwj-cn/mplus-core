package com.mplus.utils;

import java.util.Objects;

public enum DataState {
	ENABLE("0"), DELETED("1");
	
	private String cName;
	private DataState(String name) {
		this.cName = name;
	}
	@Override
	public String toString() {
		return this.cName;
	}
	
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	static DataState fromString(String name){
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
