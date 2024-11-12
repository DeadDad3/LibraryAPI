package ru.kudukhov.libraryapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Security configuration class for setting up Spring Security.
 * <p>
 * This class defines the security filter chain, the authentication manager, and password encoding method.
 * It also sets up JWT-based authentication.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomUserDetailsService customUserDetailsService;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.customUserDetailsService = customUserDetailsService;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  /**
   * Configures HTTP security, including which endpoints require authentication and which do not.
   * <p>
   * This method configures the filter chain to disable CSRF, allow certain endpoints without authentication,
   * and require authentication for others. It also configures stateless session management and adds a JWT filter.
   * </p>
   *
   * @param http The HTTP security object.
   * @return The configured security filter chain.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login", "/api/readers/unreturned-books").permitAll()
            .requestMatchers("/api/transactions/**", "/api/authors/most-popular", "/api/readers/top-reader").authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /**
   * Configures the authentication manager.
   * <p>
   * This method sets up the user details service and password encoder for Spring Security.
   * </p>
   *
   * @param http The HTTP security object.
   * @return The configured authentication manager.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    return authenticationManagerBuilder.build();
  }

  /**
   * Configures the password encoder for Spring Security.
   * <p>
   * This method uses BCryptPasswordEncoder to encode passwords.
   * </p>
   *
   * @return The password encoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}