package com.shopx.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "new_customer")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer extends BaseEntity{

	@Column(length = 25)
	private String firstName;
	@Column(length = 25)
	private String lastName;
	@Column(length = 50, unique = true) 
	private String email;
	@Column(length = 50, nullable = false) // NOT NULL constraint
	private String password;	
	@Column(length = 200)
	private String address;
	@Column(length = 25)
	private String mobile;
	@Enumerated(EnumType.STRING)
	@Column(length = 25)
	private RoleType role=RoleType.ROLE_CUSTOMER;
	
	
	
	
	
	
	
}
