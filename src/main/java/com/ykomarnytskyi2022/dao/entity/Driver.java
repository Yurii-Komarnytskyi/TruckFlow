package com.ykomarnytskyi2022.dao.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Driver {
	
	private String cellPhone;
	
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<ProofOfDelivery> proofOfDeliveries;

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
}
