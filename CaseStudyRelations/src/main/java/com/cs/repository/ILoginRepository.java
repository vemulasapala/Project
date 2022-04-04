package com.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs.bean.Login;

/** 
 * <h2> Login Repository </h2> 
 * This interface is used to communicate database and to do all
 * Database operations. Using this class we are communicating with
 * database.
 *    
 * @author SatyamMishra(EmpId - 46191989) 
 * @version 1.0 
 * @since 2022-03-27 
 */ 

@Repository
public interface ILoginRepository extends JpaRepository<Login, String>{

}

