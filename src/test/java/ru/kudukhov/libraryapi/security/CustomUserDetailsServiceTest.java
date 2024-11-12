package ru.kudukhov.libraryapi.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kudukhov.libraryapi.entity.Employee;
import ru.kudukhov.libraryapi.repository.EmployeeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private CustomUserDetailsService customUserDetailsService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testLoadUserByUsername_UserFound() {
    // Создаем Employee вместо User
    Employee employee = new Employee();
    employee.setUsername("testuser");
    employee.setPassword("password");

    when(employeeRepository.findByUsername("testuser")).thenReturn(Optional.of(employee));

    UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser");

    assertThat(userDetails).isNotNull();
    assertThat(userDetails.getUsername()).isEqualTo("testuser");
  }

  @Test
  void testLoadUserByUsername_UserNotFound() {
    when(employeeRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class, () -> {
      customUserDetailsService.loadUserByUsername("unknownuser");
    });
  }
}