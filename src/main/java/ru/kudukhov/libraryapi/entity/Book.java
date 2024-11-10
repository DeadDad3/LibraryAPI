package ru.kudukhov.libraryapi.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private Integer publicationYear;

  @ManyToMany(mappedBy = "books")
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
