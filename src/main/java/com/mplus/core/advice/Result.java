package com.mplus.core.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class Result {
	private static final Logger logger = LoggerFactory.getLogger(Result.class);

	private boolean success;
	private Object data;

	private Result() {
	}

	public static Result sucess(Object data) {
		Result result = new Result();
		result.setSuccess(true);
		result.setData(data);
		logger.info("result: " + result.toString());
		return result;
	}

	public static Result failure(Object data) {
		Result result = new Result();
		result.setSuccess(false);
		result.setData(data);
		logger.info("result: " + result.toString());
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

	@Override
	public String toString() {
		return "[success=" + success + ", data=" + JSON.toJSONString(data) + "]";
	}
}
