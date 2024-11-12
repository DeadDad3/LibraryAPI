package ru.kudukhov.libraryapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents an employee with authentication details.
 */
@Schema(description = "Entity representing an employee in the library system with login credentials")
@Entity
public class Employee implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the employee", example = "1")
  private Long id;

  @Schema(description = "Username used for employee login", example = "john_doe")
  private String username;

  @Schema(description = "Password used for employee login", example = "password123")
  private String password;

  /**
   * Default constructor for Employee.
   */
  public Employee() {}

  /**
   * Constructs a new Employee with the specified ID, username, and password.
   *
   * @param id       the unique ID of the employee
   * @param username the username for employee login
   * @param password the password for employee login
   */
  public Employee(Long id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
  }

  /**
   * Returns the employee's unique identifier.
   *
   * @return the employee ID
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the employee's unique identifier.
   *
   * @param id the employee ID
   */
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username for employee login.
   *
   * @param username the username for employee login
   */
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password for employee login.
   *
   * @param password the password for employee login
   */
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  @Schema(description = "Collection of granted authorities for the employee", example = "[]")
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  @Schema(description = "Indicates if the account is expired", example = "true")
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @Schema(description = "Indicates if the account is locked", example = "true")
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @Schema(description = "Indicates if the credentials are expired", example = "true")
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @Schema(description = "Indicates if the employee account is enabled", example = "true")
  public boolean isEnabled() {
    return true;
  }
}