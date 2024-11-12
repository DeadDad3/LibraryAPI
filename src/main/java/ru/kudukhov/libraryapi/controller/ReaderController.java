package ru.kudukhov.libraryapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import ru.kudukhov.libraryapi.entity.Reader;
import ru.kudukhov.libraryapi.service.ReaderService;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

  private final ReaderService readerService;

  public ReaderController(ReaderService readerService) {
    this.readerService = readerService;
  }

  // Endpoint для получения самого читающего клиента
  @GetMapping("/top-reader")
  public ResponseEntity<Reader> getTopReader() {
    System.out.println("Handling /api/top-reader request");
    Reader topReader = readerService.getTopReader();
    return ResponseEntity.ok(topReader);
  }

  // Endpoint для получения списка читателей по количеству несданных книг
  @GetMapping("/unreturned-books")
  public ResponseEntity<List<Reader>> getReadersByUnreturnedBooksCount() {
    List<Reader> readers = readerService.getReadersByUnreturnedBooksCount();
    return ResponseEntity.ok(readers);
  }
}