package ru.kudukhov.libraryapi.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import ru.kudukhov.libraryapi.entity.*;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.repository.*;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;

  public TransactionService(TransactionRepository transactionRepository,
      BookRepository bookRepository,
      ReaderRepository readerRepository) {
    this.transactionRepository = transactionRepository;
    this.bookRepository = bookRepository;
    this.readerRepository = readerRepository;
  }

  public Transaction createTransaction(Long bookId, String clientId, TransactionType transactionType) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    Reader reader = readerRepository.findById(clientId)
        .orElseThrow(() -> new IllegalArgumentException("Client not found"));

    Transaction transaction = new Transaction();
    transaction.setBook(book);
    transaction.setClient(reader);
    transaction.setTransactionType(transactionType);
    transaction.setTransactionDateTime(LocalDateTime.now());

    return transactionRepository.save(transaction);
  }
}