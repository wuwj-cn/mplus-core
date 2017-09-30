package com.mplus.enums;

import java.util.Objects;

public enum PriviType {
	MENU("0"), OPERATION("1"), FILE("2"), ELEMENT("3");
	
	private final String name;

	private PriviType(String name) {
		this.name= name;
	}
	
	public String getName() {
		return name;
	}
	
	public static PriviType fromString(String name){
        Objects.requireNonNull(name, "value can not be null");
        switch (name) {
        case "0":
            return PriviType.MENU;
        case "1":
            return PriviType.OPERATION;
        case "2":
            return PriviType.FILE;
        case "3":
            return PriviType.ELEMENT;
        default:
            throw new IllegalArgumentException("Name [" + name + "] not supported.");
        }
    }
}
