package com.r4.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@Column(name = "operation_type_id")
	private int operationTypeId;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "event_date")
	private LocalDateTime eventDate = LocalDateTime.now();
	
	public Transaction() {	}

	public Transaction(Account account, int operationTypeId, BigDecimal amount) {
		this.account = account;
		this.operationTypeId = operationTypeId;
		this.amount = amount;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	@Override
	public int hashCode() {
		return Objects.hash(account, eventDate, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(account, other.account) && Objects.equals(eventDate, other.eventDate)
				&& Objects.equals(id, other.id);
	}
	
}
