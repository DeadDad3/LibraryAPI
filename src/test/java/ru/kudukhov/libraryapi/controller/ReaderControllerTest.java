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
import ru.kudukhov.libraryapi.entity.Reader;
import ru.kudukhov.libraryapi.security.JwtTokenProvider;
import ru.kudukhov.libraryapi.security.CustomUserDetailsService;
import ru.kudukhov.libraryapi.service.ReaderService;

import java.util.Collections;
import java.util.List;

@WebMvcTest(ReaderController.class)
public class ReaderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReaderService readerService;

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
  void testGetTopReader() throws Exception {
    Reader reader = new Reader();
    reader.setFirstName("John");
    reader.setLastName("Doe");
    when(readerService.getTopReader()).thenReturn(reader);

    mockMvc.perform(get("/api/readers/top-reader")
            .with(bearerToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"));
  }

  @Test
  void testGetReadersByUnreturnedBooksCount() throws Exception {
    Reader reader = new Reader();
    reader.setFirstName("John");
    reader.setLastName("Doe");
    List<Reader> readers = Collections.singletonList(reader);
    when(readerService.getReadersByUnreturnedBooksCount()).thenReturn(readers);

    mockMvc.perform(get("/api/readers/unreturned-books")
            .with(bearerToken))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstName").value("John"))
        .andExpect(jsonPath("$[0].lastName").value("Doe"));
  }
}