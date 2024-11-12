package ru.kudukhov.libraryapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final CustomUserDetailsService customUserDetailsService;

  @Autowired
  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
    this.jwtTokenProvider = jwtTokenProvider;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    System.out.println("JwtAuthenticationFilter: Начало фильтрации запроса.");

    String token = getTokenFromRequest(request);
    System.out.println("JwtAuthenticationFilter: Извлеченный токен - " + token);

    if (token != null && jwtTokenProvider.validateToken(token)) {
      System.out.println("JwtAuthenticationFilter: Токен действителен.");

      String username = jwtTokenProvider.getUsername(token);
      System.out.println("JwtAuthenticationFilter: Извлеченное имя пользователя - " + username);

      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
      System.out.println("JwtAuthenticationFilter: Загрузка данных пользователя завершена.");

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      System.out.println("JwtAuthenticationFilter: Аутентификация установлена для пользователя - " + username);
    } else {
      System.out.println("JwtAuthenticationFilter: Токен недействителен или отсутствует.");
    }

    System.out.println("JwtAuthenticationFilter: Завершение фильтрации запроса.");
    filterChain.doFilter(request, response);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}