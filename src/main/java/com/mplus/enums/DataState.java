package com.mplus.enums;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DataState {
	ENABLE("0", "正常"), DELETED("1", "删除");
	
	private final String code;
	private final String name;

	private DataState(String code, String name) {
		this.code = code;
		this.name= name;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public static DataState fromString(String code){
        Objects.requireNonNull(code, "value can not be null");
        switch (code) {
        case "0":
            return DataState.ENABLE;
        case "1":
            return DataState.DELETED;
        default:
            throw new IllegalArgumentException("code [" + code + "] not supported.");
        }
    }
}
