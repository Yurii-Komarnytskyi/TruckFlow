package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<String> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", lastName=" + lastName + ", cellPhone=" + cellPhone
				+ ", birthDate=" + birthDate + ", sex=" + sex + ", age=" + age + ", email=" + email
				+ ", emergencyContacts=" + emergencyContacts + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, birthDate, cellPhone, email, id, lastName, name, sex);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		return Objects.equals(age, other.age) && Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(cellPhone, other.cellPhone) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(name, other.name) && sex == other.sex;
	}
}
