package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.library.model.LibraryUser;
import java.util.UUID;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, UUID> {
    Optional<LibraryUser> findByEmail(String email);
}