package ru.kudukhov.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main entry point for the Library API application.
 * <p>
 * This class contains the main method that starts the Spring Boot application.
 * It is annotated with {@link SpringBootApplication}, which is a convenience annotation
 * that combines {@link EnableAutoConfiguration}, {@link ComponentScan}, and {@link Configuration}.
 * </p>
 */
@SpringBootApplication
public class LibraryApiApplication {

  /**
   * The main method that starts the Spring Boot application.
   * It runs the {@link SpringApplication} to launch the application.
   *
   * @param args command-line arguments (if any) passed to the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(LibraryApiApplication.class, args);
  }

}