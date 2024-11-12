package ru.kudukhov.libraryapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.kudukhov.libraryapi.entity.Author;
import ru.kudukhov.libraryapi.security.JwtTokenProvider;
import ru.kudukhov.libraryapi.security.CustomUserDetailsService;
import ru.kudukhov.libraryapi.service.AuthorService;

import java.time.LocalDate;

@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc(addFilters = false) // Отключаем фильтры безопасности для этого теста
public class AuthorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AuthorService authorService;

  @MockBean
  private JwtTokenProvider jwtTokenProvider; // Добавляем mock для JwtTokenProvider

  @MockBean
  private CustomUserDetailsService customUserDetailsService; // Добавляем mock для CustomUserDetailsService

  @Test
  void testGetMostPopularAuthor() throws Exception {
    Author author = new Author();
    author.setId(1L);
    author.setFirstName("George");
    author.setLastName("Orwell");
    when(authorService.getMostPopularAuthor(any(LocalDate.class), any(LocalDate.class))).thenReturn(author);

    mockMvc.perform(get("/api/authors/most-popular")
            .param("startDate", "2024-01-01")
            .param("endDate", "2024-12-31"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("George"))
        .andExpect(jsonPath("$.lastName").value("Orwell"));
  }
}