package com.r4.transaction.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.r4.transaction.model.Account;

public class AccountDto {

	@JsonProperty("account_id")
	private Long id;
	@JsonProperty("document_number")
	private String documentNumber;
	
	
	public AccountDto() { }

	public AccountDto(Account account) {
		this.id = account.getId();
		this.documentNumber = account.getDocumentNumber();
	}
	
	public static List<AccountDto> converter(List<Account> accountList) {
		return accountList.stream().map(AccountDto::new).collect(Collectors.toList());
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
	
}
