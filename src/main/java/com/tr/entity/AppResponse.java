package com.tr.entity;

public class AppResponse {
	
	private String code;
	private String message;
	public AppResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	

}
