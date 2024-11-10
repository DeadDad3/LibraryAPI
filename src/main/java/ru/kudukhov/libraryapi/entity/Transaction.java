package ru.kudukhov.libraryapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import ru.kudukhov.libraryapi.enums.TransactionType;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  private LocalDateTime transactionDateTime;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Reader client;

  @ManyToOne
  @JoinColumn(name = "book_id")
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
