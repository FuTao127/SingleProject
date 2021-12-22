package com.szeastroc.middle.code.syn.utils;


import com.szeastroc.middle.code.syn.constants.ErrorCode;
import com.szeastroc.middle.code.syn.constants.EastrocException;
import org.apache.commons.lang3.StringUtils;

public class ResultUtils {

	public static Result<Object> render(Object data) {
		Result<Object> result = new Result<Object>();
		result.setData(data);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMsg());
		return result;
	}

	public static Result<Object> render(String msg, Object data) {
		Result<Object> result = new Result<Object>();
		result.setData(data);
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(msg);
		return result;
	}

	public static Result<Object> render(String msg, String code, Object data) {
		Result<Object> result = new Result<Object>();
		result.setData(data);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static Result<Object> render(String msg, String code) {
		Result<Object> result = new Result<Object>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static Result<Object> render() {
		Result<Object> result = new Result<Object>();
		result.setCode(ErrorCode.SUCCESS.getCode());
		result.setMsg(ErrorCode.SUCCESS.getMsg());
		return result;
	}

	public static Result<Object> renderError(String msg) {
		Result<Object> result = new Result<Object>();
		result.setCode(ErrorCode.FAILED.getCode());
		result.setMsg(msg);
		return result;
	}

	public static Result<Object> noLoginError(String msg) {
		Result<Object> result = new Result<Object>();
		result.setCode(ErrorCode.NO_LOGIN.getCode());
		result.setMsg(msg);
		return result;
	}

	public static Result<Object> renderError(String msg, String code) {
		Result<Object> result = new Result<Object>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static <T> T getData(Result<T> result) {
		if (!StringUtils.equals(result.getCode(), ErrorCode.SUCCESS.getCode())) {
			throw new EastrocException(result.getMsg());
		}
		return result.getData();
	}

}
