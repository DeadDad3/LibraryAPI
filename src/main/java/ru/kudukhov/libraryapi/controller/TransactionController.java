package ru.kudukhov.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.service.TransactionService;

@Tag(name = "Transactions", description = "Endpoints for managing transactions with books")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Operation(summary = "Create a transaction (borrow/return book)")
  @PostMapping
  public ResponseEntity<Transaction> createTransaction(
      @Parameter(description = "Book ID") @RequestParam Long bookId,
      @Parameter(description = "Client ID") @RequestParam String clientId,
      @Parameter(description = "Type of transaction") @RequestParam TransactionType transactionType) {
    Transaction transaction = transactionService.createTransaction(bookId, clientId, transactionType);
    return ResponseEntity.ok(transaction);
  }
}