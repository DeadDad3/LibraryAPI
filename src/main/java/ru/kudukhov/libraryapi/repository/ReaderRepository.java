package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Reader;

/**
 * Repository interface for performing CRUD operations on the Reader entity.
 * <p>
 * This repository uses the phone number as the primary key for the Reader entity.
 * </p>
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}