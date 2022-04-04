package com.cs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * <h2> Login DTO </h2> 
 * This class is used to show limited information as output, through
 * this class we will show only email and isLogin information as output
 * password will be not shown
 *      
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	private String email;
	private boolean isLogin;
}
