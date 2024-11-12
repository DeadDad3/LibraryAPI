package ru.kudukhov.libraryapi.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Transaction;

/**
 * Repository interface for performing CRUD operations on the Transaction entity.
 * <p>
 * This repository includes a custom method to find transactions by date range.
 * </p>
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  /**
   * Finds all transactions that occurred between the given start and end dates.
   *
   * @param startDate The start date of the range.
   * @param endDate The end date of the range.
   * @return A list of transactions that occurred within the specified date range.
   */
  List<Transaction> findByTransactionDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}