package com.szeastroc.middle.code.syn.utils;

import lombok.Data;

@Data
public class Result<T> {

	private String msg;
	private String code;
	private T data;
}
