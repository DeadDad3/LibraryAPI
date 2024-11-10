package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}