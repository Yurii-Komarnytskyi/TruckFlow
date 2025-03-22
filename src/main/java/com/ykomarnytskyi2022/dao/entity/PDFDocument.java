package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class PDFDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY)
	protected byte[] document;
	
	protected String name;
	
	@Column(nullable = true)
	protected String description;

	// To enforce the @NotNull constraint, you need to trigger validation at the appropriate point in your application
	@NotNull 
	protected String issuer;
	
	// To enforce the @NotNull constraint, you need to trigger validation at the appropriate point in your application
	@NotNull
	protected LocalDate dateOfUploading;

	public PDFDocument() {
		// default no-argument constructor for Spring JPA	
	}
}
