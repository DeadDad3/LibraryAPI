package ru.kudukhov.libraryapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for generating and validating JWT tokens.
 * <p>
 * This class is responsible for creating JWT tokens, refreshing them, and extracting the username from a token.
 * </p>
 */
@Component
public class JwtTokenProvider {

  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
  private final long validityInMilliseconds = 600000; // 10 minutes

  /**
   * Generates an access token for the given authentication object.
   *
   * @param authentication The authentication object containing user details.
   * @return The generated JWT access token.
   */
  public String generateToken(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  /**
   * Validates the given JWT token.
   *
   * @param token The token to validate.
   * @return True if the token is valid, false otherwise.
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Generates a refresh token for the given authentication object.
   *
   * @param authentication The authentication object containing user details.
   * @return The generated JWT refresh token.
   */
  public String generateRefreshToken(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    Date now = new Date();
    Date validity = new Date(now.getTime() + 1800000); // 30 minutes

    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  /**
   * Generates a token for the given username.
   *
   * @param username The username for which to generate the token.
   * @return The generated JWT token.
   */
  public String generateTokenWithUsername(String username) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(key)
        .compact();
  }

  /**
   * Extracts the username from the given JWT token.
   *
   * @param token The JWT token.
   * @return The username extracted from the token.
   */
  public String getUsername(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    return claims.getSubject();
  }
}