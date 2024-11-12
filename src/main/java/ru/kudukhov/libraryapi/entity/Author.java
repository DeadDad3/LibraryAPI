package ru.kudukhov.libraryapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Schema(description = "Author entity storing author details and associated books")
@Entity
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the author", example = "1")
  private Long id;

  @Schema(description = "First name of the author", example = "Jane")
  private String firstName;

  @Schema(description = "Last name of the author", example = "Doe")
  private String lastName;

  @Schema(description = "Birth date of the author", example = "1970-01-01")
  private LocalDate birthDate;

  @ManyToMany
  @JoinTable(name = "author_books",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  @JsonIgnore
  @Schema(description = "List of books written by the author")
  private List<Book> books = new ArrayList<>();

  public Author(Long id, String firstName, String lastName, LocalDate birthDate, List<Book> books) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.books = books;
  }

  public Author() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}
