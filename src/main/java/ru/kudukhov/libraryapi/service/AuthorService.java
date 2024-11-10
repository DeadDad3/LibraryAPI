package ru.kudukhov.libraryapi.service;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import ru.kudukhov.libraryapi.entity.Author;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.repository.AuthorRepository;
import ru.kudukhov.libraryapi.repository.TransactionRepository;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;
  private final TransactionRepository transactionRepository;

  public AuthorService(AuthorRepository authorRepository, TransactionRepository transactionRepository) {
    this.authorRepository = authorRepository;
    this.transactionRepository = transactionRepository;
  }

  public Author getMostPopularAuthor(LocalDate startDate, LocalDate endDate) {
    List<Transaction> transactions = transactionRepository.findByTransactionDateTimeBetween(startDate.atStartOfDay(), endDate.atTime(23, 59));

    return transactions.stream()
        .flatMap(transaction -> transaction.getBook().getAuthors().stream())
        .collect(Collectors.groupingBy(author -> author, Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);
  }
}