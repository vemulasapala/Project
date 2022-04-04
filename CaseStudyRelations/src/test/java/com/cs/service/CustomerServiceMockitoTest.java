package com.cs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cs.bean.Address;
import com.cs.bean.Cart;
import com.cs.bean.EndUser;
import com.cs.bean.Login;
import com.cs.dto.Register;
import com.cs.dto.RegisterOutputDto;
import com.cs.repository.IAddressRepository;
import com.cs.repository.ICartRepository;
import com.cs.repository.IEndUserRepository;
import com.cs.repository.ILoginRepository;

@ExtendWith(SpringExtension.class)
public class CustomerServiceMockitoTest {

	@InjectMocks
	EndUserServiceImpl customerService;

	@MockBean
	IEndUserRepository endUserRepository;

	@MockBean
	ILoginRepository loginRepository;

	@MockBean
	ICartRepository cartRepository;

	@MockBean
	IAddressRepository addressRepository;

	RegisterOutputDto registerDto;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addCustomerTest() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());		
		customer.setMobileNumber(register.getMobileNumber());
		Mockito.when(endUserRepository.save(customer)).thenReturn(customer);
		RegisterOutputDto registerDto = customerService.addCustomer(register);
		assertEquals("Arun", registerDto.getName());
		assertEquals("ardoing@gmail.com", registerDto.getEmailId());

	}

	@Test
	void getCustomerByIdTest() throws Exception {		
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);	
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		EndUser valueCustomer = customerService.getCustomerById(22);		
		assertEquals("Arun", valueCustomer.getFullName());
		assertEquals("ardoing@gmail.com", valueCustomer.getLogin().getEmail());
	}
	
	@Test
	void getCustomerByNameTest() {
		EndUser admin=new EndUser();
		admin.setFullName("Ashish");
		admin.setMobileNumber("7979874545");
		admin.setAdmin(true);
		Login login2 = new Login();
		admin.setLogin(login2);
		admin.getLogin().setEmail("ashish32@gmail.com");
		admin.getLogin().setPassword("@$Ashish56");
		admin.getLogin().setLogin(true);
		admin.setId(1);
		Cart cart3 = new Cart();
		cart3.setCartId(2);
		admin.setCart(cart3);
		Mockito.when(endUserRepository.findById(1)).thenReturn(Optional.of(admin));
		Mockito.when(endUserRepository.getById(1)).thenReturn(admin);
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Register register1 = new Register("Arun", "ardone@gmail.com", "ardoin@1", "ardoin@1", "8787878886", false);
		EndUser customer2 = new EndUser();
		customer2.setFullName(register1.getName());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		Login login1 = new Login();
		customer2.setLogin(login1);
		customer2.getLogin().setEmail(register1.getEmailId());
		customer2.getLogin().setPassword(register1.getPassword());
		Cart cart1 = new Cart();
		cart1.setCartId(2);
		customer2.setCart(cart1);
		customer2.setMobileNumber(register1.getMobileNumber());
		customer2.setAdmin(false);
		customer2.setId(23);
		List<EndUser> customers = new ArrayList<>();
		customers.add(customer);
		customers.add(customer2);
		Mockito.when(endUserRepository.getAllByFullName("Arun")).thenReturn(customers);
		List<RegisterOutputDto> resultCustomers = customerService.getCustomersByName(1,"Arun");
		assertEquals(2, resultCustomers.size());
	}
	
	
	@Test
	void getCustomerByEmailIdTest() {
		EndUser admin=new EndUser();
		admin.setFullName("Ashish");
		admin.setMobileNumber("7979874545");
		admin.setAdmin(true);
		Login login2 = new Login();
		admin.setLogin(login2);
		admin.getLogin().setEmail("ashish32@gmail.com");
		admin.getLogin().setPassword("@$Ashish56");
		admin.getLogin().setLogin(true);
		admin.setId(1);
		Cart cart3 = new Cart();
		cart3.setCartId(2);
		admin.setCart(cart3);
		Mockito.when(endUserRepository.findById(1)).thenReturn(Optional.of(admin));
		Mockito.when(endUserRepository.getById(1)).thenReturn(admin);
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);	
		Mockito.when(endUserRepository.getByEmailId("ardoing@gmail.com")).thenReturn(customer);
		RegisterOutputDto regiterDto = customerService.getCustomerByEmailId(1,"ardoing@gmail.com");
		assertEquals("Arun", regiterDto.getName());
		assertEquals("ardoing@gmail.com", regiterDto.getEmailId());
	}
	
	@Test
	void getCustomerByMobileNumberTest() {
		EndUser admin=new EndUser();
		admin.setFullName("Ashish");
		admin.setMobileNumber("7979874545");
		admin.setAdmin(true);
		Login login2 = new Login();
		admin.setLogin(login2);
		admin.getLogin().setEmail("ashish32@gmail.com");
		admin.getLogin().setPassword("@$Ashish56");
		admin.getLogin().setLogin(true);
		admin.setId(1);
		Cart cart3 = new Cart();
		cart3.setCartId(2);
		admin.setCart(cart3);
		Mockito.when(endUserRepository.findById(1)).thenReturn(Optional.of(admin));
		Mockito.when(endUserRepository.getById(1)).thenReturn(admin);
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Mockito.when(endUserRepository.getByMobileNumber("8787878889")).thenReturn(customer);
		RegisterOutputDto registerDto1 = customerService.getCustomerByMobileNumber(1,"8787878889");
		assertEquals("Arun", registerDto1.getName());
		assertEquals("ardoing@gmail.com", registerDto1.getEmailId());
	}
	
	@Test
	void getAllCustomersDtoTest() {
		EndUser admin=new EndUser();
		admin.setFullName("Ashish");
		admin.setMobileNumber("7979874545");
		admin.setAdmin(true);
		Login login2 = new Login();
		admin.setLogin(login2);
		admin.getLogin().setEmail("ashish32@gmail.com");
		admin.getLogin().setPassword("@$Ashish56");
		admin.getLogin().setLogin(true);
		admin.setId(1);
		Cart cart3 = new Cart();
		cart3.setCartId(2);
		admin.setCart(cart3);
		Mockito.when(endUserRepository.findById(1)).thenReturn(Optional.of(admin));
		Mockito.when(endUserRepository.getById(1)).thenReturn(admin);
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer1 = new EndUser();
		customer1.setFullName(register.getName());
		Login login = new Login();
		customer1.setLogin(login);
		Cart cart = new Cart();
		customer1.setCart(cart);
		customer1.getCart().setCartId(2);
		customer1.getLogin().setEmail(register.getEmailId());
		customer1.getLogin().setPassword(register.getPassword());
		customer1.setMobileNumber(register.getMobileNumber());
		customer1.setAdmin(false);
		customer1.setId(22);
		Register register1 = new Register("Arun", "ardone@gmail.com", "ardoin@1", "ardoin@1", "8787878886", false);
		EndUser customer2 = new EndUser();
		customer2.setFullName(register1.getName());
		Login login1 = new Login();
		customer2.setLogin(login1);
		customer2.getLogin().setEmail(register.getEmailId());
		customer2.getLogin().setPassword(register.getPassword());
		Cart cart1 = new Cart();
		customer2.setCart(cart1);
		customer2.getCart().setCartId(3);
		customer2.setMobileNumber(register1.getMobileNumber());
		customer2.setAdmin(false);
		customer2.setId(23);
		List<EndUser> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);
		Mockito.when(endUserRepository.findAll()).thenReturn(customers);
		List<RegisterOutputDto> resultCustomers = customerService.getAllCustomerDto(1);
		assertEquals(2, resultCustomers.size());

	}
	
	@Test
	void updateNameTest() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.setMobileNumber(register.getMobileNumber());
		customer.getLogin().setLogin(true);
		customer.setAdmin(false);
		customer.setId(22);
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		String resultOfUpdate = customerService.updateName(22, "Ajay");
		assertEquals("Full name is successfully updated", resultOfUpdate);
	}
	
	@Test
	void updateMobileNumberTest() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		String resultOfUpdate = customerService.updateMobileNumber(22, "8787868889");
		assertEquals("Mobile Number is successfully updated", resultOfUpdate);
	}
	
	@Test
	void updateCustomerAddressTest() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		Address address = new Address(103, "Estate", "Uppal", "Hyd", "Ts", 501010, "India");
		customer.setAddress(address);
		Mockito.when(endUserRepository.save(customer)).thenReturn(customer);
		RegisterOutputDto registerDto = customerService.updateCustomerAddress(22, address);
		assertEquals("Arun", registerDto.getName());
		assertEquals("ardoing@gmail.com", registerDto.getEmailId());
		assertEquals(2, registerDto.getCartId());
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
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setId(22);
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		customer.setMobileNumber(register.getMobileNumber());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		Integer cartId = customerService.getCustomerCartId(22);
		assertEquals(2, cartId);
	}
	
	@Test
	void getCustomerCartPrice() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setId(22);
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		customer.setMobileNumber(register.getMobileNumber());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		Double cartCost = customerService.getCustomerCartPrice(22);
		assertEquals(0, cartCost);
	}
	

	
	@Test
	void updatePassword() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setId(22);
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		customer.setMobileNumber(register.getMobileNumber());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		Mockito.when(endUserRepository.getByEmailId("ardoing@gmail.com")).thenReturn(customer);
		String message = customerService.updatePassword("ardoing@gmail.com", "ardoin@1", "ardoin@12", "ardoin@12");
		assertEquals("Password is updated successfully", message);
	}

	@Test
	void deleteCustomerByIdTest() {
		EndUser admin=new EndUser();
		admin.setFullName("Ashish");
		admin.setMobileNumber("7979874545");
		admin.setAdmin(true);
		Login login2 = new Login();
		admin.setLogin(login2);
		admin.getLogin().setEmail("ashish32@gmail.com");
		admin.getLogin().setPassword("@$Ashish56");
		admin.getLogin().setLogin(true);
		admin.setId(1);
		Cart cart3 = new Cart();
		cart3.setCartId(2);
		admin.setCart(cart3);
		Mockito.when(endUserRepository.findById(1)).thenReturn(Optional.of(admin));
		Mockito.when(endUserRepository.getById(1)).thenReturn(admin);
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.setCart(cart);
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		customer.getLogin().setLogin(true);
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Mockito.when(endUserRepository.findById(22)).thenReturn(Optional.of(customer));
		Mockito.when(endUserRepository.getById(22)).thenReturn(customer);
		Mockito.doNothing().when(endUserRepository).deleteById(22);
		RegisterOutputDto registerDto1 = customerService.deleteCustomerById(1,22);
		assertEquals("Arun", registerDto1.getName());
		assertEquals("ardoing@gmail.com", registerDto1.getEmailId());
	}

	@Test
	void deleteCustomerByEmailId() {
		Register register = new Register("Arun", "ardoing@gmail.com", "ardoin@1", "ardoin@1", "8787878889", false);
		EndUser customer = new EndUser();
		customer.setFullName(register.getName());
		Login login = new Login();
		customer.setLogin(login);
		customer.getLogin().setEmail(register.getEmailId());
		customer.getLogin().setPassword(register.getPassword());
		Cart cart = new Cart();
		cart.setCartId(2);
		customer.getLogin().setLogin(true);
		customer.setCart(cart);
		customer.setMobileNumber(register.getMobileNumber());
		customer.setAdmin(false);
		customer.setId(22);
		Mockito.when(endUserRepository.getByEmailId("ardoing@gmail.com")).thenReturn(customer);
		Mockito.doNothing().when(endUserRepository).delete(customer);
		RegisterOutputDto registerDto1 = customerService.deleteCustomerByEmailId("ardoing@gmail.com");
		assertEquals("Arun", registerDto1.getName());
		assertEquals("ardoing@gmail.com", registerDto1.getEmailId());
	}



}
