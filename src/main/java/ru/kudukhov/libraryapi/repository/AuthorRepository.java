package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}