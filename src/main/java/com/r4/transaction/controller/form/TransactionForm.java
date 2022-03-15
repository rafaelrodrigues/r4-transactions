package com.r4.transaction.controller.form;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.r4.transaction.model.Account;
import com.r4.transaction.model.OperationType;
import com.r4.transaction.model.Transaction;
import com.r4.transaction.repository.AccountRepository;

public class TransactionForm {
	
	@NotNull
	@JsonProperty("account_id")
	private Long accountId;
	@NotNull
	@JsonProperty("operation_type_id")
	private Integer operationTypeId;
	@NotNull @Min(value = 0)
	private BigDecimal amount;


	public Transaction converter(AccountRepository accountRepository) {
		Optional<Account> account = accountRepository.findById(accountId);
		return new Transaction(account.get(), operationTypeId, amount);
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getOperationTypeId() {
		return operationTypeId;
	}

	public void setOperationTypeId(Integer operationTypeId) {
		this.operationTypeId = operationTypeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
