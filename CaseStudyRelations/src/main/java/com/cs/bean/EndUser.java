package com.cs.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * <h3> EndUser Entity </h3> 
 * This entity consists of 10 fields. 
 * It consists of the information of the Online-Plant Nursery.
 * Including their Id,fullName,mobileNumber,isAdmin,createdAt,
 * modifiedDate,Login,address,cart and order details.
 * 
 *  @author : Sapala Srusti Vemula
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class EndUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String mobileNumber;
	private String fullName;
	private boolean isAdmin;

	@CreatedDate
	@Setter(AccessLevel.PROTECTED)
	Date createdAt;

	@LastModifiedDate
	@Setter(AccessLevel.PROTECTED)
	Date modifiedAt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "login_Id")
	private Login login;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_Id")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_Id")
	private Cart cart;

}
