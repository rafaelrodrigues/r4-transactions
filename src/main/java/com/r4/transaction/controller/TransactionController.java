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

import com.r4.transaction.controller.dto.TransactionDto;
import com.r4.transaction.controller.form.TransactionForm;
import com.r4.transaction.model.OperationType;
import com.r4.transaction.model.Transaction;
import com.r4.transaction.repository.AccountRepository;
import com.r4.transaction.repository.TransactionRepository;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<TransactionDto> create(@RequestBody @Valid TransactionForm form, UriComponentsBuilder uriBuilder) {
		
		validateForm(form);
		
		Transaction transaction = form.converter(accountRepository);
		OperationType.forValue(transaction.getOperationTypeId()).calculateInput(transaction);
		transactionRepository.save(transaction);
		
		URI uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri();
		return ResponseEntity.created(uri).body(new TransactionDto(transaction));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> show(@PathVariable Long id) {
		Optional<Transaction> transaction = transactionRepository.findById(id);
		if (transaction.isPresent()) {
			return ResponseEntity.ok(new TransactionDto(transaction.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<TransactionDto> list() {
		List<Transaction> transactionList = transactionRepository.findAll();
		return TransactionDto.converter(transactionList);
	}

	private void validateForm(TransactionForm form) {
		accountRepository.findById(form.getAccountId()).orElseThrow(() -> {
			throw new IllegalArgumentException("Account not found with ID "+form.getAccountId()+".");
		});
		
		if (!OperationType.exists(form.getOperationTypeId())){
			throw new IllegalArgumentException("Operation Type not found with ID "+form.getOperationTypeId()+".");
		}
	}
}

