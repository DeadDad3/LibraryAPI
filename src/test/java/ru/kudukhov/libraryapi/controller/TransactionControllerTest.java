package ru.kudukhov.libraryapi.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import ru.kudukhov.libraryapi.entity.Transaction;
import ru.kudukhov.libraryapi.enums.TransactionType;
import ru.kudukhov.libraryapi.security.JwtTokenProvider;
import ru.kudukhov.libraryapi.security.CustomUserDetailsService;
import ru.kudukhov.libraryapi.service.TransactionService;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TransactionService transactionService;

  @MockBean
  private JwtTokenProvider jwtTokenProvider;

  @MockBean
  private CustomUserDetailsService customUserDetailsService;

  private RequestPostProcessor bearerToken;

  @BeforeEach
  void setUp() {
    // Создаем mock-токен, который будет добавляться к каждому запросу
    when(jwtTokenProvider.validateToken(anyString())).thenReturn(true);
    when(jwtTokenProvider.getUsername(anyString())).thenReturn("testuser");
    bearerToken = SecurityMockMvcRequestPostProcessors.jwt();
  }

  @Test
  void testCreateTransaction() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setId(1L);
    when(transactionService.createTransaction(anyLong(), anyString(), any(TransactionType.class))).thenReturn(transaction);

    mockMvc.perform(post("/api/transactions")
            .param("bookId", "1")
            .param("clientId", "1234567890")
            .param("transactionType", "BORROW")
            .with(bearerToken)) // Добавляем токен для авторизации
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
  }
}