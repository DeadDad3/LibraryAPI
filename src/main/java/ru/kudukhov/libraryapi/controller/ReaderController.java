package ru.kudukhov.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import ru.kudukhov.libraryapi.entity.Reader;
import ru.kudukhov.libraryapi.service.ReaderService;

@Tag(name = "Readers", description = "Endpoints for managing readers")
@RestController
@RequestMapping("/api/readers")
public class ReaderController {

  private final ReaderService readerService;

  public ReaderController(ReaderService readerService) {
    this.readerService = readerService;
  }

  @Operation(summary = "Get the most active reader (most books taken)")
  @GetMapping("/top-reader")
  public ResponseEntity<Reader> getTopReader() {
    System.out.println("Handling /api/top-reader request");
    Reader topReader = readerService.getTopReader();
    return ResponseEntity.ok(topReader);
  }

  @Operation(summary = "Get a list of readers sorted by the number of unreturned books")
  @GetMapping("/unreturned-books")
  public ResponseEntity<List<Reader>> getReadersByUnreturnedBooksCount() {
    List<Reader> readers = readerService.getReadersByUnreturnedBooksCount();
    return ResponseEntity.ok(readers);
  }
}