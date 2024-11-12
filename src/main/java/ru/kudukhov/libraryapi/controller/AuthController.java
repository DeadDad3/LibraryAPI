package ru.kudukhov.libraryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.kudukhov.libraryapi.security.JwtTokenProvider;
import ru.kudukhov.libraryapi.dto.LoginRequest;
import ru.kudukhov.libraryapi.dto.TokenResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
      );

      String accessToken = jwtTokenProvider.generateToken(authentication);
      String refreshToken = jwtTokenProvider.generateRefreshToken(authentication); // Добавьте метод для создания refresh token

      return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(401).body(null);
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<TokenResponse> refresh(@RequestBody TokenResponse tokenResponse) {
    if (jwtTokenProvider.validateToken(tokenResponse.getRefreshToken())) {
      String username = jwtTokenProvider.getUsername(tokenResponse.getRefreshToken());
      String newAccessToken = jwtTokenProvider.generateTokenWithUsername(username);
      return ResponseEntity.ok(new TokenResponse(newAccessToken, tokenResponse.getRefreshToken()));
    } else {
      return ResponseEntity.status(401).body(null);
    }
  }
}