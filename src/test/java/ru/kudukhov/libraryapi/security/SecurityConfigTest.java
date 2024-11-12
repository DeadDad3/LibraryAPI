package ru.kudukhov.libraryapi.security;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(SecurityConfig.class)
public class SecurityConfigTest {

  @Autowired
  private SecurityConfig securityConfig;

  @MockBean
  private CustomUserDetailsService customUserDetailsService;

  @MockBean
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Test
  void testPasswordEncoder() {
    PasswordEncoder encoder = securityConfig.passwordEncoder();
    assertThat(encoder).isNotNull();
  }

  @Test
  void testAuthenticationManager() throws Exception {
    AuthenticationManager authManager = securityConfig.authenticationManager(Mockito.mock(HttpSecurity.class));
    assertThat(authManager).isNotNull();
  }
}