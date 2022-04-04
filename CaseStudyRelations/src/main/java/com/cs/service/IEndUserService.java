package com.cs.service;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.cs.bean.Address;
import com.cs.bean.EndUser;
import com.cs.dto.Register;
import com.cs.dto.RegisterOutputDto;

public interface IEndUserService {
	/*
	 * Function: Creation
	 * 
	 * Description: To add new Customer to Online Plant Nursery
	 * 
	 * Parameter: Register DTO 
	 * 
	 * Return: RegisterOutputDto
	 */
	RegisterOutputDto addCustomer(Register register);
	
	/*
	 * Function: Retrieval
	 *  
	 * Parameter: adminId
	 * 
	 * Return: List<RegisterOutputDto>
	 * 
	 * Description: To get all Customers registered in Online Plant Nursery
	 *              If Admin user is not logged in or it is not admin type then 
	 *              returns exceptions.
	 */
	List<RegisterOutputDto> getAllCustomerDto(Integer adminId);
	
	/*
	 * Function: Retrieval
	 *  
	 * Parameter: customerId
	 * 
	 * Return: EndUser
	 * 
	 * Description: To get a particular customer registered in Online Plant Nursery
	 *              If customer is not logged in or not registered in Online Plant Nursery
	 *              returns exceptions.
	 */
	EndUser getCustomerById(Integer customerId);

	/*
	 * Function: Retrieval
	 *  
	 * Parameter: adminId, fullName
	 * 
	 * Return: List<RegisterOutputDto>
	 * 
	 * Description: To get all Customers registered in Online Plant Nursery with
	 *              same fullName. If user is not logged in or not present then 
	 *              returns exceptions.
	 */
	List<RegisterOutputDto> getCustomersByName(Integer adminId, String fullName);

	/*
	 * Function: Retrieval
	 *  
	 * Parameter: adminId, emailId
	 * 
	 * Return: RegisterOutputDto
	 * 
	 * Description: To get customers registered in Online Plant Nursery with
	 *              emailId. If user is not logged in or not present then 
	 *              returns exceptions.
	 */
	RegisterOutputDto getCustomerByEmailId(Integer id, String emailId);

	
	/*
	 * Function: Retrieval
	 *  
	 * Parameter: adminId, mobileNumber
	 * 
	 * Return: RegisterOutputDto
	 * 
	 * Description: To get Customers registered in Online Plant Nursery with
	 *              mobileNumber. If user is not logged in or not present then 
	 *              returns exceptions.
	 */	
	RegisterOutputDto getCustomerByMobileNumber(Integer id, String mobileNumber);

	/*
	 * Function: Updation
	 *  
	 * Parameter: customerId, Name
	 * 
	 * Return: String
	 * 
	 * Description: To change the fullName of Customers registered in Online Plant Nursery.
	 *              If user is not logged in or not present then 
	 *              returns exceptions.
	 */	
	String updateName(Integer id,
			@NotEmpty(message = "Field must not be Empty") @Pattern(regexp = "[a-zA-Z[\\s]]{3,50}", message = "Name should not have any digit or special character") String fullName);

	/*
	 * Function: Updation
	 *  
	 * Parameter: customerId, mobileNumber
	 * 
	 * Return: String
	 * 
	 * Description: To change the mobileNumber of Customers registered in Online Plant Nursery.
	 *              If user is not logged in or not present then 
	 *              returns exceptions.
	 */		
	String updateMobileNumber(Integer id,
			@NotEmpty(message = "Field must not be Empty") @Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "Please enter valid mobile number") String mobileNumber);

	/*
	 * Function: Deletion
	 *  
	 * Parameter: adminId, customerId
	 * 
	 * Return: RegisterOutputDto
	 * 
	 * Description: To delete the Customers registered in Online Plant Nursery using
	 * 				adminId and customerId. If user is not logged in or not present then 
	 *              returns exceptions.
	 */		
	RegisterOutputDto deleteCustomerById(Integer adminid, Integer id);

	/*
	 * Function: Deletion
	 *  
	 * Parameter: emailId
	 * 
	 * Return: RegisterOutputDto
	 * 
	 * Description: To delete the Customers registered in Online Plant Nursery using
	 * 				customer's emailId. If user is not logged in or not present then 
	 *              returns exceptions.
	 */	
	RegisterOutputDto deleteCustomerByEmailId(String emailId);

	/*
	 * Function: Updation
	 *  
	 * Parameter: customerId, Address
	 * 
	 * Return: RegisterOutputDto
	 * 
	 * Description: To update the address of Customers registered in Online Plant Nursery.
	 *              If user is not logged in or not present then 
	 *              returns exceptions.
	 */	
	RegisterOutputDto updateCustomerAddress(Integer customerId, Address address);

	/*
	 * Function: Retrieval
	 *  
	 * Parameter: customerId
	 * 
	 * Return: int
	 * 
	 * Description: To get a particular customer's cart Id registered in Online Plant Nursery
	 *              If customer is not logged in or not registered in Online Plant Nursery
	 *              returns exceptions.
	 */	
	int getCustomerCartId(Integer customerId);

	/*
	 * Function: Retrieval
	 *  
	 * Parameter: customerId
	 * 
	 * Return: double
	 * 
	 * Description: To get a particular customer's cart price registered in Online Plant Nursery
	 *              If customer is not logged in or not registered in Online Plant Nursery
	 *              returns exceptions.
	 */		
	double getCustomerCartPrice(Integer customerId);
	
	/*
	 * Function: Updation
	 *  
	 * Parameter: customerId, Name
	 * 
	 * Return: String
	 * 
	 * Description: To change the password of Customers registered in Online Plant Nursery.
	 *              If user is not logged in or not present then 
	 *              returns exceptions.
	 */	
	public String updatePassword(String email, String oldPassword,
			@NotEmpty(message = "Field must not be Empty") @Pattern(regexp = "^(([a-z])|([A-Z])|([!@#$&*])|([0-9])){8,12}$", message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit and should be atleast 8 letters") String newPassword,
			String confirmNewPassword);
}
