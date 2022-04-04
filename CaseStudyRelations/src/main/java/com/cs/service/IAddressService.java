package com.cs.service;

import java.util.List;

import com.cs.bean.Address;

/**
 * This Interface is used to define the services provided to AddressController
 * so that we can add, delete, update and retrieve the address into or from
 * database
 * 
 * @author Rohith(Employee id: 46191986)
 *
 * 
 */
public interface IAddressService {

	Address addAddress(int userId, Address address);

	Address deleteAddressById(int id);

	Address updateAddressById(int id, Address address);

	Address getAddresById(int id);

	List<Address> getAllAddresses();
}