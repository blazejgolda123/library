package com.example.library.DTO;


import java.time.LocalDate;
import java.util.UUID;

public class RentalDTO {
    private UUID id;

    private LocalDate rentalDate;
    private LocalDate rentalTo;

    private BookDTO book;

    //todo pole do user

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
    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
