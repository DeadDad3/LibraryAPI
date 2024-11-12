package ru.kudukhov.libraryapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LibraryApiApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void generateEncodedPassword() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "1234567890"; // Замените пароль на любой, какой хотите
    String encodedPassword = encoder.encode(rawPassword);
    System.out.println("Encoded Password: " + encodedPassword);
  }
}