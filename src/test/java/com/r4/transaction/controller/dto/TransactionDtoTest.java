package com.r4.transaction.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.r4.transaction.model.Account;
import com.r4.transaction.model.Transaction;

class TransactionDtoTest {

	private static final String DOCUMENT_NUMBER = "12345678901";
	private static final Long ACCOUNT_ID = 1L;
	private static final Long TRANSACTION_ID = 1L;
	private static final int OPERATION_TYPE_ID = 1;
	private static final BigDecimal AMOUNT = new BigDecimal("15.0");
	private static final Account ACCOUNT = new Account(ACCOUNT_ID, DOCUMENT_NUMBER);
	
	@Test
	void shouldConvertTransactionToTranasactionDto() {
		Transaction transaction = new Transaction(ACCOUNT,OPERATION_TYPE_ID,AMOUNT);
		transaction.setId(TRANSACTION_ID);
		transaction.setEventDate(LocalDateTime.now());
		
		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(transaction);
		
		var dto = TransactionDto.converter(transactionList).get(0);
		
		assertEquals(TRANSACTION_ID, dto.getId());
		assertEquals(ACCOUNT_ID, dto.getAccountId());
		assertEquals(OPERATION_TYPE_ID, dto.getOperationTypeId());
		assertEquals(AMOUNT, dto.getAmount());
	}

}
