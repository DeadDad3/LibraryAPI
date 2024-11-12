package ru.kudukhov.libraryapi.enums;

/**
 * Enum representing the type of a transaction.
 * <p>
 * This enum has two types of transactions:
 * - BORROW: The transaction where a book is borrowed.
 * - RETURN: The transaction where a book is returned.
 * </p>
 */
public enum TransactionType {
  BORROW, RETURN
}