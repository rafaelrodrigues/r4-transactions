package com.r4.transaction.controller.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountFormTest {

	private static final String DOCUMENT = "12345678901";
	
	private AccountForm form;

	@Test
	void shouldConvertAccountFormToAccount() {
		form = new AccountForm();
		form.setDocumentNumber(DOCUMENT);
		
		var account = form.converter();
		assertEquals(DOCUMENT, account.getDocumentNumber());
	}

}
