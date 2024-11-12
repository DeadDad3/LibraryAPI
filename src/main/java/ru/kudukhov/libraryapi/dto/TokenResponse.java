package ru.kudukhov.libraryapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response containing access and refresh tokens")
public class TokenResponse {

  @Schema(description = "Access token for the user", example = "eyJhb...")
  private String accessToken;

  @Schema(description = "Refresh token for the user", example = "eyJhb...")
  private String refreshToken;

  public TokenResponse(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}