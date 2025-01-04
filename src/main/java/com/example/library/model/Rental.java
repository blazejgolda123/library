package com.example.library.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="id_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name="id_libraryUser")
    private LibraryUser libraryUser;

    private LocalDate rentalDate;

    private LocalDate rentalTo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getRentalTo() {
        return rentalTo;
    }

    public void setRentalTo(LocalDate rentalTo) {
        this.rentalTo = rentalTo;
    }
}
