package com.enigmacamp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommonResponseError {

	@Schema(description = "Response status code.")
	private String status;
	
	@Schema(description = "Response message")
	private String message;

	public CommonResponseError() {
		super();
	}

	public CommonResponseError(String status, String message) {
		super();
		this.status = status;
		this.message = message;
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
}
