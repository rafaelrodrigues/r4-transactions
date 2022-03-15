package com.r4.transaction.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.r4.transaction.model.Account;

class AccountDtoTest {

	private final Long ID = 1L;
	private final String DOCUMENT = "12345678901";
	
	@Test
	void shouldConvertAccountToAccountDto() {
		Account account = new Account();
		account.setId(ID);
		account.setDocumentNumber(DOCUMENT);
		
		List<Account> accountList = new ArrayList<Account>();
		accountList.add(account);
		
		var dto = AccountDto.converter(accountList).get(0);
		
		assertEquals(ID, dto.getId());
		assertEquals(DOCUMENT, dto.getDocumentNumber());
	}
	
}
