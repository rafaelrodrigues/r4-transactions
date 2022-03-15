package com.r4.transaction.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;
	@Column(name = "document_number")
	private String documentNumber;
	
	public Account() { }
	
	public Account(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	public Account(Long id, String documentNumber) {
		this.id = id;
		this.documentNumber = documentNumber;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(documentNumber, id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(documentNumber, other.documentNumber) && Objects.equals(id, other.id);
	}
}
