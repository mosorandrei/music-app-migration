package com.enigmacamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class CommonResponse<T> {

	@Schema(description = "Response status code.")
	private String status = "200";
	
	@Schema(description = "Response message")
	private String message = "Ok";
	
	@Schema(description = "Response data")
	private T data;

	public CommonResponse() {
		super();
	}
	
	public CommonResponse(T data) {
		this.data = data;
	}

	public CommonResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public CommonResponse(String status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
