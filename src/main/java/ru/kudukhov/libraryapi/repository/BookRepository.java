package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Book;

/**
 * Repository interface for performing CRUD operations on the Book entity.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}