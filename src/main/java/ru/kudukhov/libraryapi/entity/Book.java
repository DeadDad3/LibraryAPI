package ru.kudukhov.libraryapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.*;

@Schema(description = "Book entity representing a book in the library")
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier of the book", example = "1")
  private Long id;

  @Schema(description = "Title of the book", example = "Java Programming")
  private String title;

  @Schema(description = "Year the book was published", example = "2000")
  private Integer publicationYear;

  @ManyToMany(mappedBy = "books")
  @JsonIgnore
  @Schema(description = "List of authors who wrote the book")
  private List<Author> authors = new ArrayList<>();

  public Book(Long id, String title, Integer publicationYear, List<Author> authors) {
    this.id = id;
    this.title = title;
    this.publicationYear = publicationYear;
    this.authors = authors;
  }

  public Book() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(Integer publicationYear) {
    this.publicationYear = publicationYear;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }
}
