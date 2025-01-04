package com.example.library.DTO;


import java.time.LocalDate;
import java.util.UUID;

public class RentalCreateRequest {


    private UUID bookId;

    private UUID libraryUserId;

    private LocalDate rentalDate;
    private LocalDate rentalTo;


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

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getLibraryUserId() {
        return libraryUserId;
    }

    public void setLibraryUserId(UUID libraryUserId) {
        this.libraryUserId = libraryUserId;
    }

}
