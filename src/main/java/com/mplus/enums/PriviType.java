package com.mplus.enums;

import java.util.Objects;

public enum PriviType {
	MENU("0", "菜单"), OPERATION("1", "功能操作"), FILE("2", "文件"), ELEMENT("3", "页面元素");
	
	private final String code;
	private final String name;

	private PriviType(String code, String name) {
		this.code = code;
		this.name= name;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public static PriviType fromString(String code){
        Objects.requireNonNull(code, "value can not be null");
        switch (code) {
        case "0":
            return PriviType.MENU;
        case "1":
            return PriviType.OPERATION;
        case "2":
            return PriviType.FILE;
        case "3":
            return PriviType.ELEMENT;
        default:
            throw new IllegalArgumentException("code [" + code + "] not supported.");
        }
    }
}
