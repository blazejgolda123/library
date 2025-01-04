package com.example.library.service;


import com.example.library.DTO.*;
import com.example.library.model.Book;
import com.example.library.model.Rental;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryUserRepository;
import com.example.library.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RentalService {
    private final RentalRepository repository;
    private final BookRepository bookRepository;

    private final LibraryUserRepository libraryUserRepository;

    public RentalService(RentalRepository repository, BookRepository bookRepository, LibraryUserRepository libraryUserRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.libraryUserRepository = libraryUserRepository;
    }
    public RentalDTO createRental(RentalCreateRequest rental) {
        Rental r = new Rental();
        Optional<Book> b = bookRepository.findById(rental.getBookId());
        if(b.isPresent()){
            r.setBook(b.get());
        } else {
            //obsługa bledow. chcesz pozyczyc ksiazke której nie ma
        }

        //todo walidacja dat

//        Optional<LibraryUser> lU = libraryUserRepository.findById(rental.getLibraryUserId());
//        if(lU.isPresent()){
//            r.setUser(lU.get());
//        } else {
//            //obsługa bledow. chcesz zrobic wypozyczenie dla usera ktorego nie ma
//        }

        r.setRentalDate(rental.getRentalDate());
        r.setRentalTo(rental.getRentalTo());

        repository.save(r);

        RentalDTO response = new RentalDTO();
        response.setId(r.getId());
        response.setRentalDate(r.getRentalDate());
        response.setRentalTo(r.getRentalTo());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(r.getBook().getId());
        bookDTO.setName(r.getBook().getName());
        bookDTO.setGenre(r.getBook().getGenre());
        bookDTO.setYear2(r.getBook().getYear2());
        bookDTO.setDescription(r.getBook().getDescription());

        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(r.getBook().getAutor().getId());
        autorDTO.setDataUrodzenia(r.getBook().getAutor().getDataUrodzenia());
        autorDTO.setImie(r.getBook().getAutor().getImie());
        autorDTO.setNazwisko(r.getBook().getAutor().getNazwisko());
        autorDTO.setNarodowosc(r.getBook().getAutor().getNarodowosc());
        bookDTO.setAutor(autorDTO);

        response.setBook(bookDTO);

        return response;
    }
    public void deleteRental(UUID id) {
        repository.deleteById(id);
    }
    public Optional<RentalDTO> findRentalById(UUID id) {
        Optional<Rental> fromDataBase = repository.findById(id);

        if(fromDataBase.isEmpty()){
            return Optional.empty();
        } else {
            Rental r = fromDataBase.get();
            RentalDTO response = new RentalDTO();
            response.setId(r.getId());
            response.setRentalDate(r.getRentalDate());
            response.setRentalTo(r.getRentalTo());

            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(r.getBook().getId());
            bookDTO.setName(r.getBook().getName());
            bookDTO.setGenre(r.getBook().getGenre());
            bookDTO.setYear2(r.getBook().getYear2());
            bookDTO.setDescription(r.getBook().getDescription());

            AutorDTO autorDTO = new AutorDTO();
            autorDTO.setId(r.getBook().getAutor().getId());
            autorDTO.setDataUrodzenia(r.getBook().getAutor().getDataUrodzenia());
            autorDTO.setImie(r.getBook().getAutor().getImie());
            autorDTO.setNazwisko(r.getBook().getAutor().getNazwisko());
            autorDTO.setNarodowosc(r.getBook().getAutor().getNarodowosc());
            bookDTO.setAutor(autorDTO);

            response.setBook(bookDTO);

            return Optional.of(response);
        }
    }

    public RentalDTO updateRental(RentalUpdateRequest rental) {
        Rental r = new Rental();
        r.setId(rental.getId());
        Optional<Book> b = bookRepository.findById(rental.getBookId());
        if(b.isPresent()){
            r.setBook(b.get());
        } else {
            //obsługa bledow. chcesz pozyczyc ksiazke której nie ma
        }

        //todo walidacja dat

//        Optional<LibraryUser> lU = libraryUserRepository.findById(rental.getLibraryUserId());
//        if(lU.isPresent()){
//            r.setUser(lU.get());
//        } else {
//            //obsługa bledow. chcesz zrobic wypozyczenie dla usera ktorego nie ma
//        }

        r.setRentalDate(rental.getRentalDate());
        r.setRentalTo(rental.getRentalTo());

        repository.save(r);

        RentalDTO response = new RentalDTO();
        response.setId(r.getId());
        response.setRentalDate(r.getRentalDate());
        response.setRentalTo(r.getRentalTo());

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(r.getBook().getId());
        bookDTO.setName(r.getBook().getName());
        bookDTO.setGenre(r.getBook().getGenre());
        bookDTO.setYear2(r.getBook().getYear2());
        bookDTO.setDescription(r.getBook().getDescription());

        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(r.getBook().getAutor().getId());
        autorDTO.setDataUrodzenia(r.getBook().getAutor().getDataUrodzenia());
        autorDTO.setImie(r.getBook().getAutor().getImie());
        autorDTO.setNazwisko(r.getBook().getAutor().getNazwisko());
        autorDTO.setNarodowosc(r.getBook().getAutor().getNarodowosc());
        bookDTO.setAutor(autorDTO);

        response.setBook(bookDTO);

        return response;
    }
}

