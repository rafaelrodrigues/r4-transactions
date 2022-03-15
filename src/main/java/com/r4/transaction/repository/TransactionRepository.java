package com.r4.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r4.transaction.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findAll();
}
