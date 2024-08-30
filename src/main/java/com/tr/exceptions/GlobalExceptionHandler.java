package com.tr.exceptions;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String,Object>> handleNotFoundException(NotFoundException ex){
		
		Map<String, Object> errorDetails=new HashMap<String, Object>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("code", ex.getCode());
		errorDetails.put("messgae", ex.getMessage());
		errorDetails.put("details", "Resource not Found");
		
		return new ResponseEntity<Map<String,Object>>(errorDetails,HttpStatus.NOT_FOUND);
		
	}
}
