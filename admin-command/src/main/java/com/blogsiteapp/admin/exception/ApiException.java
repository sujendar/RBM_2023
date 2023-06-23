package com.blogsiteapp.admin.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {
	
	private HttpStatus httpStatus;
	
	public ApiException(String message,HttpStatus status) {
		super(message);
		this.httpStatus=status;
	}
	
	public ApiException(Exception e,HttpStatus status) {
		super(e);
		this.httpStatus=status;
	}
	
	
	public ApiException(String message,Exception e,HttpStatus status) {
		super(message,e);
		this.httpStatus=status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
