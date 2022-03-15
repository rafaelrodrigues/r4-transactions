package com.r4.transaction.controller.form;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.r4.transaction.model.Account;
import com.r4.transaction.model.OperationType;
import com.r4.transaction.repository.AccountRepository;

class TransactionFormTest {
	
	private static final String DOCUMENT_NUMBER = "12345678901";
	private static final Long ACCOUNT_ID = 1L;
	private static final int OPERATION_TYPE_ID = 1;
	private static final BigDecimal AMOUNT = new BigDecimal("15.0");
	private static final Account ACCOUNT = new Account(ACCOUNT_ID, DOCUMENT_NUMBER);
	
	private static final int OPERATION_TYPE_ID_PAGAMENTO = OperationType.PAGAMENTO.toValue();
	private static final int OPERATION_TYPE_ID_COMPRA_VISTA = OperationType.COMPRA_A_VISTA.toValue();
	
	private TransactionForm form;
	
	@Test
	void shouldConvertTransactionFormToTransaction() {
		AccountRepository mockRepository = Mockito.mock(AccountRepository.class);
		Mockito.when(mockRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT));
		
		form = new TransactionForm();
		form.setAccountId(ACCOUNT_ID);
		form.setOperationTypeId(OPERATION_TYPE_ID);
		form.setAmount(AMOUNT);
		
		var transaction = form.converter(mockRepository);
		assertEquals(ACCOUNT_ID, transaction.getAccount().getId());
		assertEquals(DOCUMENT_NUMBER, transaction.getAccount().getDocumentNumber());
		assertEquals(OPERATION_TYPE_ID, transaction.getOperationTypeId());
		assertEquals(AMOUNT, transaction.getAmount());
	}
	
	@Test
	void shouldConvertTransactionFormToTransaction_AndValidateAmountByOperationType_ThenAmountPositive() {
		AccountRepository mockRepository = Mockito.mock(AccountRepository.class);
		Mockito.when(mockRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT));
		
		form = new TransactionForm();
		form.setAccountId(ACCOUNT_ID);
		form.setOperationTypeId(OPERATION_TYPE_ID_PAGAMENTO);
		form.setAmount(AMOUNT);
	
		var transaction = form.converter(mockRepository);
		
		OperationType.forValue(transaction.getOperationTypeId()).calculateInput(transaction);
		
		assertEquals(OPERATION_TYPE_ID_PAGAMENTO, transaction.getOperationTypeId());
		assertTrue( transaction.getAmount().compareTo(BigDecimal.ZERO) >= 0 );
		assertTrue( transaction.getAmount().equals(AMOUNT) );
	}
	
	@Test
	void shouldConvertTransactionFormToTransaction_AndValidateAmountByOperationType_ThenAmountNegative() {
		AccountRepository mockRepository = Mockito.mock(AccountRepository.class);
		Mockito.when(mockRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(ACCOUNT));
		
		form = new TransactionForm();
		form.setAccountId(ACCOUNT_ID);
		form.setOperationTypeId(OPERATION_TYPE_ID_COMPRA_VISTA);
		form.setAmount(AMOUNT);
	
		var transaction = form.converter(mockRepository);
		
		OperationType.forValue(transaction.getOperationTypeId()).calculateInput(transaction);
		
		assertEquals(OPERATION_TYPE_ID_COMPRA_VISTA, transaction.getOperationTypeId());
		assertTrue( transaction.getAmount().compareTo(BigDecimal.ZERO) < 0 );
		assertTrue( transaction.getAmount().equals(AMOUNT.negate()) );
	}

}
