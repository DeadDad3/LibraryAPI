package ru.kudukhov.libraryapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

  @Mock
  private JwtTokenProvider jwtTokenProvider;

  @Mock
  private CustomUserDetailsService customUserDetailsService;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  @InjectMocks
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testDoFilterInternal_ValidToken() throws ServletException, IOException {
    String token = "validToken";
    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtTokenProvider.validateToken(token)).thenReturn(true);
    when(jwtTokenProvider.getUsername(token)).thenReturn("testuser");

    // Создаем пользователя с пустым списком полномочий вместо null
    UserDetails userDetails = new User("testuser", "password", new ArrayList<>());
    when(customUserDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(customUserDetailsService).loadUserByUsername("testuser");
    verify(filterChain).doFilter(request, response);
  }

  @Test
  void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
    String token = "invalidToken";
    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtTokenProvider.validateToken(token)).thenReturn(false);

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(customUserDetailsService, never()).loadUserByUsername(anyString());
    verify(filterChain).doFilter(request, response);
  }

  @Test
  void testDoFilterInternal_NoToken() throws ServletException, IOException {
    when(request.getHeader("Authorization")).thenReturn(null);

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(customUserDetailsService, never()).loadUserByUsername(anyString());
    verify(filterChain).doFilter(request, response);
  }
}