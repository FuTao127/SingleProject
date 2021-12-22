package com.szeastroc.middle.code.syn.constants;

public enum ErrorCode {
	SUCCESS("200", "success"), ERROR("500", "系统错误"), FAILED("400", "业务型错误"), NO_LOGIN("401", "未登录"),
	NO_PERMISSION("403", "没有权限");

	private String code;
	private String msg;

	ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
