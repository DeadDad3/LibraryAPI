package ru.kudukhov.libraryapi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kudukhov.libraryapi.repository.EmployeeRepository;

/**
 * Service class for loading user details based on the username.
 * Implements Spring Security's UserDetailsService interface.
 * <p>
 * This service retrieves the user details from the EmployeeRepository.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final EmployeeRepository employeeRepository;

  public CustomUserDetailsService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  /**
   * Loads a user by their username.
   *
   * @param username The username of the user.
   * @return The user details for the given username.
   * @throws UsernameNotFoundException If the user is not found.
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return employeeRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}