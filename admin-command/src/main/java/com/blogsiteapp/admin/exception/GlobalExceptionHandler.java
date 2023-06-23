package com.blogsiteapp.admin.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.blogsiteapp.admin.model.ResponseParms;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ApiException.class)
	ResponseEntity<ResponseParms> handleException(ApiException e){
		ResponseParms apiError=new ResponseParms();
		apiError.setErrorcode(String.valueOf(e.getHttpStatus().value()));
		apiError.setErrormessage(e.getMessage());
		return new ResponseEntity<ResponseParms>(apiError, e.getHttpStatus());
	}
	
//	@ExceptionHandler(Exception.class)
//	ResponseEntity<String> handleExceptionForGenericException(Exception e){
//		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//	}
	
}
