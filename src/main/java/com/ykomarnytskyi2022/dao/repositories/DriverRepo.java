package com.ykomarnytskyi2022.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ykomarnytskyi2022.dao.entity.Driver;

public interface DriverRepo extends JpaRepository<Driver, Long> {
	Optional<Driver> findDriverByCellPhone(String cellPhone);
}
