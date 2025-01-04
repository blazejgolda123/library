package com.example.library.DTO;


import java.time.LocalDate;
import java.util.UUID;

public class RentalUpdateRequest {
    private UUID id;
    private LocalDate rentalDate;
    private LocalDate rentalTo;

    private UUID bookId;
    //todo pole do userID (UUID)

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
}
