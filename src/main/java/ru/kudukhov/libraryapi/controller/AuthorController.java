package ru.kudukhov.libraryapi.controller;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kudukhov.libraryapi.entity.Author;
import ru.kudukhov.libraryapi.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/most-popular")
  public ResponseEntity<Author> getMostPopularAuthor(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
    Author author = authorService.getMostPopularAuthor(startDate, endDate);
    return ResponseEntity.ok(author);
  }
}