package com.mplus.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum DataState {
	ENABLE("0", "正常"), DELETED("1", "删除");

	private String code;
	private String name;

	private DataState(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final Map<String, DataState> MAP = new HashMap<String, DataState>();
    static {
        for (DataState e : DataState.values()) {
            MAP.put(e.getCode(), e);
        }
    }
    
	public static DataState fromString(String code) {
		Objects.requireNonNull(code, "value can not be null");
		DataState e = MAP.get(code);
		if(null == e) throw new IllegalArgumentException("code [" + code + "] not supported.");
		return e;
	}

//	@Override
//	public String toString() {
//		// "dataState":{"code":"0","name":"正常"}
//		return "{\"code\":\"" + code + "\", \"name\":\"" + name + "\"}";
//	}
}
