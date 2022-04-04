package com.cs.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * This class as input DTO for EndUser entity.
 * It is used for registering endUser into database.
 * 
 * @author Sapala Srusti Vemula
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
	@NotEmpty(message = "Field must not be Empty")
	@Pattern(regexp = "[a-zA-Z[\\s]]{3,50}", message = "Name should not have any digit or special character")
	private String name;
	@NotEmpty(message = "Field must not be Empty")
	@Email
	private String emailId;
	@NotEmpty(message = "Field must not be Empty")
	@Pattern(regexp = "^(([a-z])|([A-Z])|([!@#$&*])|([0-9])){8,12}$", message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit and should be atleast 8 letters")
	private String password;
	@NotEmpty(message = "Field must not be Empty")
	private String confirmPassword;
	@NotEmpty(message = "Field must not be Empty")
	@Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "Please enter valid mobile number")
	private String mobileNumber;
	@JsonIgnore
	private boolean isAdmin = false;
}
