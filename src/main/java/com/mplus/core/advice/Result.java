package com.mplus.core.advice;

public class Result {

	private boolean success;
	private Object data;

	private Result() {
	}

	public static Result sucess(Object data) {
		Result result = new Result();
		result.setSuccess(true);
		result.setData(data);
		return result;
	}

	public static Result failure(Object data) {
		Result result = new Result();
		result.setSuccess(false);
		result.setData(data);
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
