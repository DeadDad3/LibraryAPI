package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}