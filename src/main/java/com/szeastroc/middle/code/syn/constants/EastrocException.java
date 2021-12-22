package com.szeastroc.middle.code.syn.constants;


/**
 * 通用业务异常
 * 
 * @author LiangHao
 *
 */
public class EastrocException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5208661835896619062L;

	private String code;

	private String message;

	private Object data;

	public EastrocException(String message) {
		this.code = ErrorCode.FAILED.getCode();
		this.message = message;
	}

	public EastrocException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public EastrocException(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public EastrocException(String message, Object data) {
		this.message = message;
		this.data = data;
		this.code = ErrorCode.FAILED.getCode();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
