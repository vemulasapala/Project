package com.cs.bean;

import java.time.LocalDateTime;

import lombok.Data;

/** 
 * <h2> Login Error Response </h2> 
 * This class is used to generate proper message with timeStamp
 * and status for login class in case if some exception is generated.
 * Class consist of 3 members message(String), status(Integer),
 * timeStamp(LocalDateTime)
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 
@Data
public class LoginErrorResponse {
	private int status;
	private String message;
	private LocalDateTime timeStamp;
}
