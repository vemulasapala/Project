package com.cs.exception;

/** 
 * <h2> Login Exceptions </h2> 
 * This class is used to override RuntimeException Class 
 * so that we can create our own exception for Login
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 

public class LoginExceptions extends RuntimeException{

	public LoginExceptions() {
		super();
		
	}

	public LoginExceptions(String message) {
		super(message);
		
	}

	public LoginExceptions(Throwable cause) {
		super(cause);
		
	}

}

