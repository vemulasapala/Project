package com.cs.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cs.bean.LoginErrorResponse;

/** 
 * <h2> Login Exception Handler </h2> 
 * This class is used to handle exception which is generated with the
 * help of LoginErrorResponse Class(com.cs.bean.LoginErrorResponse) and
 * LoginException Class(com.cs.exception.LoginExceptions)
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 

@ControllerAdvice
public class LoginExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<LoginErrorResponse> handleException(LoginExceptions exception){
		LoginErrorResponse error = new LoginErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
