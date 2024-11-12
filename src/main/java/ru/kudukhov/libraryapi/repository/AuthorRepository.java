package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Author;

/**
 * Repository interface for performing CRUD operations on the Author entity.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}