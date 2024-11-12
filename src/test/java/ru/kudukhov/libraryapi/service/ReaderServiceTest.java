package ru.kudukhov.libraryapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kudukhov.libraryapi.entity.Reader;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.repository.ReaderRepository;
import ru.kudukhov.libraryapi.repository.TransactionRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReaderServiceTest {

  @Mock
  private ReaderRepository readerRepository;

  @Mock
  private TransactionRepository transactionRepository;

  @InjectMocks
  private ReaderService readerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetTopReader() {
    Reader reader = new Reader();
    Transaction transaction = new Transaction();
    transaction.setClient(reader);

    List<Transaction> transactions = Collections.singletonList(transaction);

    when(transactionRepository.findAll()).thenReturn(transactions);

    Reader result = readerService.getTopReader();

    assertEquals(reader, result);
    verify(transactionRepository, times(1)).findAll();
  }

  @Test
  void testGetReadersByUnreturnedBooksCount() {
    Reader reader1 = new Reader();
    Reader reader2 = new Reader();

    Transaction transaction1 = new Transaction();
    transaction1.setClient(reader1);
    transaction1.setTransactionType(TransactionType.BORROW);

    Transaction transaction2 = new Transaction();
    transaction2.setClient(reader2);
    transaction2.setTransactionType(TransactionType.BORROW);

    Transaction transaction3 = new Transaction();
    transaction3.setClient(reader2);
    transaction3.setTransactionType(TransactionType.RETURN);

    List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

    when(transactionRepository.findAll()).thenReturn(transactions);

    List<Reader> result = readerService.getReadersByUnreturnedBooksCount();

    assertEquals(1, result.size());
    assertEquals(reader1, result.get(0));
    verify(transactionRepository, times(1)).findAll();
  }
}

