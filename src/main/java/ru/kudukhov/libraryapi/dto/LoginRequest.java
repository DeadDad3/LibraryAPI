package ru.kudukhov.libraryapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login request with username and password")
public class LoginRequest {

  @Schema(description = "Username of the employee", example = "user123")
  private String username;

  @Schema(description = "Password of the employee", example = "password123")
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}