package com.r4.transaction.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.r4.transaction.model.Transaction;

public class TransactionDto {

	@JsonProperty("transaction_id")
	private Long id;
	@JsonProperty("account_id")
	private Long accountId;
	@JsonProperty("operation_type_id")
	private int operationTypeId;
	private BigDecimal amount;
	@JsonProperty("event_date")
	private LocalDateTime eventDate;
	
	
	public TransactionDto() { }
	
	public TransactionDto(Transaction transaction) {
		this.id = transaction.getId();
		this.accountId = transaction.getAccount().getId();
		this.operationTypeId = transaction.getOperationTypeId();
		this.amount = transaction.getAmount();
		this.eventDate = transaction.getEventDate();
	}
	
	public static List<TransactionDto> converter(List<Transaction> transactionList) {
		return transactionList.stream().map(TransactionDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public int getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(int operationTypeId) {
		this.operationTypeId = operationTypeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

}
