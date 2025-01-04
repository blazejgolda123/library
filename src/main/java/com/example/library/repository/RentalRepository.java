package com.example.library.repository;
import com.example.library.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface RentalRepository extends JpaRepository<Rental, UUID> {
}
