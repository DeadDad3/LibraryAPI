package ru.kudukhov.libraryapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kudukhov.libraryapi.entity.Author;
import ru.kudukhov.libraryapi.entity.Book;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.repository.AuthorRepository;
import ru.kudukhov.libraryapi.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

  @Mock
  private AuthorRepository authorRepository;

  @Mock
  private TransactionRepository transactionRepository;

  @InjectMocks
  private AuthorService authorService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetMostPopularAuthor() {
    Author author = new Author();
    author.setFirstName("George");
    author.setLastName("Orwell");

    Book book = new Book();
    book.setAuthors(Collections.singletonList(author));

    Transaction transaction = new Transaction();
    transaction.setBook(book);
    transaction.setTransactionDateTime(LocalDateTime.now());

    List<Transaction> transactions = Collections.singletonList(transaction);

    when(transactionRepository.findByTransactionDateTimeBetween(any(), any())).thenReturn(transactions);

    Author result = authorService.getMostPopularAuthor(LocalDate.now(), LocalDate.now());

    assertEquals(author, result);
    verify(transactionRepository, times(1)).findByTransactionDateTimeBetween(any(), any());
  }
}
