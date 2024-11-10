package ru.kudukhov.libraryapi.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.kudukhov.libraryapi.entity.Reader;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.repository.ReaderRepository;
import ru.kudukhov.libraryapi.repository.TransactionRepository;

@Service
public class ReaderService {

  private final ReaderRepository readerRepository;
  private final TransactionRepository transactionRepository;

  public ReaderService(ReaderRepository readerRepository, TransactionRepository transactionRepository) {
    this.readerRepository = readerRepository;
    this.transactionRepository = transactionRepository;
  }

  // Метод для получения самого читающего клиента
  public Reader getTopReader() {
    List<Transaction> transactions = transactionRepository.findAll();

    return transactions.stream()
        .collect(Collectors.groupingBy(Transaction::getClient, Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);
  }

  // Метод для получения списка читателей по количеству несданных книг
  public List<Reader> getReadersByUnreturnedBooksCount() {
    List<Transaction> transactions = transactionRepository.findAll();

    Map<Reader, Long> unreturnedBooksCount = transactions.stream()
        .filter(transaction -> transaction.getTransactionType() == TransactionType.BORROW)
        .collect(Collectors.groupingBy(Transaction::getClient, Collectors.counting()));

    return unreturnedBooksCount.entrySet().stream()
        .sorted(Map.Entry.<Reader, Long>comparingByValue().reversed())
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }
}