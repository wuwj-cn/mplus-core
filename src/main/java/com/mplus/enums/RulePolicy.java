package com.mplus.utils;

import java.util.Objects;

public enum RulePolicy {
	SERIAL("0"), // 流水号
	DATE("1"), // 日期
	DATE_SERIAL("2"); // 日期+流水号
	
	private String name;
	private RulePolicy(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	static RulePolicy fromString(String name){
        Objects.requireNonNull(name, "value can not be null");
        switch (name) {
        case "0":
            return RulePolicy.SERIAL;
        case "1":
            return RulePolicy.DATE;
        case "2":
            return RulePolicy.DATE_SERIAL;
        default:
            throw new IllegalArgumentException("Name [" + name + "] not supported.");
        }
    }
}
