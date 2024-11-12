package ru.kudukhov.libraryapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import ru.kudukhov.libraryapi.enums.TransactionType;

@Schema(description = "Transaction entity representing a transaction with a book")
@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the transaction", example = "1")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Schema(description = "Type of transaction (BORROW or RETURN)", example = "BORROW")
  private TransactionType transactionType;

  @Schema(description = "Date and time of the transaction", example = "2023-11-11T12:30:00")
  private LocalDateTime transactionDateTime;

  @ManyToOne
  @JoinColumn(name = "client_id")
  @Schema(description = "Reader who is involved in the transaction")
  private Reader client;

  @ManyToOne
  @JoinColumn(name = "book_id")
  @Schema(description = "Book involved in the transaction")
  private Book book;

  public Transaction(Long id, TransactionType transactionType, LocalDateTime transactionDateTime,
      Reader client, Book book) {
    this.id = id;
    this.transactionType = transactionType;
    this.transactionDateTime = transactionDateTime;
    this.client = client;
    this.book = book;
  }

  public Transaction() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public LocalDateTime getTransactionDateTime() {
    return transactionDateTime;
  }

  public void setTransactionDateTime(LocalDateTime transactionDateTime) {
    this.transactionDateTime = transactionDateTime;
  }

  public Reader getClient() {
    return client;
  }

  public void setClient(Reader client) {
    this.client = client;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
