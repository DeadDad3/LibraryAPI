package ru.kudukhov.libraryapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<Transaction> createTransaction(@RequestParam Long bookId,
      @RequestParam String readerPhone,
      @RequestParam TransactionType type) {
    Transaction transaction = transactionService.createTransaction(bookId, readerPhone, type);
    return ResponseEntity.ok(transaction);
  }
}