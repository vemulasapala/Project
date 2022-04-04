package com.cs.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/** 
 * <h2> Login Entity </h2> 
 * This class implements functionality of login 
 * for administrators and users.
 * Consist of 3 members email(String), password(String),
 * isLogin(boolean)
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */  

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "FieldHandler" })
public class Login {
	@Id
	private String email;
	@Size(min = 8, message = "Please enter password with minimum 8 characters")
	@NotEmpty(message = "Please enter password")
	private String password;
	@JsonIgnore
	private boolean isLogin;
}
