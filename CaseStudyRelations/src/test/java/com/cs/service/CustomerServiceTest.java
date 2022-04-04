package com.cs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cs.bean.Address;
import com.cs.bean.EndUser;
import com.cs.dto.Register;
import com.cs.dto.RegisterOutputDto;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	IEndUserService endUserService;

	@Test
	@Disabled
	void addCustomerTest() {
		Register register = new Register("Abhilash", "abhi125@gmail.com", "Abhilash@12", "Abhilash@12", "9566464657",
				false);
		RegisterOutputDto registerDto = endUserService.addCustomer(register);
		assertEquals("Abhilash", registerDto.getName());
		assertEquals("abhi1245@gmail.com", registerDto.getEmailId());
	}

	@Test
	@Disabled
	void getCustomerDtoByIdTest() {
		EndUser endUser = endUserService.getCustomerById(7);
		assertEquals("Abhilash", endUser.getFullName());
		assertEquals("abhi1245@gmail.com", endUser.getLogin().getEmail());
		assertEquals("9546464657", endUser.getMobileNumber());
	}
	
	@Test
	@Disabled
	void getCustomerByNameTest() {
		List<RegisterOutputDto> registerDtos = endUserService.getCustomersByName(3,"Karthik");
		assertEquals(1, registerDtos.size());
	}
	
	@Test
	@Disabled
	void getCustomerByEmailIdTest() {
		RegisterOutputDto registerDto = endUserService.getCustomerByEmailId(7,"karthikia@gmail.com");
		assertEquals("karthikia@gmail.com", registerDto.getEmailId());
		assertEquals("8789948682", registerDto.getMobileNumber());
	}
	
	@Test
	@Disabled
	void getCustomerByMobileNumberTest() {
		RegisterOutputDto registerDto = endUserService.getCustomerByMobileNumber(7,"8789948682");
		assertEquals("Karthik Aluri", registerDto.getName());
		assertEquals("karthikia@gmail.com", registerDto.getEmailId());
		assertEquals("8789948682", registerDto.getMobileNumber());
	}
	
	@Test
	void getAllCustomersDtoTest() {
		List<RegisterOutputDto> customersDto = endUserService.getAllCustomerDto(7);
		assertEquals(3, customersDto.size());
	}
	
	@Test
	@Disabled
	void updateNameTest() {
		String output = endUserService.updateName(3, "Karthik Aluri");
		assertEquals("Full name is successfully updated", output);
	}
	
	@Test
	@Disabled
	void updateMobileNumber() {
		String output = endUserService.updateMobileNumber(3, "9175787574");
		assertEquals("Mobile Number is successfully updated", output);
	}
	
	@Test
	@Disabled
	void updateCustomerAddressTest() {
		Address address = new Address(103, "Estate", "Uppal", "Hyd", "Ts", 501010, "India");
		RegisterOutputDto registerDto = endUserService.updateCustomerAddress(3, address);
		assertEquals("karthikia@gmail.com", registerDto.getEmailId());
		assertEquals("Karthik Aluri", registerDto.getName());
		assertEquals("9175787574", registerDto.getMobileNumber());
		assertEquals(39, registerDto.getCartId());
		assertEquals("Estate", registerDto.getAddress().getBuildingName());
		assertEquals("Hyd", registerDto.getAddress().getCity());
		assertEquals("Uppal", registerDto.getAddress().getColony());
		assertEquals("Hyd", registerDto.getAddress().getCity());
		assertEquals(103, registerDto.getAddress().getFlatNum());
		assertEquals("India", registerDto.getAddress().getCountry());
		assertEquals(501010, registerDto.getAddress().getPincode());

	}
	

	@Test
	void getCustomerCartId() {
		Integer cartId = endUserService.getCustomerCartId(3);
		assertEquals(39, cartId);
	}
	
	@Test
	void getCustomerCartPrice() {
		Double cartPrice = endUserService.getCustomerCartPrice(3);
		assertEquals(0, cartPrice);
	}
	
	@Test
	@Disabled
	void updatePassword() {
		String message = endUserService.updatePassword("karthikia@gmail.com", "Karthik@34", "@Karthi!56", "@Karthi!56");
		assertEquals("Password is updated successfully", message);
	}
	
	@Test
	@Disabled
	void deleteCustomerByIdTest() {
		RegisterOutputDto registerDto = endUserService.deleteCustomerById(7,6);
		assertEquals("string", registerDto.getName());
		assertEquals("string@gmail.com", registerDto.getEmailId());
		assertEquals("8687828381", registerDto.getMobileNumber());
	}


	@Test
	@Disabled
	void deleteCustomerByEmailIdTest() {
		RegisterOutputDto registerDto = endUserService.deleteCustomerByEmailId("string1546@gmail.com");
		assertEquals("string", registerDto.getName());
		assertEquals("string1546@gmail.com", registerDto.getEmailId());

	}

	
}
