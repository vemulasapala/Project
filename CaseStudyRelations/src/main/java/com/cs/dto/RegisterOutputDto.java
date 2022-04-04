package com.cs.dto;

import com.cs.bean.Address;

import lombok.Data;

/*
 * This class acts as output DTO after an object is registered in Customer database.
 * 
 *  @author Sapala Srusti Vemula
 */

@Data
public class RegisterOutputDto {
	private String name;
	private String emailId;
	private String mobileNumber;
	private Integer cartId;
	private Address address;
}
