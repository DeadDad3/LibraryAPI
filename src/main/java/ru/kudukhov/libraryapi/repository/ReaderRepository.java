package ru.kudukhov.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kudukhov.libraryapi.entity.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String> {}