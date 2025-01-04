package com.example.library.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String hashHasla;
    private boolean isAdmin = false;
    @Column(nullable = false,updatable = false)
    private LocalDateTime dataRejestracji=LocalDateTime.now();

    @OneToMany(mappedBy = "libraryUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> rentals;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashHasla() {
        return hashHasla;
    }

    public void setHashHasla(String hashHasla) {
        this.hashHasla = hashHasla;
    }

    public boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public LocalDateTime getDataRejestracji() {
        return dataRejestracji;
    }

    public void setDataRejestracji(LocalDateTime dataRejestracji) {
        this.dataRejestracji = dataRejestracji;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}