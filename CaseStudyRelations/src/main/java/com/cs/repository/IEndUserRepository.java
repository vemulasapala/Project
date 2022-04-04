package com.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cs.bean.EndUser;

@Repository
public interface IEndUserRepository extends JpaRepository<EndUser, Integer> {
	
	
	@Query(value ="select * from end_user eu where eu.full_name = :fullName",nativeQuery=true)
	List<EndUser> getAllByFullName(@Param("fullName")String fullName);
	
	@Query(value ="select * from end_user eu where eu.mobile_number = :mobileNumber",nativeQuery=true)
	EndUser getByMobileNumber(@Param("mobileNumber") String mobileNumber);	
	
	@Query(value ="select * from end_user eu where eu.login_id = :emailId",nativeQuery=true)
	EndUser getByEmailId(@Param("emailId") String emailId);	
	
}
