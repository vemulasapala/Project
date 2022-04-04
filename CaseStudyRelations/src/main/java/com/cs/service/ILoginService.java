package com.cs.service;

import com.cs.bean.Login;
import com.cs.dto.LoginDto;

/** 
 * <h2> Login Service </h2> 
 * This interface is used to provide the service
 * to loginController so that we can add, login, logout
 * reset or change password using database. This interface 
 * provides us with all the functionality that we can do 
 * using LoginController.
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 

public interface ILoginService {
	LoginDto addLogin(Login addL);
	LoginDto loginNetwork(Login loginDetails);
	LoginDto logoutNetwork(String emailId);
	String resetPassword(String emailId);
	String forgotPassword(String emailId);
	LoginDto changePassword(Login loginDetails, String newPass);
}
