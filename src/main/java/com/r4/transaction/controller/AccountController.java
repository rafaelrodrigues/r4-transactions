package com.r4.transaction.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.r4.transaction.controller.dto.AccountDto;
import com.r4.transaction.controller.form.AccountForm;
import com.r4.transaction.model.Account;
import com.r4.transaction.repository.AccountRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AccountDto> create(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder) {
		
		accountRepository.findByDocumentNumber(form.getDocumentNumber()).ifPresent(account -> {
			throw new IllegalArgumentException("Account with Document Number "+form.getDocumentNumber()+" already exists");
		});
		
		Account account = form.converter();
		accountRepository.save(account);
		
		URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getId()).toUri();
		return ResponseEntity.created(uri).body(new AccountDto(account));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> show(@PathVariable Long id) {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			return ResponseEntity.ok(new AccountDto(account.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<AccountDto> list() {
		List<Account> accountList = accountRepository.findAll();
		return AccountDto.converter(accountList);
	}
}
