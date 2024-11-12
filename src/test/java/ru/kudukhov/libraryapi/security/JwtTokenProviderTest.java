package ru.kudukhov.libraryapi.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtTokenProviderTest {

  private JwtTokenProvider jwtTokenProvider;

  @BeforeEach
  void setUp() {
    jwtTokenProvider = new JwtTokenProvider();
  }

  @Test
  void testGenerateAndValidateToken() {
    Authentication auth = mock(Authentication.class);
    User user = new User("testuser", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    when(auth.getPrincipal()).thenReturn(user);

    String token = jwtTokenProvider.generateToken(auth);
    assertThat(token).isNotNull();
    assertThat(jwtTokenProvider.validateToken(token)).isTrue();
  }

  @Test
  void testGetUsernameFromToken() {
    Authentication auth = mock(Authentication.class);
    User user = new User("testuser", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    when(auth.getPrincipal()).thenReturn(user);

    String token = jwtTokenProvider.generateToken(auth);
    String username = jwtTokenProvider.getUsername(token);

    assertThat(username).isEqualTo("testuser");
  }
}