package ru.kudukhov.libraryapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kudukhov.libraryapi.entity.Book;
import ru.kudukhov.libraryapi.entity.Reader;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.repository.BookRepository;
import ru.kudukhov.libraryapi.repository.ReaderRepository;
import ru.kudukhov.libraryapi.repository.TransactionRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

  @Mock
  private TransactionRepository transactionRepository;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private ReaderRepository readerRepository;

  @InjectMocks
  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateTransaction() {
    // Создаем тестовые объекты
    Book book = new Book();
    Reader reader = new Reader();
    Transaction savedTransaction = new Transaction();
    savedTransaction.setId(1L); // Устанавливаем ID для сохраненного объекта

    // Настраиваем моки для репозиториев
    when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
    when(readerRepository.findById(anyString())).thenReturn(Optional.of(reader));
    when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction); // Возвращаем сохраненный объект

    // Выполняем тестируемый метод
    Transaction transaction = transactionService.createTransaction(1L, "1234567890", TransactionType.BORROW);

    // Проверяем, что результат не null
    assertNotNull(transaction);
    assertEquals(savedTransaction.getId(), transaction.getId()); // Проверяем, что возвращается именно сохраненный объект

    // Проверяем, что метод save был вызван один раз
    verify(transactionRepository, times(1)).save(any(Transaction.class));
  }

  @Test
  void testCreateTransactionBookNotFound() {
    when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () ->
        transactionService.createTransaction(1L, "1234567890", TransactionType.BORROW)
    );
  }

  @Test
  void testCreateTransactionClientNotFound() {
    Book book = new Book();
    when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
    when(readerRepository.findById(anyString())).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () ->
        transactionService.createTransaction(1L, "1234567890", TransactionType.BORROW)
    );
  }
}