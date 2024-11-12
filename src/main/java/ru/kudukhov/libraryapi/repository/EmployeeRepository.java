package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Employee;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the Employee entity.
 * <p>
 * This repository also contains a custom method to find an employee by their username.
 * </p>
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  /**
   * Finds an Employee by their username.
   *
   * @param username The username of the employee.
   * @return An Optional containing the Employee if found, otherwise empty.
   */
  Optional<Employee> findByUsername(String username);
}