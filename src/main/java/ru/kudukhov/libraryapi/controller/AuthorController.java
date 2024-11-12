package ru.kudukhov.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kudukhov.libraryapi.entity.Author;
import ru.kudukhov.libraryapi.service.AuthorService;

@Tag(name = "Authors", description = "Endpoints for managing authors")
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @Operation(summary = "Get the most popular author within a specific date range")
  @GetMapping("/most-popular")
  public ResponseEntity<Author> getMostPopularAuthor(
      @Parameter(description = "Start date for the range") @RequestParam LocalDate startDate,
      @Parameter(description = "End date for the range") @RequestParam LocalDate endDate) {
    Author author = authorService.getMostPopularAuthor(startDate, endDate);
    return ResponseEntity.ok(author);
  }
}