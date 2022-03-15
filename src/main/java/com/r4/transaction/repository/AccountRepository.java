package com.r4.transaction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.r4.transaction.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByDocumentNumber(String documentNumber);
	
	Optional<Account> findById(Long id);
}
