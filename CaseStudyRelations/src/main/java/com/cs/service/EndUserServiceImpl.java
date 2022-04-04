package com.cs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.cs.bean.Address;
import com.cs.bean.Cart;
import com.cs.bean.EndUser;
import com.cs.bean.Login;
import com.cs.dto.Register;
import com.cs.dto.RegisterOutputDto;
import com.cs.exception.CustomerNotFoundException;
import com.cs.exception.DuplicateValueException;
import com.cs.exception.NotAdminException;
import com.cs.exception.NotLoggedInException;
import com.cs.exception.PasswordDoNotMatchException;
import com.cs.repository.IAddressRepository;
import com.cs.repository.ICartRepository;
import com.cs.repository.IEndUserRepository;
import com.cs.repository.ILoginRepository;

@Service
@Validated
public class EndUserServiceImpl implements IEndUserService {

	@Autowired
	IEndUserRepository endUserRepository;
	@Autowired
	ILoginRepository loginRepository;
	@Autowired
	ICartRepository cartRepository;
	@Autowired
	IAddressRepository addressRepository;

	@Override
	public RegisterOutputDto addCustomer(Register register) {
		EndUser newCustomer = new EndUser();
		newCustomer.setFullName(register.getName());
		newCustomer.setMobileNumber(register.getMobileNumber());
		EndUser customer = endUserRepository.getByMobileNumber(register.getMobileNumber());
		if (customer != null) {
			throw new DuplicateValueException(
					"Customer with the given phone number already exists, try registering using other mobile number");
		}
		customer = null;
		if (!register.getPassword().equals(register.getConfirmPassword())) {
			throw new PasswordDoNotMatchException("Confirm password and Password donot match exception");
		}
		newCustomer.setAdmin(register.isAdmin());
		customer = endUserRepository.getByEmailId(register.getEmailId());
		if (customer != null) {
			throw new DuplicateValueException(
					"Customer with the given email Id already exists, try registering using other email id or login though this mail Id");
		}
		Login login = new Login();
		login.setEmail(register.getEmailId());
		login.setPassword(register.getPassword());
		newCustomer.setLogin(login);
		Cart cart = new Cart();
		Address address = new Address();
		newCustomer.setAddress(address);
		newCustomer.setCart(cart);
		newCustomer.setAddress(address);
		endUserRepository.save(newCustomer);
		RegisterOutputDto registerDto = new RegisterOutputDto();
		registerDto.setEmailId(newCustomer.getLogin().getEmail());
		registerDto.setName(newCustomer.getFullName());
		registerDto.setMobileNumber(newCustomer.getMobileNumber());
		registerDto.setCartId(newCustomer.getCart().getCartId());
		registerDto.setAddress(newCustomer.getAddress());
		return registerDto;
	}

	@Override
	public List<RegisterOutputDto> getAllCustomerDto(Integer id) {

		Optional<EndUser> opt = endUserRepository.findById(id);

		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + id);
		}
		EndUser customer = endUserRepository.getById(id);
		if (customer.isAdmin() == false) {
			throw new NotAdminException("You are not authorized to view such methods");
		}
		if (customer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		List<EndUser> customers = endUserRepository.findAll();
		List<RegisterOutputDto> registerOuput = new ArrayList<>();

		for (EndUser custome : customers) {
			RegisterOutputDto newRegisterOuput = new RegisterOutputDto();
			newRegisterOuput.setName(custome.getFullName());
			newRegisterOuput.setMobileNumber(custome.getMobileNumber());
			newRegisterOuput.setEmailId(custome.getLogin().getEmail());
			newRegisterOuput.setCartId(custome.getCart().getCartId());
			newRegisterOuput.setAddress(custome.getAddress());
			registerOuput.add(newRegisterOuput);
		}
		return registerOuput;
	}

	@Override
	public EndUser getCustomerById(Integer id) {
		Optional<EndUser> opt = endUserRepository.findById(id);
		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + id);
		}
		EndUser updatedCustomer = endUserRepository.getById(id);
		if (updatedCustomer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		return updatedCustomer;

	}

	@Override
	public List<RegisterOutputDto> getCustomersByName(Integer adminId, String fullName) {
		EndUser admin = endUserRepository.getById(adminId);
		if (admin.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		if (admin.isAdmin() == false) {
			throw new NotAdminException("You are not authorized to view such methods");
		}
		List<EndUser> customers = endUserRepository.getAllByFullName(fullName);
		List<RegisterOutputDto> registerOuput = new ArrayList<>();
		for (EndUser customer : customers) {
			RegisterOutputDto newRegisterOuput = new RegisterOutputDto();
			newRegisterOuput.setEmailId(customer.getLogin().getEmail());
			newRegisterOuput.setName(customer.getFullName());
			newRegisterOuput.setMobileNumber(customer.getMobileNumber());
			newRegisterOuput.setCartId(customer.getCart().getCartId());
			newRegisterOuput.setAddress(customer.getAddress());
			registerOuput.add(newRegisterOuput);
		}
		return registerOuput;
	}

	@Override
	public RegisterOutputDto getCustomerByEmailId(Integer adminId, String emailId) {
		EndUser admin = endUserRepository.getById(adminId);
		if (admin.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		if (admin.isAdmin() == false) {
			throw new NotAdminException("You are not authorized to view such methods");
		}
		EndUser customer = endUserRepository.getByEmailId(emailId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with given reference Email Id : " + emailId);
		}
		RegisterOutputDto registerDto = new RegisterOutputDto();
		registerDto.setEmailId(customer.getLogin().getEmail());
		registerDto.setName(customer.getFullName());
		registerDto.setMobileNumber(customer.getMobileNumber());
		registerDto.setCartId(customer.getCart().getCartId());
		registerDto.setAddress(customer.getAddress());
		return registerDto;
	}

	@Override
	public RegisterOutputDto getCustomerByMobileNumber(Integer adminId, String mobileNumber) {
		EndUser admin = endUserRepository.getById(adminId);
		if (admin.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		if (admin.isAdmin() == false) {
			throw new NotAdminException("You are not authorized to view such methods");
		}
		EndUser customer = endUserRepository.getByMobileNumber(mobileNumber);
		if (customer == null) {
			throw new CustomerNotFoundException(
					"Customer not found with given reference mobile number : " + mobileNumber);
		}
		RegisterOutputDto registerDto = new RegisterOutputDto();
		registerDto.setEmailId(customer.getLogin().getEmail());
		registerDto.setName(customer.getFullName());
		registerDto.setMobileNumber(mobileNumber);
		registerDto.setCartId(customer.getCart().getCartId());
		registerDto.setAddress(customer.getAddress());
		return registerDto;
	}

	@Override
	public String updateName(Integer id,
			@NotEmpty(message = "Field must not be Empty") @Pattern(regexp = "[a-zA-Z[\\s]]{3,50}", message = "Name should not have any digit or special character") String fullName) {
		Optional<EndUser> opt = endUserRepository.findById(id);

		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + id);
		}
		EndUser updatedCustomer = endUserRepository.getById(id);
		if (updatedCustomer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		updatedCustomer.setFullName(fullName);
		endUserRepository.save(updatedCustomer);
		return "Full name is successfully updated";
	}

	@Override
	public String updateMobileNumber(Integer id,
			@NotEmpty(message = "Field must not be Empty") @Pattern(regexp = "^(9|7|8)([0-9]){9}$", message = "Please enter valid mobile number") String mobileNumber) {

		Optional<EndUser> opt = endUserRepository.findById(id);

		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + id);
		}
		EndUser updatedCustomer = endUserRepository.getById(id);
		if (updatedCustomer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		updatedCustomer.setMobileNumber(mobileNumber);
		try {
			endUserRepository.save(updatedCustomer);
			return "Mobile Number is successfully updated";
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityViolationException(
					"Customer with the given phone number already exists, try registering using other mobile number");
		}

	}

	@Override
	public RegisterOutputDto deleteCustomerById(Integer adminId, Integer id) {
		EndUser admin = endUserRepository.getById(adminId);
		if (admin.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		if (admin.isAdmin() == false) {
			throw new NotAdminException("You are not authorized to view such methods");
		}

		Optional<EndUser> opt = endUserRepository.findById(id);

		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + id);
		}
		EndUser customer = endUserRepository.getById(id);
		RegisterOutputDto register = new RegisterOutputDto();
		register.setEmailId(customer.getLogin().getEmail());
		register.setName(customer.getFullName());
		register.setMobileNumber(customer.getMobileNumber());
		register.setCartId(customer.getCart().getCartId());
		register.setAddress(customer.getAddress());
		endUserRepository.deleteById(customer.getId());
		return register;
	}

	@Override
	public RegisterOutputDto deleteCustomerByEmailId(String emailId) {
		EndUser customer = endUserRepository.getByEmailId(emailId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with given reference Email Id : " + emailId);
		}
		if (customer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		RegisterOutputDto register = new RegisterOutputDto();
		register.setEmailId(customer.getLogin().getEmail());
		register.setName(customer.getFullName());
		register.setMobileNumber(emailId);
		register.setCartId(customer.getCart().getCartId());
		register.setAddress(customer.getAddress());
		endUserRepository.delete(customer);
		return register;
	}

	@Override
	public RegisterOutputDto updateCustomerAddress(Integer customerId, Address address) {
		Optional<EndUser> opt = endUserRepository.findById(customerId);
		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + customerId);
		}
		EndUser customer = endUserRepository.getById(customerId);
		if (customer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		RegisterOutputDto register = new RegisterOutputDto();
		register.setEmailId(customer.getLogin().getEmail());
		register.setName(customer.getFullName());
		register.setMobileNumber(customer.getMobileNumber());
		register.setCartId(customer.getCart().getCartId());
		register.setAddress(address);
		customer.getAddress().setBuildingName(address.getBuildingName());
		customer.getAddress().setCity(address.getCity());
		customer.getAddress().setColony(address.getColony());
		customer.getAddress().setCountry(address.getCountry());
		customer.getAddress().setFlatNum(address.getFlatNum());
		customer.getAddress().setPincode(address.getPincode());
		customer.getAddress().setState(address.getState());
		endUserRepository.save(customer);
		return register;
	}

	@Override
	public int getCustomerCartId(Integer customerId) {
		Optional<EndUser> opt = endUserRepository.findById(customerId);
		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + customerId);
		}
		EndUser customer = endUserRepository.getById(customerId);
		if (customer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}
		return customer.getCart().getCartId();
	}

	@Override
	public String updatePassword(String email, String oldPassword,
			@NotEmpty(message = "Field must not be Empty") @Pattern(regexp = "^(([a-z])|([A-Z])|([!@#$&*])|([0-9])){8,12}$", message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit and should be atleast 8 letters") String newPassword,
			String confirmNewPassword) {
		EndUser customer = endUserRepository.getByEmailId(email);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with given reference Email Id : " + email);
		}
		if (customer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}

		if (!oldPassword.equals(customer.getLogin().getPassword())) {
			throw new PasswordDoNotMatchException("Password donot match");
		}
		if (!newPassword.equals(confirmNewPassword)) {
			throw new PasswordDoNotMatchException("Confirm password and Password donot match exception");
		}
		customer.getLogin().setPassword(newPassword);
		endUserRepository.save(customer);
		return "Password is updated successfully";
	}

	@Override
	public double getCustomerCartPrice(Integer id) {
		Optional<EndUser> opt = endUserRepository.findById(id);

		if (!opt.isPresent()) {
			throw new CustomerNotFoundException("Customer not found with given reference id : " + id);
		}
		EndUser customer = endUserRepository.getById(id);
		if (customer.getLogin().isLogin() == false) {
			throw new NotLoggedInException("Please login first to update");
		}

		return customer.getCart().getCartCost();
	}

}
