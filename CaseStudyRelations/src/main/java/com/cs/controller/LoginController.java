package com.cs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cs.bean.Login;
import com.cs.dto.LoginDto;
import com.cs.service.ILoginService;

/** 
 * <h2> Login Controller </h2> 
 * This class is used as controller to add, login, logout users and
 * administrator. Also this controller gives functionality to reset your password
 * in case user/administrator forgets password and user/Administrator can also
 * change their passwords.
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 

@RestController
public class LoginController {
	@Autowired
	ILoginService loginServ;
	
	//add email, password to database
	@PostMapping("/user/login")
	ResponseEntity<LoginDto> addLogin(@Valid @RequestBody Login loginDetails){
		LoginDto login = loginServ.addLogin(loginDetails);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
	
	//if email and password is correct make isLogin true
	@PostMapping("/user/loginNet")
	ResponseEntity<LoginDto> login(@RequestBody Login loginDetails){
		LoginDto login = loginServ.loginNetwork(loginDetails);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}
	
	//used to logout that is make isLogin false
	@GetMapping("/user/logout/{email}")
	ResponseEntity<LoginDto> logout(@PathVariable("email") String emailId) {
		LoginDto login = loginServ.logoutNetwork(emailId);
		return new ResponseEntity<>(login, HttpStatus.OK);
	};
	
	//used to reset password will return a string with message password reset to 12345678
	@GetMapping("/user/resetPassword/{email}")
	ResponseEntity<String> resetPass(@PathVariable("email") String emailId) {
		String mssg =  loginServ.resetPassword(emailId);
		return new ResponseEntity<>(mssg, HttpStatus.OK);
	}
	
	//used to reset password in case someone forgets password
	@GetMapping("/user/forgotPassword/{email}")
	ResponseEntity<String> forgotPass(@PathVariable("email") String emailId) {
		String mssg = loginServ.forgotPassword(emailId);
		return new ResponseEntity<>(mssg, HttpStatus.OK);
	}
	
	//used to change password
	@PostMapping("/user/changePassword")
	ResponseEntity<LoginDto> changePass(@RequestBody Login loginDetails, String newPass) {
		LoginDto loginChange = loginServ.changePassword(loginDetails, newPass);
		return new ResponseEntity<>(loginChange, HttpStatus.ACCEPTED);
	}
}
