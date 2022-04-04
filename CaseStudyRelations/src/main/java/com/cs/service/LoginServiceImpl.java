package com.cs.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.bean.Login;
import com.cs.dto.LoginDto;
import com.cs.exception.LoginExceptions;
import com.cs.repository.ILoginRepository;

/** 
 * <h2> Login Service Implementation</h2> 
 * This class implements the interface ILoginService
 * the description is provided in the interface
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	ILoginRepository loginRepo;

	//validation using regex for email
	public Boolean patternMatches(String email) {
		Pattern p = Pattern.compile("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	//add login credentials to database made for initial testing only
	@Override
	public LoginDto addLogin(Login addL) {
		if (patternMatches(addL.getEmail()) == false) {
			throw new LoginExceptions("Please enter a valid email");
		}

		loginRepo.save(addL);

		LoginDto loginD = new LoginDto();
		loginD.setEmail(addL.getEmail());
		loginD.setLogin(addL.isLogin());
		return loginD;
	}

	//used to login in the network this method will set isLogin variable to true if email and password is correct
	@Override
	public LoginDto loginNetwork(Login loginDetails) {
		Optional<Login> dbLoginOpt = loginRepo.findById(loginDetails.getEmail());

		if (!dbLoginOpt.isPresent()) {
			throw new LoginExceptions("No login credentials exist for emailId: " + loginDetails.getEmail());
		}

		Login dbLogin = dbLoginOpt.get();
		if (!dbLogin.getPassword().equals(loginDetails.getPassword())) {
			throw new LoginExceptions("Incorrect password for emailId: " + loginDetails.getEmail());
		} else {
			dbLogin.setLogin(true);
			loginRepo.save(dbLogin);
		}

		LoginDto loginD = new LoginDto();
		loginD.setEmail(dbLogin.getEmail());
		loginD.setLogin(dbLogin.isLogin());
		return loginD;
	}

	//this method will set isLogin variable to false in database that is user will logout from the application
	@Override
	public LoginDto logoutNetwork(String emailId) {
		Optional<Login> dbLogoutOpt = loginRepo.findById(emailId);

		if (!dbLogoutOpt.isPresent()) {
			throw new LoginExceptions("No login credentials exist for emailId: " + emailId);
		}

		Login dbLogout = dbLogoutOpt.get();
		dbLogout.setLogin(false);
		loginRepo.save(dbLogout);

		LoginDto loginD = new LoginDto();
		loginD.setEmail(dbLogout.getEmail());
		loginD.setLogin(dbLogout.isLogin());
		return loginD;
	}

	//this method will reset the password to 12345678
	@Override
	public String resetPassword(String emailId) {
		Optional<Login> loginDetailsOpt = loginRepo.findById(emailId);

		if (!loginDetailsOpt.isPresent()) {
			throw new LoginExceptions("No login credentials exist for emailId: " + emailId);
		}

		Login loginDetails = loginDetailsOpt.get();
		loginDetails.setPassword("12345678");
		loginRepo.save(loginDetails);
		return "Password reset to 12345678. Please change your password as soon as possible!";

	}

	//this method will invoke resetPassword method and will reset the password to 12345678
	@Override
	public String forgotPassword(String emailId) {
		Optional<Login> loginDetailsOpt = loginRepo.findById(emailId);

		if (!loginDetailsOpt.isPresent()) {
			throw new LoginExceptions("No login credentials exist for emailId: " + emailId);
		}

		return resetPassword(emailId);
	}

	//this method will change the password by new Password passed by user and will update it in the database
	@Override
	public LoginDto changePassword(Login loginDetails, String newPass) {
		Optional<Login> loginChangeOpt = loginRepo.findById(loginDetails.getEmail());

		if (!loginChangeOpt.isPresent()) {
			throw new LoginExceptions("No login credentials exist for emailId: " + loginDetails.getEmail());
		}

		Login loginChange = loginChangeOpt.get();
		if (!loginChange.getPassword().equals(loginDetails.getPassword())) {
			throw new LoginExceptions("Incorrect password for emailId: " + loginDetails.getEmail());
		}

		else if (loginChange.getPassword().equals(loginDetails.getPassword())) {
			loginChange.setPassword(newPass);
			loginRepo.save(loginChange);
		}

		LoginDto loginD = new LoginDto();
		loginD.setEmail(loginChange.getEmail());
		loginD.setLogin(loginChange.isLogin());
		return loginD;
	}

}

