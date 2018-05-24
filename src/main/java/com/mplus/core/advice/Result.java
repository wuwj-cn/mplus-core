package com.mplus.core.advice;

import com.mplus.enums.ResponseStatus;

public class Result {

	private int status;
	private String msg;
	private Object data;

	private Result() {
	}

	public static Result sucess(Object data) {
		Result result = new Result();
		result.setStatus(ResponseStatus.SUCCESS.value());
		result.setMsg(ResponseStatus.SUCCESS.getDesc());
		result.setData(data);
		return result;
	}

	public static Result failure(int stauts, String msg) {
		Result result = new Result();
		result.setStatus(stauts);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
