package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDate;
import java.util.List;

import com.ykomarnytskyi2022.enums.Sex;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotNull
	protected String name;
	
	@NotNull
	@Column(name = "last_name")
	protected String lastName;
	
	@NotNull
	@Column(name = "cell_phone")
	protected String cellPhone;
	
	@NotNull
	@Column(name = "birth_date")
    protected LocalDate birthDate;
	
	@Enumerated(EnumType.STRING)
	protected Sex sex;
	
	@Min(18)
	@Max(100)
	protected Integer age;
	
	@NotNull
	@Email
	protected String email;
	
	@NotNull
	protected List<String> emergencyContacts;	
}
