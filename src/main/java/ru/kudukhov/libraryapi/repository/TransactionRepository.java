package ru.kudukhov.libraryapi.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByTransactionDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}