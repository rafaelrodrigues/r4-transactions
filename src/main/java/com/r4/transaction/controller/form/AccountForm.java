package com.r4.transaction.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.r4.transaction.model.Account;

public class AccountForm {
	
	@NotEmpty @NotBlank
	@JsonProperty("document_number")
	private String documentNumber;

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	public Account converter() {
		return new Account(documentNumber);
	}
	
}
